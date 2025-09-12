package com.example.CRUD.rest;

import com.example.CRUD.model.Spartan;
import com.example.CRUD.Service.SpartanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spartans")
public class SpartanRest {

    @Autowired
    private SpartanService spartanService;

    @PostMapping
    public ResponseEntity<Spartan> createSpartan(@RequestBody Spartan spartan) {
        try {
            Spartan savedSpartan = spartanService.save(spartan);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSpartan);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Spartan>> getAllSpartans() {
        try {
            List<Spartan> spartans = spartanService.findAll();
            return ResponseEntity.ok(spartans);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Spartan> getSpartanById(@PathVariable String id) {
        try {
            Optional<Spartan> spartan = spartanService.findById(id);
            return spartan.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Spartan> updateSpartan(@PathVariable String id, @RequestBody Spartan spartan) {
        try {
            Spartan updatedSpartan = spartanService.update(id, spartan);
            return ResponseEntity.ok(updatedSpartan);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpartan(@PathVariable String id) {
        try {
            if (spartanService.findById(id).isPresent()) {
                spartanService.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
