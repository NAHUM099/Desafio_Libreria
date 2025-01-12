package com.example.literatura.controller;

import com.example.literatura.dto.LibroDTO;
import com.example.literatura.service.GutendexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class LibroController {

    private final GutendexService gutendexService;

    public LibroController(GutendexService gutendexService) {
        this.gutendexService = gutendexService;
    }

    @GetMapping("/buscarLibro")
    public Flux<LibroDTO> buscarLibro(@RequestParam String titulo) {
        return gutendexService.buscarLibrosPorTitulo(titulo);
    }
}
