package com.portfolio.pruebajwt.controller;

import com.portfolio.pruebajwt.dto.Mensaje;
import com.portfolio.pruebajwt.service.EducacionService;
import com.portfolio.pruebajwt.entity.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.portfolio.pruebajwt.entity.Educacion;
import com.portfolio.pruebajwt.service.PersonaService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/educacion")
@CrossOrigin
public class EducacionController {
    
    @Autowired
    EducacionService educacionService;
    
    @Autowired
    PersonaService personaService;
    
    @GetMapping("/lista/{id}")
    public ResponseEntity<List<Educacion>> listEducaciones(@PathVariable int id){
        return ResponseEntity.ok(educacionService.getEducaciones(id));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/{idPersona}")
    public ResponseEntity<Mensaje> saveEducacion(@RequestBody Educacion educacion, @PathVariable int idPersona){
        return ResponseEntity.ok(educacionService.saveEducacion(educacion, idPersona));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @PutMapping("/edit/{idPersona}/{id}")
    public Mensaje editEducacion(@PathVariable int id,
                                    @PathVariable int idPersona,
                                    @RequestBody Educacion newEducacion){
        Educacion educacion = educacionService.findEducacion(id);
        Persona persona = personaService.findPersona(idPersona);
        
        educacion.setTitulo(newEducacion.getTitulo());
        educacion.setNombreInstituto(newEducacion.getNombreInstituto());
        educacion.setDescripcion(newEducacion.getDescripcion());
        educacion.setFecha_inicio(newEducacion.getFecha_inicio());
        educacion.setFecha_fin(newEducacion.getFecha_fin());
        educacion.setImagen(newEducacion.getImagen());
        educacion.setEn_progreso(newEducacion.isEn_progreso());
        educacion.setPersona(persona);
        
        educacionService.saveEducacion(educacion, idPersona);
        return new Mensaje(educacion.getTitulo() + " se editó");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> deleteEducacion(@PathVariable int id){
        return ResponseEntity.ok(educacionService.deleteEducacion(id));
    }
    
}
