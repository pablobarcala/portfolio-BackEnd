package com.portfolio.pruebajwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portfolio.pruebajwt.entity.Tecnologia;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia, Integer>{
    @Query("select t from Tecnologia t where t.persona.id = :id")
    List<Tecnologia> findByPersona(@Param("id") int id);
    boolean existsByNombreTecnologia(String nombreTecnologia);
}
