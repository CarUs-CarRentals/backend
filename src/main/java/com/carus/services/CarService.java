package com.carus.services;

import com.carus.dto.CarDTO;
import com.carus.entities.CarEntity;
import com.carus.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public List<CarDTO> findAll() {
        return carRepository.findAll().stream().map(entity -> new CarDTO(entity)).toList();
    }

    @Transactional(readOnly = true)
    public CarDTO findById(Long id) {
        return new CarDTO(carRepository.findById(id).orElse(null));
    }

    @Transactional
    public CarDTO save(CarDTO dto) {
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
        entity.setColor(dto.getColor());
        entity.setDoors(dto.getDoors());
        entity.setModel(dto.getModel());
        entity.setPlate(dto.getPlate());
        entity.setUser(userService.findById(dto.getUser().getId()));
        entity.setSeats(dto.getSeats());
        entity.setTrunk(dto.getTrunk());
        entity.setYear(dto.getYear());

        return entity;
    }
}
