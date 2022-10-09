package com.carus.controllers;

import com.carus.dto.CnhDTO;
import com.carus.services.CnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cnh")
public class CnhController {

    @Autowired
    private CnhService cnhService;

    @GetMapping
    public ResponseEntity<List<CnhDTO>> findAll() {
        return ResponseEntity.ok(cnhService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CnhDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cnhService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CnhDTO> save(@RequestBody CnhDTO dto) {
        return ResponseEntity.ok(cnhService.save(dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        cnhService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CnhDTO> update(@RequestBody CnhDTO dto) {
        return ResponseEntity.ok(cnhService.update(dto));
    }
}
