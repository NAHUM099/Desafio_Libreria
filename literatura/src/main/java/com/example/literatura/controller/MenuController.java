package com.example.literatura.controller;

import com.example.literatura.model.Libro;
import com.example.literatura.model.Autor;
import com.example.literatura.service.AutorService;
import com.example.literatura.service.LibroService;
import com.example.literatura.service.LibroSyncService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;


@Controller
public class MenuController{

    private final LibroService libroService;
    private final AutorService autorService;
    private final LibroSyncService libroSyncService;

    @Autowired
    public MenuController(LibroService libroService, AutorService autorService, LibroSyncService libroSyncService) {
        this.libroService = libroService;
        this.autorService = autorService;
        this.libroSyncService = libroSyncService;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Elija la opción a través de su número:");
            System.out.println("1- Buscar un libro por título");
            System.out.println("2- Listar los libros registrados en la base de datos");
            System.out.println("3- Listar los autores registrados");
            System.out.println("4- Listar los autores vivos en un año determinado");
            System.out.println("5- Listar los libros por idioma");
            System.out.println("0- Salir");

            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1 -> buscarLibro(scanner);
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivos(scanner);
                case 5 -> listarLibrosPorIdioma(scanner);
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private void buscarLibro(Scanner scanner) {
        System.out.print("Ingrese el nombre del libro que desea buscar: ");
        String titulo = scanner.nextLine();
        Libro libro = libroSyncService.sincronizarLibroDesdeGutendex(titulo).block();

        if (libro == null) {
            System.out.println("No se encontró información para el libro.");
        } else {
            System.out.println("LIBRO -----");
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor().getNombre());
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("Número de descargas: " + libro.getNumeroDescargas());
        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroService.listarLibros();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("Libros registrados:");
            libros.forEach(libro -> System.out.println("- " + libro.getTitulo() + " (" + libro.getIdioma() + ")"));
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorService.listarAutores();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("Autores registrados:");
            autores.forEach(autor -> System.out.println("- " + autor.getNombre()));
        }
    }

    private void listarAutoresVivos(Scanner scanner) {
        System.out.print("Ingrese el año para buscar autores vivos: ");
        int anio = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        List<Autor> autores = autorService.listarAutoresVivosEnAnio(anio);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio + ".");
        } else {
            System.out.println("Autores vivos en el año " + anio + ":");
            autores.forEach(autor -> System.out.println("- " + autor.getNombre()));
        }
    }


    private void listarLibrosPorIdioma(Scanner scanner) {
        System.out.print("Ingrese el idioma: ");
        String idioma = scanner.nextLine();
        List<Libro> libros = libroService.buscarPorIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma " + idioma + ".");
        } else {
            System.out.println("Libros en el idioma " + idioma + ":");
            libros.forEach(libro -> System.out.println("- " + libro.getTitulo()));
        }
    }
}
