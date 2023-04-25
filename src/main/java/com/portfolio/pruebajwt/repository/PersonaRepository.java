package com.portfolio.pruebajwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portfolio.pruebajwt.entity.Persona;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer>{
    Optional<Persona> findByNombre(String nombre);
}
