package com.carus.services;

import com.carus.dto.RateUserDTO;
import com.carus.entities.RateUserEntity;
import com.carus.repositories.RateUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateUserService {

    @Autowired
    private RateUserRepository rateUserRepository;

    @Transactional(readOnly = true)
    public List<RateUserDTO> findAll() {
        return rateUserRepository.findAll().stream().map(entity -> new RateUserDTO(entity)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RateUserDTO findById(Long id) {
        return new RateUserDTO(rateUserRepository.findById(id).orElse(null));
    }

    @Transactional
    public RateUserDTO save(RateUserDTO dto) {
        RateUserEntity entity = rateUserRepository.save(this.DTOToEntity(dto));
        return new RateUserDTO(entity);
    }

    @Transactional(readOnly = true)
    public Long countRatesByUserId(Long userId) {
        return rateUserRepository.countRatesByUserId(userId);
    }

    @Transactional
    public void deleteById(Long id) {
        rateUserRepository.deleteById(id);
    }

    private RateUserEntity DTOToEntity(RateUserDTO dto) {
        RateUserEntity entity = new RateUserEntity();
        entity.setRate(dto.getRate());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());

        return entity;
    }
}
