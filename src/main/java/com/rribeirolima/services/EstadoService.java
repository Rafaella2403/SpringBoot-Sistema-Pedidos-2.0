package com.rribeirolima.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rribeirolima.domain.Estado;
import com.rribeirolima.repositories.EstadoRepository;
import com.rribeirolima.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado find(Integer id) {
		Optional<Estado> obj = estadoRepository.findById(id);
		if (obj.isEmpty()) {
			 throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
			 + ", Tipo: " + Estado.class.getName());
		}
		return obj.orElse(null);
	}
	
}
