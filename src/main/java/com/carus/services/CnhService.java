package com.carus.services;

import com.carus.dto.CnhDTO;
import com.carus.entities.CnhEntity;
import com.carus.entities.UserEntity;
import com.carus.repositories.CnhRepository;
import com.carus.repositories.UserRepository;
import com.carus.services.exceptions.EntityAlreadyExistsException;
import com.carus.services.exceptions.EntityNotFoundException;
import com.carus.services.exceptions.InternalServerErrorException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CnhService {

    @Autowired
    private CnhRepository cnhRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<CnhDTO> findAll() {
        return cnhRepository.findAll().stream().map(CnhDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CnhDTO findById(Long id) {
        return new CnhDTO(this.findEntityById(id));
    }

    @Transactional
    public CnhDTO save(CnhDTO dto) {
        CnhEntity entity = this.dtoToEntity(dto);
        try {
            entity = cnhRepository.save(entity);
            return new CnhDTO(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityAlreadyExistsException("Entity already exists");
        }
    }

    public CnhDTO findByUserUuid(String userUuid) {
        Optional<CnhEntity> cnhOpt = cnhRepository.findByUserUuid(userUuid);
        if (cnhOpt.isPresent()) return new CnhDTO(cnhOpt.get());

        return null;
    }

    @Transactional
    public CnhDTO update(CnhDTO dto, Long id) {
        CnhEntity entity = this.dtoToEntity(dto);
        entity.setId(id);
        return new CnhDTO(cnhRepository.save(entity));
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            cnhRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            log.error("Entity with id {} not found", id);
            throw new EntityNotFoundException("Entity with id ".concat(id.toString()).concat(" not found"));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new InternalServerErrorException("An internal server error has occurred, please try again later");
        }
    }

    @Transactional(readOnly = true)
    public CnhEntity findEntityById(Long id) {
        return cnhRepository.findById(id).orElseThrow(() -> {
            log.error("Entity with id {} not found", id);
            return new EntityNotFoundException("Entity with id ".concat(id.toString()).concat(" not found"));
        });
    }

    private CnhEntity dtoToEntity(CnhDTO dto) {
        UserEntity user = userRepository.findByUuid(dto.getUserId()).get();
        CnhEntity entity = new CnhEntity();
        entity.setRg(dto.getRg());
        entity.setUser(user);
        entity.setRegisterNumber((dto.getRegisterNumber()));
        entity.setCnhNumber(dto.getCnhNumber());
        entity.setBirthDate(dto.getBirthDate());
        entity.setState(dto.getState());
        entity.setExpirationDate(dto.getExpirationDate());

        return entity;
    }
}
