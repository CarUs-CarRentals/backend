package com.carus.services;

import com.carus.dto.CnhDTO;
import com.carus.entities.CnhEntity;
import com.carus.repositories.CnhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CnhService {

    @Autowired
    private CnhRepository cnhRepository;

    @Transactional(readOnly = true)
    public List<CnhDTO> findAll() {
        return cnhRepository.findAll().stream().map(entity -> new CnhDTO(entity)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CnhDTO findById(Long id) {
        return new CnhDTO(cnhRepository.findById(id).orElse(null));
    }

    @Transactional
    public CnhDTO save(CnhDTO dto) {
        CnhEntity entity = cnhRepository.save(this.DTOToEntity(dto));
        return new CnhDTO(entity);
    }

    @Transactional
    public void deleteById(Long id) {
        cnhRepository.deleteById(id);
    }

    private CnhEntity DTOToEntity(CnhDTO dto) {
        CnhEntity entity = new CnhEntity();
        entity.setRg(dto.getRg());
        entity.setRegisterNumber((dto.getRegisterNumber()));
        entity.setCnhNumber(dto.getCnhNumber());
        entity.setBirthDate(dto.getBirthDate());
        entity.setStateEnum(dto.getStateEnum());
        entity.setExpirationDate(dto.getExpirationDate());

        return entity;
    }
}
