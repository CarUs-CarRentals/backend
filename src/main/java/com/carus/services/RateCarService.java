package com.carus.services;

import com.carus.dto.RateCarDTO;
import com.carus.entities.RateCarEntity;
import com.carus.repositories.RateCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateCarService {

    @Autowired
    private RateCarRepository rateCarRepository;

    @Transactional(readOnly = true)
    public List<RateCarDTO> findAll() {
        return rateCarRepository.findAll().stream().map(entity -> new RateCarDTO(entity)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RateCarDTO findById(Long id) {
        return new RateCarDTO(rateCarRepository.findById(id).orElse(null));
    }

    @Transactional
    public RateCarDTO save(RateCarDTO dto) {
        RateCarEntity entity = rateCarRepository.save(this.DTOToEntity(dto));
        return new RateCarDTO(entity);
    }

    @Transactional
    public void deleteById(Long id) {
        rateCarRepository.deleteById(id);
    }

    private RateCarEntity DTOToEntity(RateCarDTO dto) {
        RateCarEntity entity = new RateCarEntity();
        entity.setRate(dto.getRate());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());

        return entity;
    }
}
