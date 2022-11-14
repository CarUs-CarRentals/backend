package com.carus.services;

import com.carus.dto.RentalDTO;
import com.carus.entities.RentalEntity;
import com.carus.repositories.LocationRepository;
import com.carus.services.exceptions.EntityNotFoundException;
import com.carus.services.exceptions.InternalServerErrorException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class RentalService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Transactional(readOnly = true)
    public List<RentalDTO> findAll() {
        return locationRepository.findAll().stream().map(RentalDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RentalDTO findById(Long id) {
        return new RentalDTO(locationRepository.findById(id).orElseThrow(() -> {
            log.error("Entity with id {} not found", id);
            return new EntityNotFoundException("Entity with id ".concat(id.toString()).concat(" not found"));
        }));
    }

    @Transactional
    public RentalDTO save(RentalDTO dto) {
        RentalEntity entity = locationRepository.save(this.dtoToEntity(dto));
        return new RentalDTO(entity);
    }

    @Transactional
    public RentalDTO update(RentalDTO dto, Long id) {
        RentalEntity updated = this.dtoToEntity(dto);
        updated.setId(id);
        return new RentalDTO(locationRepository.save(updated));
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            locationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            log.error("Entity with id {} not found", id);
            throw new EntityNotFoundException("Entity with id ".concat(id.toString()).concat(" not found"));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new InternalServerErrorException("An internal server error has occurred, please try again later");
        }
    }

    private RentalEntity dtoToEntity(RentalDTO dto) {
        RentalEntity entity = new RentalEntity();
        entity.setUser(userService.findEntityByUuid(dto.getUser()));
        entity.setCar(carService.findEntityById(dto.getCar()));
        entity.setLocationDate(dto.getLocationDate());
        entity.setReturnDate(dto.getReturnDate());
        entity.setPrice(dto.getPrice());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setAddress(dto.getAddress());
        entity.setStatus(dto.getStatus());
        entity.setIsReview(dto.getIsReview());

        return entity;
    }
}
