package com.bootcamp.gestor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestorSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorSpringApplication.class, args);
		System.out.println("Server iniciado");
	}

}
