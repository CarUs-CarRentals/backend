package com.carus.controllers;

import com.carus.dto.CarDTO;
import com.carus.entities.CarEntity;
import com.carus.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<CarDTO>> findAll() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CarDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<CarDTO> save(@RequestBody CarDTO dto) {
        return ResponseEntity.ok(carService.save(dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<CarDTO>> carsByLoggedUser() {
        return ResponseEntity.ok(carService.getCarsByLoggedUser());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CarDTO> update(@RequestBody CarDTO dto) {
        return ResponseEntity.ok(carService.save(dto));
    }
}
