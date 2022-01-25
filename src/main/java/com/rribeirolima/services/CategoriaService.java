package com.rribeirolima.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rribeirolima.domain.Categoria;
import com.rribeirolima.repositories.CategoriaRepository;
import com.rribeirolima.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	//Com esta anotação o spring vai instanciar automáticamente
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//Operação capaz de buscar uma categoria por código
	public Categoria find(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		if (obj.isEmpty()) {
			 throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
			 + ", Tipo: " + Categoria.class.getName());
		}
		return obj.orElse(null);
	}
	
}
