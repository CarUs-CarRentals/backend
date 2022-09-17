package com.carus.controllers;

import com.carus.dto.RateUserDTO;
import com.carus.services.RateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/rate-user")
public class RateUserController {

    @Autowired
    private RateUserService rateUserService;

    @GetMapping
    public ResponseEntity<List<RateUserDTO>> findAll() {
        return ResponseEntity.ok(rateUserService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RateUserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(rateUserService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RateUserDTO> save(@RequestBody RateUserDTO dto) {
        return ResponseEntity.ok(rateUserService.save(dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        rateUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
