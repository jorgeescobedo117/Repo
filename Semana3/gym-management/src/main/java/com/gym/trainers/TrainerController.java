package com.gym.trainers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {
    private final TrainerService service;

    public TrainerController(TrainerService service) { this.service = service; }

    @PostMapping
    public Trainer create(@RequestBody Trainer t) { return service.create(t); }

    @GetMapping
    public List<Trainer> list() { return service.list(); }
}
