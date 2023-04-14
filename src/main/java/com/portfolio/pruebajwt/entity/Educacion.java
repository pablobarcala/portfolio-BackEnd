package com.portfolio.pruebajwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Educacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String titulo;
    private String nombreInstituto;
    private String imagen;
    private Date fecha_inicio;
    private Date fecha_fin;
    private boolean en_progreso;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "persona_id")
    @JsonIgnore
    private Persona persona;

    public Educacion() {
    }

    public Educacion(String titulo, String nombreInstituto, String imagen, 
            Date fecha_inicio, Date fecha_fin, boolean en_progreso, 
            String descripcion, Persona persona) {
        this.titulo = titulo;
        this.nombreInstituto = nombreInstituto;
        this.imagen = imagen;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.en_progreso = en_progreso;
        this.descripcion = descripcion;
        this.persona = persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreInstituto() {
        return nombreInstituto;
    }

    public void setNombreInstituto(String nombreInstituto) {
        this.nombreInstituto = nombreInstituto;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public boolean isEn_progreso() {
        return en_progreso;
    }

    public void setEn_progreso(boolean en_progreso) {
        this.en_progreso = en_progreso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
