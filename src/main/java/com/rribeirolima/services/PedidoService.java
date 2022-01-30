package com.rribeirolima.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rribeirolima.domain.Pedido;
import com.rribeirolima.repositories.PedidoRepository;
import com.rribeirolima.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	//Operação capaz de buscar uma categoria por código
	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		if (obj.isEmpty()) {
			 throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
			 + ", Tipo: " + Pedido.class.getName());
		}
		return obj.orElse(null);
	}
	
}
