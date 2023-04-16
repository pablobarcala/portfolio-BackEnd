package com.portfolio.pruebajwt.security.service;

import com.portfolio.pruebajwt.security.enums.RolNombre;
import com.portfolio.pruebajwt.security.repository.RolRepository;
import com.portfolio.pruebajwt.security.entity.Rol;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    
    @Autowired
    RolRepository rolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }
}
