package com.portfolio.pruebajwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portfolio.pruebajwt.entity.Proyecto;
import java.util.Optional;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer>{
    Optional<Proyecto> findByNombreProyecto(String nombreProyecto);
    boolean existsByNombreProyecto(String nombreProyecto);
}
