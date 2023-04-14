package com.portfolio.pruebajwt.controller;

import com.portfolio.pruebajwt.dto.Mensaje;
import com.portfolio.pruebajwt.service.ExperienciaLaboralService;
import com.portfolio.pruebajwt.entity.ExperienciaLaboral;
import com.portfolio.pruebajwt.entity.Persona;
import com.portfolio.pruebajwt.service.PersonaService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/experiencia")
@CrossOrigin
public class ExperienciaLaboralController {
    
    @Autowired
    ExperienciaLaboralService experienciaService;
    
    @Autowired
    PersonaService personaService;
    
    @GetMapping("/lista/{idPersona}")
    public ResponseEntity<List<ExperienciaLaboral>> getExperiencias(@PathVariable int idPersona){
        return ResponseEntity.ok(experienciaService.getExperiencias(idPersona));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/{idPersona}")
    public ResponseEntity<Mensaje> saveExperiencia(@PathVariable int idPersona, @RequestBody ExperienciaLaboral experiencia){
        return ResponseEntity.ok(experienciaService.saveExperiencia(experiencia, idPersona));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{idPersona}/{id}")
    @Transactional
    public Mensaje editExperiencia(@PathVariable int idPersona,
                                    @PathVariable int id,
                                    @RequestBody ExperienciaLaboral newExperiencia){
        ExperienciaLaboral experiencia = experienciaService.findExperiencia(id);
        Persona persona = personaService.findPersona(idPersona);
        
        experiencia.setPuesto(newExperiencia.getPuesto());
        experiencia.setNombre_empresa(newExperiencia.getNombre_empresa());
        experiencia.setImagen(newExperiencia.getImagen());
        experiencia.setFecha_inicio(newExperiencia.getFecha_inicio());
        experiencia.setFecha_fin(newExperiencia.getFecha_fin());
        experiencia.setTrabajo_actual(newExperiencia.isTrabajo_actual());
        experiencia.setDescripcion(newExperiencia.getDescripcion());
        experiencia.setPersona(persona);
        
        experienciaService.saveExperiencia(experiencia, idPersona);
        
        return new Mensaje(experiencia.getPuesto() + " se editó.");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> deleteExperiencia(@PathVariable int id){
        return ResponseEntity.ok(experienciaService.deleteExperiencia(id));
    }
}
