package com.alesandro.ejercicio15s.model;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Objects;

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

    /**
     * Constructor con parámetros de animales
     *
     * @param id del animal
     * @param nombre del animal
     * @param especie del animal
     * @param raza del animal
     * @param sexo del animal
     * @param edad del animal
     * @param peso del animal
     * @param observaciones del animal
     * @param fecha_primera_consulta del animal
     * @param foto del animal
     */
    public Animal(int id, String nombre, String especie, String raza, String sexo, int edad, int peso, String observaciones, LocalDate fecha_primera_consulta, Blob foto) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.edad = edad;
        this.peso = peso;
        this.observaciones = observaciones;
        this.fecha_primera_consulta = fecha_primera_consulta;
        this.foto = foto;
    }

    /**
     * Constructor vacío de animales
     */
    public Animal() {}

    /**
     * Getter para el id del animal
     *
     * @return id del animal
     */
    public int getId() {
        return id;
    }

    /**
     * Setter para el id del animal
     *
     * @param id nuevo id del animal
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter para el nombre del animal
     *
     * @return nombre del animal
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter para el nombre del animal
     *
     * @param nombre nuevo nombre del animal
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter para la especie del animal
     *
     * @return especie del animal
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * Setter para la especie del animal
     *
     * @param especie nueva especie del animal
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     * Getter para la raza del animal
     *
     * @return raza del animal
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Setter para la del animal
     *
     * @param raza nueva raza del animal
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     * Getter para el sexo del animal
     *
     * @return sexo del animal
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Setter para el sexo del animal
     *
     * @param sexo nuevo sexo del animal
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Getter para la edad del animal
     *
     * @return edad del animal
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Setter para la edad del animal
     *
     * @param edad nueva edad del animal
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Getter para el peso del animal
     *
     * @return peso del animal
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Setter para el peso del animal
     *
     * @param peso nuevo peso del animal
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * Getter para observaciones del animal
     *
     * @return observaciones del animal
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Setter para ovservaciones del animal
     *
     * @param observaciones nuevas observaciones del animal
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Getter para la fecha de la primera consulta del animal
     *
     * @return fecha de la primera consulta del animal
     */
    public LocalDate getFecha_primera_consulta() {
        return fecha_primera_consulta;
    }

    /**
     * Setter para la fecha de la primera consulta del animal
     *
     * @param fecha_primera_consulta nueva fecha de la primera consulta del animal
     */
    public void setFecha_primera_consulta(LocalDate fecha_primera_consulta) {
        this.fecha_primera_consulta = fecha_primera_consulta;
    }

    /**
     * Getter para la foto del animal
     *
     * @return foto del animal
     */
    public Blob getFoto() {
        return foto;
    }

    /**
     * Setter para la foto del animal
     *
     * @param foto nueva foto del animal
     */
    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
