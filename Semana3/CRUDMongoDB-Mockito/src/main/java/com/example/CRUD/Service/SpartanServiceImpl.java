package com.example.CRUD.Service;

import com.example.CRUD.model.Spartan;
import com.example.CRUD.repository.SpartanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SpartanServiceImpl implements SpartanService {

    @Autowired
    private SpartanRepository spartanRepository;

    @Override
    public Spartan save(Spartan spartan) {
        if (spartan.getFechaAlistamiento() == null) {
            spartan.setFechaAlistamiento(LocalDateTime.now());
        }
        if (spartan.getNivelExperiencia() == 0) {
            spartan.setNivelExperiencia(1);
        }
        return spartanRepository.save(spartan);
    }

    @Override
    public List<Spartan> findAll() {
        return spartanRepository.findAll();
    }

    @Override
    public Optional<Spartan> findById(String id) {
        return spartanRepository.findById(id);
    }

    @Override
    public Spartan update(String id, Spartan spartan) {
        Optional<Spartan> existingSpartan = spartanRepository.findById(id);
        if (existingSpartan.isPresent()) {
            Spartan spartanToUpdate = existingSpartan.get();
            spartanToUpdate.setNombreCodigo(spartan.getNombreCodigo());
            spartanToUpdate.setGamertag(spartan.getGamertag());
            spartanToUpdate.setEscuadra(spartan.getEscuadra());
            spartanToUpdate.setNivelExperiencia(spartan.getNivelExperiencia());
            return spartanRepository.save(spartanToUpdate);
        }
        throw new RuntimeException("Spartan not found with id: " + id);
    }

    @Override
    public void deleteById(String id) {
        spartanRepository.deleteById(id);
    }
}
