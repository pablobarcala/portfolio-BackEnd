package com.portfolio.pruebajwt.service;

import com.portfolio.pruebajwt.dto.Mensaje;
import com.portfolio.pruebajwt.repository.ExperienciaLaboralRepository;
import com.portfolio.pruebajwt.repository.PersonaRepository;
import com.portfolio.pruebajwt.entity.ExperienciaLaboral;
import com.portfolio.pruebajwt.entity.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienciaLaboralService{
    
    @Autowired
    ExperienciaLaboralRepository experienciaRepository;
    
    @Autowired
    PersonaRepository personaRepository;
    
    public List<ExperienciaLaboral> getExperiencias(int idPersona){
        return experienciaRepository.findByPersona(idPersona);
    }
    
    public Mensaje saveExperiencia(ExperienciaLaboral experienciaLaboral, int idPersona){
        Persona persona = personaRepository.findById(idPersona).get();
        
        experienciaLaboral.setPersona(persona);
        experienciaRepository.save(experienciaLaboral);
        
        return new Mensaje(experienciaLaboral.getPuesto() + " se creó correctamente.");
    }
    
    public ExperienciaLaboral findExperiencia(int id) {
        return experienciaRepository.findById(id).get();
    }
    
    public Mensaje deleteExperiencia(int id){
        experienciaRepository.deleteById(id);
        
        return new Mensaje("La experiencia se eliminó correctamente.");
    }
    
}
