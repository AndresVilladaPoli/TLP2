package com.poli.polisales.controller;

import com.poli.polisales.model.Publicacion;
import com.poli.polisales.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    // Obtener todas las publicaciones
    @GetMapping
    public List<Publicacion> getAllPublicaciones() {
        return publicacionService.findAll();
    }

    // Obtener una publicación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> getPublicacionById(@PathVariable Long id) {
        Optional<Publicacion> publicacion = publicacionService.findById(id);
        return publicacion.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva publicación
    @PostMapping
    public ResponseEntity<Publicacion> createPublicacion(@RequestBody Publicacion publicacion) {
        // Asignar la fecha de publicación automáticamente
        publicacion.setFechaPublicacion(LocalDateTime.now());

        // Validar que el título, el contenido y la categoría estén presentes
        if (publicacion.getTitulo() == null || publicacion.getTitulo().isEmpty() ||
            publicacion.getContenido() == null || publicacion.getContenido().isEmpty() ||
            publicacion.getCategoria() == null || publicacion.getCategoria().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Publicacion savedPublicacion = publicacionService.save(publicacion);
        return new ResponseEntity<>(savedPublicacion, HttpStatus.CREATED);
    }

    // Actualizar una publicación existente
    @PutMapping("/{id}")
    public ResponseEntity<Publicacion> updatePublicacion(@PathVariable Long id, @RequestBody Publicacion publicacion) {
        Optional<Publicacion> existingPublicacion = publicacionService.findById(id);
        if (!existingPublicacion.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar los datos de la publicación existente
        publicacion.setId(id);
        publicacion.setFechaPublicacion(existingPublicacion.get().getFechaPublicacion()); // Mantener la fecha original
        Publicacion updatedPublicacion = publicacionService.save(publicacion);
        return ResponseEntity.ok(updatedPublicacion);
    }

    // Eliminar una publicación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublicacion(@PathVariable Long id) {
        Optional<Publicacion> existingPublicacion = publicacionService.findById(id);
        if (!existingPublicacion.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        publicacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}