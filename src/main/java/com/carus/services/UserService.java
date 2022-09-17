package com.carus.services;

import com.carus.dto.CarUserDTO;
import com.carus.dto.UserDTO;
import com.carus.entities.UserEntity;
import com.carus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return new UserDTO(userRepository.findById(id).orElse(new UserEntity()));
    }

    @Transactional(readOnly = true)
    public UserEntity findById(CarUserDTO carUserDTO) {
        return userRepository.findById(carUserDTO.getId()).orElse(new UserEntity());
    }

    @Transactional
    public UserDTO create(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new UserDTO(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

    @Transactional
    public UserDTO save(UserDTO dto) {
        UserEntity entity = userRepository.save(this.DTOToEntity(dto));
        return new UserDTO(entity);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private UserEntity DTOToEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.getLogin();
        entity.getEmail();
        entity.getFirstName();
        entity.getLastName();
        entity.getCpf();
        entity.getRg();
        entity.getPhone();
        entity.getGender();
        return entity;
    }
}
