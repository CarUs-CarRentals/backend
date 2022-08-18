package com.carus.services;

import com.carus.dto.CarDTO;
import com.carus.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<CarDTO> findAll() {
        return carRepository.findAll().stream().map(entity -> new CarDTO(entity)).toList();
    }
}
