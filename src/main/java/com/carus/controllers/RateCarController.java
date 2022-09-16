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

    @GetMapping(path = "/{id}")
    public ResponseEntity<RateCarDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(rateCarService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RateCarDTO> save(@RequestBody RateCarDTO dto) {
        return ResponseEntity.ok(rateCarService.save(dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        rateCarService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
