package com.poli.polisales.repository;

import com.poli.polisales.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    // Método personalizado para buscar rol por nombre
    Rol findByNombre(String nombre);
}

