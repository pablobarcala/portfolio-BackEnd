package com.portfolio.pruebajwt.security.repository;

import com.portfolio.pruebajwt.security.enums.RolNombre;
import com.portfolio.pruebajwt.security.entity.Rol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
