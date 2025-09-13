package com.example.CRUD.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Spartan")
public class Spartan {

	@Id
    private String id;

    private String nombreCodigo; 

    @Indexed(unique = true)
    private String gamertag;     

    private String escuadra;     

    private int nivelExperiencia; 

    private LocalDateTime fechaAlistamiento;

    public Spartan() {
        this.fechaAlistamiento = LocalDateTime.now();
        this.nivelExperiencia = 1;
    }

    public Spartan(String nombreCodigo, String gamertag, String escuadra, int nivelExperiencia) {
        this.nombreCodigo = nombreCodigo;
        this.gamertag = gamertag;
        this.escuadra = escuadra;
        this.nivelExperiencia = nivelExperiencia;
        this.fechaAlistamiento = LocalDateTime.now();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCodigo() {
        return nombreCodigo;
    }

    public void setNombreCodigo(String nombreCodigo) {
        this.nombreCodigo = nombreCodigo;
    }

    public String getGamertag() {
        return gamertag;
    }

    public void setGamertag(String gamertag) {
        this.gamertag = gamertag;
    }

    public String getEscuadra() {
        return escuadra;
    }

    public void setEscuadra(String escuadra) {
        this.escuadra = escuadra;
    }

    public int getNivelExperiencia() {
        return nivelExperiencia;
    }

    public void setNivelExperiencia(int nivelExperiencia) {
        this.nivelExperiencia = nivelExperiencia;
    }

    public LocalDateTime getFechaAlistamiento() {
        return fechaAlistamiento;
    }

    public void setFechaAlistamiento(LocalDateTime fechaAlistamiento) {
        this.fechaAlistamiento = fechaAlistamiento;
    }
}
