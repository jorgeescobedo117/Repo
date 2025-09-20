package com.example.CRUD.Service;

import com.example.CRUD.model.Spartan;
import com.example.CRUD.repository.SpartanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpartanServiceImplTest {

    @Mock
    private SpartanRepository spartanRepository;

    @InjectMocks
    private SpartanServiceImpl spartanService;

    private Spartan spartan;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        spartan = new Spartan();
        spartan.setId("1");
        spartan.setNombreCodigo("Spartan-117");
        spartan.setGamertag("MasterChief");
        spartan.setEscuadra("Blue Team");
        spartan.setNivelExperiencia(10);
        spartan.setFechaAlistamiento(LocalDateTime.now());
    }

    @Test
    void testSaveSpartan() {
        when(spartanRepository.save(any(Spartan.class))).thenReturn(spartan);
        Spartan saved = spartanService.save(spartan);
        assertNotNull(saved);
        assertEquals("MasterChief", saved.getGamertag());
        verify(spartanRepository, times(1)).save(any(Spartan.class));
    }

    @Test
    void testFindAll() {
        when(spartanRepository.findAll()).thenReturn(Arrays.asList(spartan));
        List<Spartan> spartans = spartanService.findAll();
        assertEquals(1, spartans.size());
        verify(spartanRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(spartanRepository.findById("1")).thenReturn(Optional.of(spartan));
        Optional<Spartan> found = spartanService.findById("1");
        assertTrue(found.isPresent());
        verify(spartanRepository, times(1)).findById("1");
    }

    @Test
    void testUpdateSpartan() {
        when(spartanRepository.findById("1")).thenReturn(Optional.of(spartan));
        when(spartanRepository.save(any(Spartan.class))).thenReturn(spartan);

        Spartan updated = new Spartan();
        updated.setNombreCodigo("Spartan-999");
        updated.setGamertag("NewChief");
        updated.setEscuadra("Red Team");
        updated.setNivelExperiencia(20);

        Spartan result = spartanService.update("1", updated);

        assertEquals("NewChief", result.getGamertag());
        assertEquals(20, result.getNivelExperiencia());
    }

    @Test
    void testDeleteById() {
        doNothing().when(spartanRepository).deleteById("1");
        spartanService.deleteById("1");
        verify(spartanRepository, times(1)).deleteById("1");
    }
}
