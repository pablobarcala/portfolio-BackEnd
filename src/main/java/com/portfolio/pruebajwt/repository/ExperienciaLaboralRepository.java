package com.portfolio.pruebajwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portfolio.pruebajwt.entity.ExperienciaLaboral;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ExperienciaLaboralRepository extends JpaRepository<ExperienciaLaboral, Integer>{
    @Query("select e from ExperienciaLaboral e where e.persona.id = :id")
    List<ExperienciaLaboral> findByPersona(@Param("id") int id);
    boolean existsByPuesto(String puesto);
}
