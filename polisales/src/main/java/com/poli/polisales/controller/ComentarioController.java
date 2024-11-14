package com.poli.polisales.controller;

import com.poli.polisales.model.Comentario;
import com.poli.polisales.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    public List<Comentario> getAllComentarios() {
        return comentarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getComentarioById(@PathVariable Long id) {
        Optional<Comentario> comentario = comentarioService.findById(id);
        return comentario.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Comentario> createComentario(@RequestBody Comentario comentario) {
        Comentario savedComentario = comentarioService.save(comentario);
        return new ResponseEntity<>(savedComentario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> updateComentario(@PathVariable Long id, @RequestBody Comentario comentario) {
        if (!comentarioService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        comentario.setId(id);
        Comentario updatedComentario = comentarioService.save(comentario);
        return ResponseEntity.ok(updatedComentario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        if (!comentarioService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        comentarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

