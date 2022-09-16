package com.carus.services;

import com.carus.dto.CarDTO;
import com.carus.entities.CarEntity;
import com.carus.entities.UserEntity;
import com.carus.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public CarDTO create(CarEntity car) {
        return new CarDTO(carRepository.save(car));
    }
    @Transactional(readOnly = true)
    public List<CarDTO> findAll() {
        return carRepository.findAll().stream().map(entity -> new CarDTO(entity)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CarDTO findById(Long id) {
        return new CarDTO(carRepository.findById(id).orElse(null));
    }

    @Transactional
    public CarDTO save(CarDTO dto) {
        UserEntity user = userService.findById(dto.getUser());
        CarEntity entity = carRepository.save(this.DTOToEntity(dto));
        return new CarDTO(entity);
    }

    @Transactional
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    private CarEntity DTOToEntity(CarDTO dto) {
        CarEntity entity = new CarEntity();
        entity.setBrand(dto.getBrand());
        entity.setGearShift(dto.getGearShift());
        entity.setFuel(dto.getFuel());
        entity.setCategory(dto.getCategory());
        entity.setDoors(dto.getDoors());
        entity.setModel(dto.getModel());
        entity.setPlate(dto.getPlate());
        entity.setUser(userService.findById(dto.getUser()));
        entity.setSeats(dto.getSeats());
        entity.setYear(dto.getYear());
        entity.setPickupLocation(dto.getPickupLocation());
        entity.setReturnLocation(dto.getReturnLocation());

        return entity;
    }
}
