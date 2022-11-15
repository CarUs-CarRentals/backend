package com.carus.services;

import com.carus.dto.RateUserDTO;
import com.carus.entities.RateUserEntity;
import com.carus.repositories.RateUserRepository;
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
import java.util.stream.Collectors;

@Service
@Log4j2
public class RateUserService {

    @Autowired
    private RateUserRepository rateUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<RateUserDTO> findAll() {
        return rateUserRepository.findAll().stream().map(RateUserDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RateUserEntity findEntityById(Long id) {
        return rateUserRepository.findById(id).orElseThrow(() -> {
            log.error("Entity with id {} not found", id);
            return new EntityNotFoundException("Entity with id ".concat(id.toString()).concat(" not found"));
        });
    }

    @Transactional(readOnly = true)
    public RateUserDTO findById(Long id) {
        return new RateUserDTO(this.findEntityById(id));
    }

    @Transactional
    public RateUserDTO save(RateUserDTO dto) {
        RateUserEntity entity = this.dtoToEntity(dto);
        try {
            entity = rateUserRepository.save(entity);
            return new RateUserDTO(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityAlreadyExistsException("Entity already exists");
        }
    }

    @Transactional
    public List<RateUserDTO> findAllByUserUuid(String uuid) {
        List<RateUserEntity> rateUser = rateUserRepository.findByUserUuid(uuid);
        return rateUser.stream().map(RateUserDTO::new).collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public Long countRatesByUserId(String userUuid) {
        return rateUserRepository.countRatesByUserId(userUuid);
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            rateUserRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            log.error("Entity with id {} not found", id);
            throw new EntityNotFoundException("Entity with id ".concat(id.toString()).concat(" not found"));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new InternalServerErrorException("An internal server error has occurred, please try again later");
        }
    }

    private RateUserEntity dtoToEntity(RateUserDTO dto) {
        RateUserEntity entity = new RateUserEntity();
        entity.setRatedUser(userRepository.findByUuid(dto.getRatedUser()).get());
        entity.setEvaluatedUser(userRepository.findByUuid(dto.getEvaluatedUser()).get());
        entity.setRate(dto.getRate());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());

        return entity;
    }
}
