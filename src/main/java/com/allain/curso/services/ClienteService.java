package com.allain.curso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allain.curso.domain.Cliente;
import com.allain.curso.repositories.ClienteRepository;
import com.allain.curso.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorio;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repositorio.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " +id + ", Tipo" + Cliente.class.getName()));
	}
}

