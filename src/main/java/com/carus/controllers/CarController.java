package com.carus.controllers;

import com.carus.dto.CarDTO;
import com.carus.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/all")
    public ResponseEntity<List<CarDTO>> findAll() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<CarDTO> save(@RequestBody CarDTO dto) {
        return ResponseEntity.ok(carService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> carsByLoggedUser() {
        return ResponseEntity.ok(carService.getCarsByLoggedUser());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> update(@RequestBody CarDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(carService.update(dto, id));
    }
}
