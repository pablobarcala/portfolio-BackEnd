package com.portfolio.pruebajwt.service;

import com.portfolio.pruebajwt.dto.Mensaje;
import com.portfolio.pruebajwt.entity.Tecnologia;
import com.portfolio.pruebajwt.entity.Persona;
import com.portfolio.pruebajwt.repository.PersonaRepository;
import com.portfolio.pruebajwt.repository.TecnologiaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnologiaService {
    
    @Autowired
    TecnologiaRepository tecnologiaRepository;
    
    @Autowired
    PersonaRepository personaRepository;
    
    public List<Tecnologia> getTecnologias(int idPersona){
        return tecnologiaRepository.findByPersona(idPersona);
    }
    
    public Mensaje saveTecnologia(Tecnologia tecnologia, int idPersona){
        if(tecnologiaRepository.existsByNombreTecnologia(tecnologia.getNombreTecnologia())){
            new Mensaje("La tecnología ya existe");
        }
        
        Persona persona = personaRepository.findById(idPersona).get();
        tecnologia.setPersona(persona);
        tecnologiaRepository.save(tecnologia);
        
        return new Mensaje(tecnologia.getNombreTecnologia()+ " se creó.");
    }
    
    public Tecnologia findTecnologia(int id){
        return tecnologiaRepository.findById(id).get();
    }
    
    public Mensaje deleteTecnologia(int id){
        tecnologiaRepository.deleteById(id);
        return new Mensaje("La tecnología se eliminó.");
    }
}
