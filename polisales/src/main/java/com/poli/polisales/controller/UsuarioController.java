package com.poli.polisales.controller;

import com.poli.polisales.model.Usuario;
import com.poli.polisales.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios con JSON
    @GetMapping
    @ResponseBody
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    // Obtener un usuario por ID JSON
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo usuario JSON
    @PostMapping
    @ResponseBody
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioService.save(usuario);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    // Actualizar un usuario existente JSON
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        if (!usuarioService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id);
        Usuario updatedUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    // Eliminar un usuario JSON
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (!usuarioService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Muestra el formulario de registro
    @GetMapping("/registro")
    public String showRegistrationForm(@RequestParam(required = false) String error, org.springframework.ui.Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "registro";
    }

    // Procesa el formulario de registro
    @PostMapping("/registro")
    public String registerUser(@RequestParam String nombre,
                               @RequestParam String contrasena,
                               @RequestParam String email) {
        try {
            usuarioService.registerUser(nombre, contrasena, email);
            return "redirect:/login"; // Redirige al login tras el registro exitoso
        } catch (IllegalArgumentException e) {
            return "redirect:/api/usuarios/registro?error=" + e.getMessage();
        }
    }
}