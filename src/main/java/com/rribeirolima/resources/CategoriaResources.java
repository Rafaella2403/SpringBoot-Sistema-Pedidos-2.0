package com.rribeirolima.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rribeirolima.domain.Categoria;
import com.rribeirolima.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResources {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		
		Categoria obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	//Criando método para receber uma caregotia no formato json e inserir ela no banco de dados "POST"
	@RequestMapping(method = RequestMethod.POST)
	//A anotação @RequestBody faz a variável ser reconhecida como json
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria){
		categoria = service.insert(categoria);
		//Aplicando boas práticas - Quando é inserido um novo recurso o código para informar que deu certo é o 201, além disso é necessário retornar a url dos dados inseridos
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
		 
	}
	
	//Criando método para atualizar dados no banco de dados "PUT"
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Integer id) {
		categoria.setId(id);
		categoria = service.update(categoria);
		//Aplicando boas práticas - Quando é atualizado um recurso o código para informar que deu certo é o 204
		return ResponseEntity.noContent().build();
	}
	
	//Criando método para deletar dados do banco "DELETE"
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		//Aplicando boas práticas - Quando é deletado um recurso o código para informar que deu certo é o 204
		return ResponseEntity.noContent().build();
	}
	
}
