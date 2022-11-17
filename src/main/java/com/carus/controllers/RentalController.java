package com.carus.controllers;

import com.carus.dto.RentalDTO;
import com.carus.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping("/all")
    public ResponseEntity<List<RentalDTO>> findAll() {
        return ResponseEntity.ok(rentalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<RentalDTO> save(@RequestBody RentalDTO dto) {
        return ResponseEntity.ok(rentalService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        rentalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDTO> update(@RequestBody RentalDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(rentalService.update(dto, id));
    }
}
