package com.rribeirolima.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rribeirolima.domain.Categoria;
import com.rribeirolima.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	//Com esta anotação o spring vai instanciar automáticamente
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//Operação capaz de buscar uma categoria por código
	public Categoria buscar(Integer id) {
		return categoriaRepository.findById(id).orElse(null);
	}
	
}
