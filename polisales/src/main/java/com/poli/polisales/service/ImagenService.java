package com.poli.polisales.service;

import com.poli.polisales.model.Imagen;
import com.poli.polisales.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    public List<Imagen> findAll() {
        return imagenRepository.findAll();
    }

    public Optional<Imagen> findById(Long id) {
        return imagenRepository.findById(id);
    }

    public Imagen save(Imagen imagen) {
        return imagenRepository.save(imagen);
    }

    public void deleteById(Long id) {
        imagenRepository.deleteById(id);
    }

    // Buscar imágenes por ID de publicación
    public List<Imagen> findByPublicacionId(Long publicacionId) {
        return imagenRepository.findByPublicacionId(publicacionId);
    }
}