package com.carus.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.carus.config.AuthenticationConfig;
import com.carus.dto.RegisterUserDTO;
import com.carus.dto.UserDTO;
import com.carus.entities.UserEntity;
import com.carus.repositories.UserRepository;
import com.carus.services.exceptions.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity entity = registerDtoToEntity(user);
        return new UserDTO(userRepository.save(entity));
    }

    protected UserEntity getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity logged = userRepository.findByLogin(authentication.getPrincipal().toString()).get();
        return logged;
    }

    public UserDTO getLoggedUserDTO() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity logged = userRepository.findByLogin(authentication.getPrincipal().toString()).get();
        return new UserDTO(logged);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public UserEntity dtoToEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setCpf(dto.getCpf());
        entity.setRg(dto.getRg());
        entity.setPhone(dto.getPhone());
        entity.setGender(dto.getGender());
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
        return entity;
    }

    public Optional<String> refreshToken(HttpServletRequest request) {
        String attribute = request.getHeader(authenticationConfig.getTokenRequestHeader());
        String tokenPrefix = authenticationConfig.getTokenPrefix();
        String tokenPassword = authenticationConfig.getTokenPassword();

        if (attribute == null || !attribute.startsWith(tokenPrefix)) return Optional.empty();

        String refreshToken = attribute.replace(tokenPrefix, "");
        String username = JWT.require(Algorithm.HMAC512(tokenPassword))
                .build()
                .verify(refreshToken)
                .getSubject();

        UserEntity user = (UserEntity) this.loadUserByUsername(username);
        String newToken = generateToken(user.getLogin());
        return Optional.of(newToken);
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

}
