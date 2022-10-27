package com.carus.controllers;

import com.carus.dto.AddressDTO;
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

    @PostMapping(value = "/create")
    public ResponseEntity<AddressDTO> save(@RequestBody AddressDTO dto) { return ResponseEntity.ok(addressService.save(dto));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<AddressDTO>> findAll() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id) {return ResponseEntity.ok(addressService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> update(@RequestBody AddressDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(addressService.update(dto, id));
    }
}
