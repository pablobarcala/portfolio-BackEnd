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
public class ExperienciaLaboral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String puesto;
    private String nombre_empresa;
    private String imagen;
    private Date fecha_inicio;
    private Date fecha_fin;
    private boolean trabajo_actual;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "persona_id")
    @JsonIgnore
    private Persona persona;
    
    public ExperienciaLaboral() {
    }

    public ExperienciaLaboral(String puesto, String nombre_empresa, String imagen, Date fecha_inicio, Date fecha_fin, boolean trabajo_actual, String descripcion, Persona persona) {
        this.puesto = puesto;
        this.nombre_empresa = nombre_empresa;
        this.imagen = imagen;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.trabajo_actual = trabajo_actual;
        this.descripcion = descripcion;
        this.persona = persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
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

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
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

    public boolean isTrabajo_actual() {
        return trabajo_actual;
    }

    public void setTrabajo_actual(boolean trabajo_actual) {
        this.trabajo_actual = trabajo_actual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
