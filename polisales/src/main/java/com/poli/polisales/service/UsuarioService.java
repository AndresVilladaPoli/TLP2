package com.poli.polisales.service;

import com.poli.polisales.model.Usuario;
import com.poli.polisales.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Obtener todos los usuarios
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario por ID
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    // Guardar o actualizar un usuario
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Eliminar un usuario por ID
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Obtener un usuario por email
    public Optional<Usuario> findByEmail(String email) {
        return Optional.ofNullable(usuarioRepository.findByEmail(email));
    }

    // Registrar un nuevo usuario
    public Usuario registerUser(String nombre, String conteasena, String email) {
        // Verificar si el usuario o email ya existen
        if (usuarioRepository.findByNombre(nombre).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        if (usuarioRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado");
        }

        // Crear y guardar el usuario con la contraseña encriptada
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setContrasena(passwordEncoder.encode(conteasena));
        usuario.setEmail(email);
        return usuarioRepository.save(usuario);
    }
}


