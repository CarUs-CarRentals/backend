package com.carus.services;

import com.carus.dto.RateCarDTO;
import com.carus.entities.RateCarEntity;
import com.carus.repositories.RateCarRepository;
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
public class RateCarService {

    @Autowired
    private RateCarRepository rateCarRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarService carService;

    @Transactional(readOnly = true)
    public List<RateCarDTO> findAll() {
        return rateCarRepository.findAll().stream().map(RateCarDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RateCarEntity findEntityById(Long id) {
        return rateCarRepository.findById(id).orElseThrow(() -> {
            log.error("Entity with id {} not found", id);
            return new EntityNotFoundException("Entity with id ".concat(id.toString()).concat(" not found"));
        });
    }

    @Transactional(readOnly = true)
    public RateCarDTO findById(Long id) {
        return new RateCarDTO(this.findEntityById(id));
    }

    @Transactional
    public RateCarDTO save(RateCarDTO dto) {
        RateCarEntity entity = this.dtoToEntity(dto);
        try {
            entity = rateCarRepository.save(entity);
            return new RateCarDTO(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityAlreadyExistsException("Entity already exists");
        }
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            rateCarRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            log.error("Entity with id {} not found", id);
            throw new EntityNotFoundException("Entity with id ".concat(id.toString()).concat(" not found"));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new InternalServerErrorException("An internal server error has occurred, please try again later");
        }
    }

    private RateCarEntity dtoToEntity(RateCarDTO dto) {
        RateCarEntity entity = new RateCarEntity();
        entity.setUser(userRepository.findByUuid(dto.getUserUuid()).get());
        entity.setCar(carService.findEntityById(dto.getCarId()));
        entity.setRate(dto.getRate());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());

        return entity;
    }
}
