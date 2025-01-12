package com.example.literatura;

import com.example.literatura.controller.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	private final MenuController menuController;

	@Autowired
	public LiteraturaApplication(MenuController menuController) {
		this.menuController = menuController;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) {
		menuController.iniciar(); // Inicia el menú interactivo
	}
}
