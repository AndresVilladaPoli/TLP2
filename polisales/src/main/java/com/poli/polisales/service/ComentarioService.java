package com.poli.polisales.service;

import com.poli.polisales.model.Comentario;
import com.poli.polisales.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> findAll() {
        return comentarioRepository.findAll();
    }

    public Optional<Comentario> findById(Long id) {
        return comentarioRepository.findById(id);
    }

    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public void deleteById(Long id) {
        comentarioRepository.deleteById(id);
    }

    // Buscar comentarios por ID de publicación
    public List<Comentario> findByPublicacionId(Long publicacionId) {
        return comentarioRepository.findByPublicacionId(publicacionId);
    }
}

