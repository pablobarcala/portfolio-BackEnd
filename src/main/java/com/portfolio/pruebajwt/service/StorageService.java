package com.portfolio.pruebajwt.service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class StorageService {
    private final Path rootLocation = Paths.get("upload-dir");
    
    public Resource cargarArchivo(String nombreArchivo) throws MalformedURLException {
        try {
            Path archivo = rootLocation.resolve(nombreArchivo);
            Resource recurso = new UrlResource(archivo.toUri());
            if(recurso.exists() || recurso.isReadable()) {
                return recurso;
            } else {
                throw new MalformedURLException();
            }
        } catch (MalformedURLException e) {
            throw new MalformedURLException();
        }
    }
}
