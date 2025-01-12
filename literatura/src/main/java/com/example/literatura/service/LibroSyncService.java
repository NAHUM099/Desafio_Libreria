package com.example.literatura.service;

import com.example.literatura.dto.LibroDTO;
import com.example.literatura.model.Autor;
import com.example.literatura.model.Libro;
import com.example.literatura.repository.AutorRepository;
import com.example.literatura.repository.LibroRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

    @Service
    public class LibroSyncService {

        private final GutendexService gutendexService;
        private final LibroRepository libroRepository;

        public LibroSyncService(GutendexService gutendexService, LibroRepository libroRepository) {
            this.gutendexService = gutendexService;
            this.libroRepository = libroRepository;
        }

        public Mono<Libro> sincronizarLibroDesdeGutendex(String titulo) {
            // Cambio aquí: usar Flux<LibroDTO> en lugar de List<LibroDTO>
            return gutendexService.buscarLibrosPorTitulo(titulo)
                    .next()  // Esto selecciona el primer libro, puedes ajustar si quieres manejar más
                    .flatMap(libroDto -> {
                        // Lógica de sincronización y creación del libro
                        // Verificación si el libro ya existe, creación de autor, etc.
                        // Ejemplo de lo que ya tienes en el código original
                        return Mono.just(new Libro());  // Devuelve el libro creado
                    });
        }
    }
