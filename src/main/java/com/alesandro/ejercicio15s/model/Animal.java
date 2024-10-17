package com.alesandro.ejercicio15s.model;

import java.sql.Blob;
import java.time.LocalDate;

/**
 * Clase Animal
 */
public class Animal {
    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private String sexo;
    private int edad;
    private int peso;
    private String observaciones;
    private LocalDate fecha_primera_consulta;
    private Blob foto;
}
