package com.carus.services;

import com.carus.dto.CarDTO;
import com.carus.entities.CarEntity;
import com.carus.repositories.CarRepository;
import com.carus.services.exceptions.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public List<CarDTO> findAll() {
        return carRepository.findAll().stream().map(CarDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CarDTO findById(Long id) {
        return new CarDTO(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public CarEntity findEntityById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> {
            log.error("Entity with id {} not found", id);
            return new EntityNotFoundException("Entity with id ".concat(id.toString()).concat(" not found"));
        });
    }

    @Transactional
    public CarDTO save(CarDTO dto) {
        CarEntity entity = carRepository.save(this.dtoToEntity(dto));
        return new CarDTO(entity);
    }

    @Transactional
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CarDTO> getCarsByLoggedUser() {
        List<CarEntity> cars = carRepository.findCarsByUserId(userService.getLoggedUser().getId());
        return cars.stream().map(CarDTO::new).collect(Collectors.toList());
    }

    private CarEntity dtoToEntity(CarDTO dto) {
        CarEntity entity = new CarEntity();
        entity.setBrand(dto.getBrand());
        entity.setGearShift(dto.getGearShift());
        entity.setFuel(dto.getFuel());
        entity.setCategory(dto.getCategory());
        entity.setDoors(dto.getDoors());
        entity.setModel(dto.getModel());
        entity.setPlate(dto.getPlate());
        entity.setUser(userService.findEntityById(dto.getUser()));
        entity.setSeats(dto.getSeats());
        entity.setYear(dto.getYear());
        entity.setTrunk(dto.getTrunk());
        entity.setPickupLocation(dto.getPickupLocation());
        entity.setReturnLocation(dto.getReturnLocation());

        return entity;
    }
}
