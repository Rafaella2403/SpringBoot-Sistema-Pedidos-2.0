package com.rribeirolima.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rribeirolima.domain.Estado;
import com.rribeirolima.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResources {
	
	@Autowired
	private EstadoService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Estado obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
