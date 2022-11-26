package com.carus.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.carus.config.AuthenticationConfig;
import com.carus.dto.AddressDTO;
import com.carus.dto.RegisterUserDTO;
import com.carus.dto.UpdateUserDTO;
import com.carus.dto.UserDTO;
import com.carus.dto.UserProfileDTO;
import com.carus.entities.AddressEntity;
import com.carus.entities.UserEntity;
import com.carus.repositories.AddressRepository;
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
    private AddressRepository addressRepository;

    @Autowired
    private RateUserService rateUserService;

    @Autowired
    private AuthenticationConfig authenticationConfig;

    @Autowired
    private AddressService addressService;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO findByUuid(String uuid) {
        return new UserDTO(this.findEntityByUuid(uuid));
    }

    @Transactional(readOnly = true)
    public UserEntity findEntityByUuid(String uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(() -> {
            log.error("Entity with id {} not found", uuid);
            return new EntityNotFoundException("Entity with id ".concat(uuid.toString()).concat(" not found"));
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

    public UserDTO update(UpdateUserDTO user, String uuid) {
        try {
            UserEntity entity = this.updateDtoToEntity(user, uuid);
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

    public UserProfileDTO getUserProfile() {
        UserDTO user = this.getLoggedUserDTO();
        Long rateNumber = rateUserService.countRatesByUserId(user.getUuid());
        return new UserProfileDTO(user, rateNumber);
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
    public void deleteByUuid(String uuid) {
        userRepository.deleteByUuid(uuid);
    }

    public UserEntity updateDtoToEntity(UpdateUserDTO dto, String uuid) {
        UserEntity entity = this.findEntityByUuid(uuid);
        AddressEntity address = addressService.dtoToEntity(dto.getAddress());
        address.setId(dto.getAddress().getId());
        entity.setLogin(dto.getLogin());
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setCpf(dto.getCpf());
        entity.setPhone(dto.getPhone());
        entity.setGender(dto.getGender());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setAbout(dto.getAbout());
        entity.setAddress(address);
        entity.setProfileImageUrl(dto.getProfileImageUrl());
        return entity;
    }

    public UserEntity registerDtoToEntity(RegisterUserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUuid(dto.getUuid());
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
