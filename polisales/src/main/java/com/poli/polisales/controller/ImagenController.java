package com.poli.polisales.controller;

import com.poli.polisales.model.Imagen;
import com.poli.polisales.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;

    @GetMapping
    public List<Imagen> getAllImagenes() {
        return imagenService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagen> getImagenById(@PathVariable Long id) {
        Optional<Imagen> imagen = imagenService.findById(id);
        return imagen.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Imagen> createImagen(@RequestBody Imagen imagen) {
        Imagen savedImagen = imagenService.save(imagen);
        return new ResponseEntity<>(savedImagen, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imagen> updateImagen(@PathVariable Long id, @RequestBody Imagen imagen) {
        if (!imagenService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        imagen.setId(id);
        Imagen updatedImagen = imagenService.save(imagen);
        return ResponseEntity.ok(updatedImagen);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImagen(@PathVariable Long id) {
        if (!imagenService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        imagenService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

