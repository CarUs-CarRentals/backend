package com.carus.services;

import com.carus.dto.UserDTO;
import com.carus.entities.UserEntity;
import com.carus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(entity -> new UserDTO(entity)).collect(Collectors.toList());
    }

    @Transactional
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public UserDTO create(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new UserDTO(userRepository.save(user));
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
}
