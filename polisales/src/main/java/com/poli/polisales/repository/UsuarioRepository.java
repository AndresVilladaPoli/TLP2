package com.poli.polisales.repository;

import com.poli.polisales.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por email
    Usuario findByEmail(String email);

    // Buscar usuario por nombre de usuario
    Optional<Usuario> findByNombre(String nombre);
}


