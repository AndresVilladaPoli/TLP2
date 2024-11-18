package com.poli.polisales.repository;

import com.poli.polisales.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    
    List<Publicacion> findByCategoriaId(Long categoriaId);
}

