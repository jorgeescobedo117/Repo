package com.example.CRUD.Service;

import com.example.CRUD.model.Spartan;
import java.util.List;
import java.util.Optional;

public interface SpartanService {
    Spartan save(Spartan spartan);
    List<Spartan> findAll();
    Optional<Spartan> findById(String id);
    Spartan update(String id, Spartan spartan);
    void deleteById(String id);
}
