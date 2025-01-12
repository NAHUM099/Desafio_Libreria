package com.example.literatura.repository;

import com.example.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :anio AND " +
            "(a.fechaFallecimiento IS NULL OR a.fechaFallecimiento > :anio)")
    List<Autor> findAutoresVivosEnAnio(@Param("anio") LocalDate anio);
    Optional<Autor> findByNombre(String nombre);
}
