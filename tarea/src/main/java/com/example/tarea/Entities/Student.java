package com.example.tarea.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStudents", nullable = false)
    private Integer idStudents;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "gpa", nullable = false)
    private float gpa;

    @Column(name = "facultad", nullable = false, length = 100)
    private String facultad;

    @Column(name = "creditosCompletados", nullable = false)
    private Integer creditosCompletados;

    public Integer getIdStudents() {
        return idStudents;
    }

    public void setIdStudents(Integer idStudents) {
        this.idStudents = idStudents;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public Integer getCreditosCompletados() {
        return creditosCompletados;
    }

    public void setCreditosCompletados(Integer creditosCompletados) {
        this.creditosCompletados = creditosCompletados;
    }
}
