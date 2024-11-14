package com.poli.polisales.controller;

import com.poli.polisales.model.Publicacion;
import com.poli.polisales.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public List<Publicacion> getAllPublicaciones() {
        return publicacionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> getPublicacionById(@PathVariable Long id) {
        Optional<Publicacion> publicacion = publicacionService.findById(id);
        return publicacion.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Publicacion> createPublicacion(@RequestBody Publicacion publicacion) {
        Publicacion savedPublicacion = publicacionService.save(publicacion);
        return new ResponseEntity<>(savedPublicacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publicacion> updatePublicacion(@PathVariable Long id, @RequestBody Publicacion publicacion) {
        if (!publicacionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        publicacion.setId(id);
        Publicacion updatedPublicacion = publicacionService.save(publicacion);
        return ResponseEntity.ok(updatedPublicacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublicacion(@PathVariable Long id) {
        if (!publicacionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        publicacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

