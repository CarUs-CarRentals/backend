package com.carus.services;

import com.carus.dto.LocationDTO;
import com.carus.entities.LocationEntity;
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
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Transactional(readOnly = true)
    public List<LocationDTO> findAll() {
        return locationRepository.findAll().stream().map(LocationDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LocationDTO findById(Long id) {
        return new LocationDTO(locationRepository.findById(id).orElseThrow(() -> {
            log.error("Entity with id {} not found", id);
            return new EntityNotFoundException("Entity with id ".concat(id.toString()).concat(" not found"));
        }));
    }

    @Transactional
    public LocationDTO save(LocationDTO dto) {
        LocationEntity entity = locationRepository.save(this.dtoToEntity(dto));
        return new LocationDTO(entity);
    }

    @Transactional
    public LocationDTO update(LocationDTO dto, Long id) {
        LocationEntity updated = this.dtoToEntity(dto);
        updated.setId(id);
        return new LocationDTO(locationRepository.save(updated));
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

    private LocationEntity dtoToEntity(LocationDTO dto) {
        LocationEntity entity = new LocationEntity();
        entity.setUser(userService.findEntityById(dto.getUser()));
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
