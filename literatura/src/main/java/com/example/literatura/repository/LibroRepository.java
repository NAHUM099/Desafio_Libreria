package com.example.literatura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.literatura.model.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloAndIdioma(String titulo, String idioma);
    List<Libro> findByIdiomaIgnoreCase(String idioma);
}