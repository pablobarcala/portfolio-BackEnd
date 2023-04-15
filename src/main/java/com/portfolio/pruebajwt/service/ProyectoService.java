package com.portfolio.pruebajwt.service;

import com.portfolio.pruebajwt.dto.Mensaje;
import com.portfolio.pruebajwt.repository.PersonaRepository;
import com.portfolio.pruebajwt.repository.ProyectoRepository;
import com.portfolio.pruebajwt.entity.Proyecto;
import com.portfolio.pruebajwt.entity.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProyectoService {
    
    @Autowired
    ProyectoRepository proyectoRepository;
    
    @Autowired
    PersonaRepository personaRepository;
    
    public List<Proyecto> getProyectos(int idPersona){
        return proyectoRepository.findByPersona(idPersona);
    }
    
    public Mensaje saveProyecto(Proyecto proyecto, int idPersona){
        if(proyectoRepository.existsByNombreProyecto(proyecto.getNombreProyecto())){
            new Mensaje("El proyecto ya existe");
        }
        
        Persona persona = personaRepository.findById(idPersona).get();
        proyecto.setPersona(persona);
        proyectoRepository.save(proyecto);
        
        return new Mensaje(proyecto.getNombreProyecto() + " se creó.");
    }
    
    public Proyecto findProyecto(int id){
        return proyectoRepository.findById(id).get();
    }
    
    public Mensaje deleteProyecto(int id){
        proyectoRepository.deleteById(id);
        return new Mensaje("El proyecto se eliminó.");
    }
}
