package com.portfolio.pruebajwt.controller;

import com.portfolio.pruebajwt.dto.Mensaje;
import com.portfolio.pruebajwt.service.PersonaService;
import com.portfolio.pruebajwt.entity.Persona;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persona")
@CrossOrigin
public class PersonaController {
    
    @Autowired
    PersonaService personaService;
    
    @GetMapping("/traer")
    public ResponseEntity<List<Persona>> getPersonas(){
        return ResponseEntity.ok(personaService.getPersonas());
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Mensaje save(@Valid @RequestBody Persona persona){
        return personaService.save(persona);
    }
}
