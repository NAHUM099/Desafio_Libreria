package com.example.literatura.service;

import com.example.literatura.dto.LibroDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class GutendexService {

    private final WebClient webClient;

    public GutendexService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://gutendex.com").build();
    }

    // Cambio aquí: usar Flux para múltiples resultados
    public Flux<LibroDTO> buscarLibrosPorTitulo(String titulo) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/books/")
                        .queryParam("search", titulo)
                        .build())
                .retrieve()
                .bodyToFlux(LibroDTO.class)  // Utilizamos bodyToFlux en lugar de bodyToMono
                .doOnError(error -> System.out.println("Error al obtener los datos de la API: " + error.getMessage()));
    }
}
