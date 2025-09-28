package com.gym.trainers;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    private final TrainerRepository repo;

    public TrainerService(TrainerRepository repo) { this.repo = repo; }

    public Trainer create(Trainer t) { return repo.save(t); }

    public List<Trainer> list() { return repo.findAll(); }
}
