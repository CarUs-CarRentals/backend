package com.carus.controllers;

import com.carus.dto.AddressDTO;
import com.carus.entities.AddressEntity;
import com.carus.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(path = "/create")
    public ResponseEntity<AddressDTO> create(@RequestBody AddressEntity newAddress) {
        return ResponseEntity.ok(addressService.create(newAddress));
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAll() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AddressDTO> save(@RequestBody AddressDTO dto) {
        return ResponseEntity.ok(addressService.save(dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AddressDTO> update(@RequestBody AddressDTO dto) {
        return ResponseEntity.ok(addressService.save(dto));
    }
}
