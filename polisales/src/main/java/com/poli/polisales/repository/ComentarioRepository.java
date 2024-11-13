package com.poli.polisales.repository;

import com.poli.polisales.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    // Método personalizado para encontrar comentarios por publicación
    List<Comentario> findByPublicacionId(Long publicacionId);
}
