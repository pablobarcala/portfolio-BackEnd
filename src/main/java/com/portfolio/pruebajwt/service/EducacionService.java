package com.portfolio.pruebajwt.service;

import com.portfolio.pruebajwt.dto.Mensaje;
import com.portfolio.pruebajwt.entity.Educacion;
import com.portfolio.pruebajwt.entity.Persona;
import com.portfolio.pruebajwt.repository.EducacionRepository;
import com.portfolio.pruebajwt.repository.PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducacionService {
    
    @Autowired
    EducacionRepository educacionRepository;
    
    @Autowired
    PersonaRepository personaRepository;
    
    public List<Educacion> getEducaciones(int idPersona){
        return educacionRepository.findByPersona(idPersona);
    }
    
    public Mensaje saveEducacion(Educacion educacion, int idPersona){
        if(educacionRepository.existsByTitulo(educacion.getTitulo())){
            return new Mensaje("ese titulo ya existe");
        }
        
        Persona persona = personaRepository.findById(idPersona).get();
        educacion.setPersona(persona);
        
        educacionRepository.save(educacion);
        return new Mensaje(educacion.getTitulo() + " ha sido creado");
    }
    
    public Educacion findEducacion(int id){
        return educacionRepository.findById(id).get();
    }
    
    public Mensaje deleteEducacion(int id){
        educacionRepository.deleteById(id);
        
        return new Mensaje("La educación se eliminó correctamente");
    }
}
