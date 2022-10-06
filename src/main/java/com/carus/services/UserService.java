package com.carus.services;

import com.carus.dto.CarUserDTO;
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

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        return new UserDTO(userRepository.findById(id).get());
    }

    @Transactional(readOnly = true)
    public UserEntity findEntityById(Long id) {
        return userRepository.findById(id).get();
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

    @Transactional
    public UserDTO save(UserDTO dto) {
        UserEntity entity = userRepository.save(this.dtoToEntity(dto));
        return new UserDTO(entity);
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
}
