package com.allain.curso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allain.curso.domain.Pedido;
import com.allain.curso.repositories.PedidoRepository;
import com.allain.curso.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorio;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repositorio.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " +id + ", Tipo" + Pedido.class.getName()));
	}
}

