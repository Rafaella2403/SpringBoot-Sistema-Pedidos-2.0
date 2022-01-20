package com.rribeirolima.resources;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rribeirolima.domain.Categorias;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResources {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categorias> listar() {
		
		Categorias categoria1 = new Categorias(1, "Informática");
		Categorias categoria2 = new Categorias(1, "Escritório");
		
		List<Categorias> lista = new ArrayList<>();
		
		lista.add(categoria1);
		lista.add(categoria2);
		
		return lista;
	}
	
}
