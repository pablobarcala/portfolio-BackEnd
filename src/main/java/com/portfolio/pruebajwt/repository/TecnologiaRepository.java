package com.portfolio.pruebajwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portfolio.pruebajwt.entity.Tecnologia;
import java.util.Optional;

@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia, Integer>{
    Optional<Tecnologia> findByNombreTecnologia(String nombreTecnologia);
    boolean existsByNombreTecnologia(String nombreTecnologia);
}
