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
	
	//Criando serviço insert para realizar inserções no banco
	public Categoria insert(Categoria categoria) {
		//Operação para garantir que esta sendo inserido um novo objeto
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	//O método save vale tanto para atualizar quanto para inserir a diferença é que no momento que o objeto esta valendo nulo ele entender que é para inserir
	public Categoria update(Categoria categoria) {
		//Validando se o ID informado existe, caso não exista vai ser lançado uma exceção para o usuário
		find(categoria.getId());
		return categoriaRepository.save(categoria);
	}
	
}
