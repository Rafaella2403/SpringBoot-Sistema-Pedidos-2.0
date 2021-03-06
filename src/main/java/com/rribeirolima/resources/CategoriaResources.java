package com.rribeirolima.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rribeirolima.domain.Categoria;
import com.rribeirolima.dto.CategoriaDTO;
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
	
	//Criando um método para listar todas as categorias
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		
		List<Categoria> list = service.findAll();
		//Criando uma lista DTO com apenas os dados necessários
		List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	//Criando um método para fazer a busca das categorias páginas
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			//Anotação para tornar opcionais os parametros
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			//A quantidade de linhas é interessante mostrar 24 por que ele é multiplo de 2, 3 e 4. Com isso va iser mais fácil deixar o layout responsivo
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
			//A direção pode ser ASC - Ascendente ou DESC - Descendente
			@RequestParam(value="direction", defaultValue = "ASC") String direction
	) {
		
		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		//Criando uma lista DTO com apenas os dados necessários
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
		
		return ResponseEntity.ok().body(listDTO);
	}
	
}
