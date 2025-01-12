package com.example.literatura.service;

import com.example.literatura.dto.LibroDTO;
import com.example.literatura.model.Libro;
import com.example.literatura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    public Optional<Libro> buscarPorTitulo(LibroDTO libroDTO) {
        return libroRepository.findByTituloAndIdioma(libroDTO.getTitle(), libroDTO.getLanguage());
    }


    public List<Libro> buscarPorIdioma(String idioma) {
        return libroRepository.findByIdiomaIgnoreCase(idioma);
    }
}
