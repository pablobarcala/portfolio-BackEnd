package com.portfolio.pruebajwt.service;

import com.portfolio.pruebajwt.dto.Mensaje;
import com.portfolio.pruebajwt.entity.Persona;
import com.portfolio.pruebajwt.repository.PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    
    @Autowired
    PersonaRepository personaRepository;
    
    public Mensaje save(Persona persona){
        personaRepository.save(persona);
        return new Mensaje(persona.getNombre() + " " + persona.getApellido() + " ha sido creado");
    }
    
    public List<Persona> getPersonas(){
        return personaRepository.findAll();
    }
    
    public Persona findPersona(int id){
        return personaRepository.findById(id).get();
    }
}
