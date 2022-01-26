package com.rribeirolima.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rribeirolima.domain.Cliente;
import com.rribeirolima.repositories.ClienteRepository;
import com.rribeirolima.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		if (obj.isEmpty()) {
			 throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
			 + ", Tipo: " + Cliente.class.getName());
		}
		return obj.orElse(null);
	}
	
}
