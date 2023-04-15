package com.portfolio.pruebajwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portfolio.pruebajwt.entity.Proyecto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer>{
    @Query("select p from Proyecto p where p.persona.id = :id")
    List<Proyecto> findByPersona(@Param("id") int id);
    boolean existsByNombreProyecto(String nombreProyecto);
}
