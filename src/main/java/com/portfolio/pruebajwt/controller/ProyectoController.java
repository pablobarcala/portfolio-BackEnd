package com.portfolio.pruebajwt.controller;

import com.portfolio.pruebajwt.dto.Mensaje;
import com.portfolio.pruebajwt.entity.Persona;
import com.portfolio.pruebajwt.entity.Proyecto;
import com.portfolio.pruebajwt.service.PersonaService;
import com.portfolio.pruebajwt.service.ProyectoService;
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
@RequestMapping("/api/proyecto")
@CrossOrigin
public class ProyectoController {
    
    @Autowired
    ProyectoService proyectoService;
    
    @Autowired
    PersonaService personaService;
    
    @GetMapping("/lista/{id}")
    public ResponseEntity<List<Proyecto>> getProyectos(@PathVariable int id){
        return ResponseEntity.ok(proyectoService.getProyectos(id));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/{idPersona}")
    public ResponseEntity<Mensaje> saveProyecto(@PathVariable int idPersona,
                                                @RequestBody Proyecto proyecto){
        return ResponseEntity.ok(proyectoService.saveProyecto(proyecto, idPersona));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{idPersona}/{id}")
    @Transactional
    public Mensaje editProyecto(@PathVariable int idPersona,
                                @PathVariable int id,
                                @RequestBody Proyecto nuevoProyecto){
        Proyecto proyecto = proyectoService.findProyecto(id);
        Persona persona = personaService.findPersona(idPersona);
        
        proyecto.setNombreProyecto(nuevoProyecto.getNombreProyecto());
        proyecto.setFecha(nuevoProyecto.getFecha());
        proyecto.setDescripcion(nuevoProyecto.getDescripcion());
        proyecto.setImagen(nuevoProyecto.getImagen());
        proyecto.setLink(nuevoProyecto.getLink());
        proyecto.setPersona(persona);
        
        proyectoService.saveProyecto(proyecto, idPersona);
        return new Mensaje(proyecto.getNombreProyecto() + " se editó.");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> deleteProyecto(@PathVariable int id){
        return ResponseEntity.ok(proyectoService.deleteProyecto(id));
    }
}
