package com.carus.controllers;

import com.carus.dto.CarDTO;
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

    @GetMapping
    public ResponseEntity<List<CarDTO>> findAll() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping(path = "/{id}") // param na rota
    public ResponseEntity<CarDTO> findById(@PathVariable Long id) { // parametro na url
        return ResponseEntity.ok(carService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CarDTO> save(@RequestBody CarDTO dto) {
        return ResponseEntity.ok().body(carService.save(dto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ?nome=Pedro  @RequestParam
}
