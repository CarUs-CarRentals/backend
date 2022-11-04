package com.carus.controllers;

import com.carus.dto.LocationDTO;
import com.carus.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<LocationDTO>> findAll() {
        return ResponseEntity.ok(locationService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LocationDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.findById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<LocationDTO> save(@RequestBody LocationDTO dto) {
        return ResponseEntity.ok(locationService.save(dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        locationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LocationDTO> update(@RequestBody LocationDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(locationService.update(dto, id));
    }
}
