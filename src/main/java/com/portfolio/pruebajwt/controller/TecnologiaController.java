package com.portfolio.pruebajwt.controller;

import com.portfolio.pruebajwt.dto.Mensaje;
import com.portfolio.pruebajwt.entity.Persona;
import com.portfolio.pruebajwt.entity.Tecnologia;
import com.portfolio.pruebajwt.service.PersonaService;
import com.portfolio.pruebajwt.service.TecnologiaService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tecnologia")
public class TecnologiaController {
    
    @Autowired
    TecnologiaService tecnologiaService;
    
    @Autowired
    PersonaService personaService;
    
    @GetMapping("/lista/{id}")
    public ResponseEntity<List<Tecnologia>> getTecnologias(@PathVariable int id){
        return ResponseEntity.ok(tecnologiaService.getTecnologias(id));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/{idPersona}")
    public ResponseEntity<Mensaje> saveTecnologia(@PathVariable int idPersona,
                                                @RequestBody Tecnologia tecnologia){
        return ResponseEntity.ok(tecnologiaService.saveTecnologia(tecnologia, idPersona));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{idPersona}/{id}")
    @Transactional
    public Mensaje editTecnologia(@PathVariable int idPersona,
                                @PathVariable int id,
                                @RequestBody Tecnologia nuevaTecnologia){
        Tecnologia tecnologia = tecnologiaService.findTecnologia(id);
        Persona persona = personaService.findPersona(idPersona);
        
        tecnologia.setNombreTecnologia(nuevaTecnologia.getNombreTecnologia());
        tecnologia.setPorcentaje(nuevaTecnologia.getPorcentaje());
        tecnologia.setPersona(persona);
        
        tecnologiaService.saveTecnologia(tecnologia, idPersona);
        return new Mensaje(tecnologia.getNombreTecnologia()+ " se editó.");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> deleteTecnologia(@PathVariable int id){
        return ResponseEntity.ok(tecnologiaService.deleteTecnologia(id));
    }
}
