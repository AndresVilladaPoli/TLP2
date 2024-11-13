package com.poli.polisales.repository;

import com.poli.polisales.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Puedes agregar métodos personalizados si los necesitas, por ejemplo:
    Usuario findByEmail(String email);
}

