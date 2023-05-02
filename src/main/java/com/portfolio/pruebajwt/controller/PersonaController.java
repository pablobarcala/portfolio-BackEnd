package com.portfolio.pruebajwt.controller;

import com.portfolio.pruebajwt.dto.Mensaje;
import com.portfolio.pruebajwt.service.PersonaService;
import com.portfolio.pruebajwt.entity.Persona;
import com.portfolio.pruebajwt.service.StorageService;
import com.portfolio.pruebajwt.util.FileUploadUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/persona")
@CrossOrigin 
public class PersonaController {
    
    @Autowired
    PersonaService personaService;
    
    @Autowired
    StorageService storageService;
    
    @GetMapping("/traer")
    public ResponseEntity<List<Persona>> getPersonas(){
        return ResponseEntity.ok(personaService.getPersonas());
    }
    
    @GetMapping("/{id}/imagen")
    public ResponseEntity<Resource> obtenerImagenPersona(@PathVariable int id) throws MalformedURLException {
        String nombreImagen = personaService.obtenerNombreImagen(id);
        Resource resource = storageService.cargarArchivo(nombreImagen);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, 
                "attachment: filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Mensaje> save(@Valid @RequestBody Persona persona){
        return ResponseEntity.ok(personaService.save(persona));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")
    @Transactional
    public Mensaje editPersona(@PathVariable int id,
                                @RequestBody Persona nuevaPersona,
                                @RequestParam MultipartFile file) throws IOException {
        Persona persona = personaService.findPersona(id);
        
        // Guarda la imagen en una carpeta del servidor
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "src/main/resources/static/images/profile/";
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        
        persona.setNombre(nuevaPersona.getNombre());
        persona.setApellido(nuevaPersona.getApellido());
        persona.setImagen(fileName);
        persona.setTitulo(nuevaPersona.getTitulo());
        persona.setDescripcion(nuevaPersona.getDescripcion());
        
        personaService.save(persona);
        
        return new Mensaje(persona.getNombre() + " se editó.");
    }
}
