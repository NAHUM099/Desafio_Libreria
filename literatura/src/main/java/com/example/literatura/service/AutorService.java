package com.example.literatura.service;

import com.example.literatura.model.Autor;
import com.example.literatura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivosEnAnio(int anio) {
        // Convierte el año entero a LocalDate usando el 1 de enero del año dado
        LocalDate inicioDelAnio = LocalDate.of(anio, 1, 1);
        return autorRepository.findAutoresVivosEnAnio(inicioDelAnio);
    }
}
