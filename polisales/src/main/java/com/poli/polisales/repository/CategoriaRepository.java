package com.poli.polisales.repository;

import com.poli.polisales.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Puedes agregar métodos personalizados si son necesarios
}

