package com.portfolio.pruebajwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    private String titulo;
    private String imagen;
    @NotNull
    @Lob
    private String descripcion;
    
    // Lista de educaciones de la persona
    @OneToMany(mappedBy = "persona")
    @JsonIgnore
    private List<Educacion> educaciones;
    
    // Lista de experiencias laborales de la persona
    @OneToMany(mappedBy = "persona")
    @JsonIgnore
    private List<ExperienciaLaboral> experienciasLaborales;
    
    // Lista de proyectos de la persona
    @OneToMany(mappedBy = "persona")
    @JsonIgnore
    private List<Proyecto> proyectos;
    
    // Lista de tecnologias de la persona
    @OneToMany(mappedBy = "persona")
    @JsonIgnore
    private List<Tecnologia> tecnologias;

    public Persona(String nombre, String apellido, String titulo, String imagen, String descripcion,
            List<Educacion> educaciones, List<ExperienciaLaboral> experienciasLaborales, 
            List<Proyecto> proyectos, List<Tecnologia> tecnologias) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.titulo = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.educaciones = educaciones;
        this.experienciasLaborales = experienciasLaborales;
        this.proyectos = proyectos;
        this.tecnologias = tecnologias;
    }

    public Persona() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public List<Tecnologia> getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(List<Tecnologia> tecnologias) {
        this.tecnologias = tecnologias;
    }

    public List<ExperienciaLaboral> getExperienciasLaborales() {
        return experienciasLaborales;
    }

    public void setExperienciasLaborales(List<ExperienciaLaboral> experienciasLaborales) {
        this.experienciasLaborales = experienciasLaborales;
    }

    public List<Educacion> getEducaciones() {
        return educaciones;
    }

    public void setEducaciones(List<Educacion> educaciones) {
        this.educaciones = educaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
