package com.rribeirolima;

import java.util.Arrays;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rribeirolima.domain.Categoria;
import com.rribeirolima.repositories.CategoriaRepository;

@SpringBootApplication
public class ImplementandoModeloConceitualApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ImplementandoModeloConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//O id esta nulo por que é o banco de dados que vai gerar o id
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
	}

}

