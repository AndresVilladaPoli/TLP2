package com.poli.polisales.service;

import com.poli.polisales.model.Rol;
import com.poli.polisales.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Optional<Rol> findById(Long id) {
        return rolRepository.findById(id);
    }

    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    public void deleteById(Long id) {
        rolRepository.deleteById(id);
    }

    // Buscar rol por nombre
    public Optional<Rol> findByNombre(String nombre) {
        return Optional.ofNullable(rolRepository.findByNombre(nombre));
    }
}
