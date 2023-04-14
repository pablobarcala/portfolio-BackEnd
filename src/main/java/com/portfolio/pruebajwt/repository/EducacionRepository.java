package com.portfolio.pruebajwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portfolio.pruebajwt.entity.Educacion;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface EducacionRepository extends JpaRepository<Educacion, Integer>{
    @Query("select e from Educacion e where e.persona.id = :id")
    List<Educacion> findByPersona(@Param("id") int id);
    boolean existsByTitulo(String titulo);
}
