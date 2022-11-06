package com.carus.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.carus.config.AuthenticationConfig;
import com.carus.dto.RegisterUserDTO;
import com.carus.dto.UpdateUserDTO;
import com.carus.dto.UserDTO;
import com.carus.entities.UserEntity;
import com.carus.repositories.UserRepository;
import com.carus.services.exceptions.EntityAlreadyExistsException;
import com.carus.services.exceptions.EntityNotFoundException;
import com.carus.services.exceptions.InternalServerErrorException;
import com.carus.services.exceptions.TokenException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationConfig authenticationConfig;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        return new UserDTO(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public UserEntity findEntityById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity with id {} not found", id);
            return new EntityNotFoundException("Entity with id ".concat(id.toString()).concat(" not found"));
        });
    }

    @Transactional
    public UserDTO create(RegisterUserDTO user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            UserEntity entity = registerDtoToEntity(user);
            return new UserDTO(userRepository.save(entity));
        } catch (DataIntegrityViolationException constraintException) {
            log.info("Login " + user.getLogin() + "already used");
            String message = constraintException.getCause().getMessage().concat(". Login " + user.getLogin() + " already used");
            throw new EntityAlreadyExistsException(message);
        } catch (Exception ex) {
            log.error(ex);
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    public UserDTO update(UpdateUserDTO user, Long id) {
        try {
            UserEntity entity = this.updateDtoToEntity(user, id);
            return new UserDTO(userRepository.save(entity));
        } catch (DataIntegrityViolationException constraintException) {
            log.info("Login " + user.getLogin() + ", CPF " + user.getCpf() + " or RG " + user.getRg() + " already used");
            String message = ". Login " + user.getLogin() + ", CPF " + user.getCpf() + " or RG " + user.getRg() + " already used";
            throw new EntityAlreadyExistsException(constraintException.getCause().getMessage().concat(message));
        } catch (Exception ex) {
            log.error(ex);
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    protected UserEntity getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity logged = userRepository.findByLogin(authentication.getPrincipal().toString()).get();
        return logged;
    }

    public UserDTO getLoggedUserDTO() {
        return new UserDTO(getLoggedUser());
    }


    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public UserEntity updateDtoToEntity(UpdateUserDTO dto, Long id) {
        UserEntity entity = this.findEntityById(id);
        entity.setLogin(dto.getLogin());
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setCpf(dto.getCpf());
        entity.setRg(dto.getRg());
        entity.setPhone(dto.getPhone());
        entity.setGender(dto.getGender());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setAbout(dto.getAbout());
        entity.setProfileImageUrl(dto.getProfileImageUrl());
        return entity;
    }

    public UserEntity registerDtoToEntity(RegisterUserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setRefreshToken(this.generateRefreshToken(entity.getLogin()));
        entity.setRefreshTokenExpiration(LocalDateTime.now().plusYears(1));
        entity.setMemberSince(LocalDate.now());
        return entity;
    }

    public Map<String, Object> refreshToken(HttpServletRequest request) {
        try {
            String attribute = request.getHeader(authenticationConfig.getTokenRequestHeader());
            String tokenPrefix = authenticationConfig.getTokenPrefix();
            String tokenPassword = authenticationConfig.getTokenPassword();

            if (attribute == null || !attribute.startsWith(tokenPrefix)) throw new TokenException("Invalid refresh token");

            String refreshToken = attribute.replace(tokenPrefix, "");
            String username = JWT.require(Algorithm.HMAC512(tokenPassword))
                    .build()
                    .verify(refreshToken)
                    .getSubject();

            UserEntity user = (UserEntity) this.loadUserByUsername(username);
            String newToken = generateToken(user.getLogin());
            long expiration = System.currentTimeMillis() + authenticationConfig.getTokenExpiration();

            Map<String, Object> data = new HashMap<>();
            data.put("token", newToken);
            data.put("tokenExpiration", expiration / 1000);
            return data;
        } catch (Exception ex) {
            log.error(ex);
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + authenticationConfig.getTokenExpiration()))
                .sign(Algorithm.HMAC512(authenticationConfig.getTokenPassword()));
    }

    public String generateRefreshToken(String username) {
        return JWT.create()
                .withSubject(username)
                    .withExpiresAt(new Date(System.currentTimeMillis() + LocalDateTime.now().plusYears(1).toInstant(ZoneOffset.UTC).toEpochMilli()))
                .sign(Algorithm.HMAC512(authenticationConfig.getTokenPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }
}
