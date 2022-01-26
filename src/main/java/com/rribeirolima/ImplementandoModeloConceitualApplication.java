package com.rribeirolima;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rribeirolima.domain.Categoria;
import com.rribeirolima.domain.Cidade;
import com.rribeirolima.domain.Estado;
import com.rribeirolima.domain.Produto;
import com.rribeirolima.repositories.CategoriaRepository;
import com.rribeirolima.repositories.CidadeRepository;
import com.rribeirolima.repositories.EstadoRepository;
import com.rribeirolima.repositories.ProdutoRepository;

@SpringBootApplication
public class ImplementandoModeloConceitualApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ImplementandoModeloConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//O id esta nulo por que é o banco de dados que vai gerar o id
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cid2 = new Cidade(null, "São Paulo", estado2);
		Cidade cid3 = new Cidade(null, "Campinas", estado2);
		
				
		//Adicionando os produtos as categorias
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		//Adicionado as categorias aos produtos
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		
		//Adicionando estados as cidades
		estado1.getCidades().addAll(Arrays.asList(cid1));
		estado2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		//Salvando os produtos e categorias
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		//Salvando as cidades e estados
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		
		
	}

}

