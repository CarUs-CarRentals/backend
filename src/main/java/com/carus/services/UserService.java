package com.carus.services;

import com.carus.dto.UserDTO;
import com.carus.entities.UserEntity;
import com.carus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(entity -> new UserDTO(entity)).toList();
    }

    @Transactional
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
