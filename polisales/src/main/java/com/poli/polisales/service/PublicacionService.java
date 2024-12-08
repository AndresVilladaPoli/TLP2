package com.poli.polisales.service;

import com.poli.polisales.model.Publicacion;
import com.poli.polisales.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    public List<Publicacion> findAll() {
        return publicacionRepository.findAll();
    }

    public Optional<Publicacion> findById(Long id) {
        return publicacionRepository.findById(id);
    }

    public Publicacion save(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    public void deleteById(Long id) {
        publicacionRepository.deleteById(id);
    }

}
