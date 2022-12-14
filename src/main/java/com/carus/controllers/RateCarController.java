package com.carus.controllers;

import com.carus.dto.RateCarDTO;
import com.carus.services.RateCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rate-car")
public class RateCarController {

    @Autowired
    private RateCarService rateCarService;

    @GetMapping
    public ResponseEntity<List<RateCarDTO>> findAll() {
        return ResponseEntity.ok(rateCarService.findAll());
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<RateCarDTO>> findAllByCarId(@PathVariable Long carId) {
        return ResponseEntity.ok(rateCarService.findAllByCarId(carId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<RateCarDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(rateCarService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<RateCarDTO> save(@RequestBody RateCarDTO dto) {
        return ResponseEntity.ok(rateCarService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        rateCarService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{uuid}")
    public ResponseEntity<List<RateCarDTO>> findAllByUserUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(rateCarService.findAllByUserUuid(uuid));
    }
}
