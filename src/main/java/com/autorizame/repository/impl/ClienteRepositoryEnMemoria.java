package com.autorizame.repository.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.autorizame.models.entity.Cliente;
import com.autorizame.repository.ClienteRepository;

@Repository
public class ClienteRepositoryEnMemoria implements ClienteRepository{

	private Map<Long, Cliente> clientes = new HashMap<>();
    private Long contador = 1L;

	@Override
	public Cliente guardar(Cliente cliente) {
		
		// Si el cliente llega sin ID (es nuevo), se le asigna uno nuevo
		if (cliente.getId() == null) {
			cliente.setId(contador);
			contador++;
		} 
		
		// Se guarda el cliente con su id en el Map
		clientes.put(cliente.getId(), cliente);
		
		return cliente;
	}
	
	@Override
	public Optional<Cliente> buscarPorEmail(String email) {
		
		// Se busca si hay algún cliente con el correo pasado por parámetro
		for (Cliente c : clientes.values()) {
			if(c.getEmail().equalsIgnoreCase(email)) {
				return Optional.of(c);
			}
		}
		
		// Si no se encuentra se manda como vacío
		return Optional.empty();
	}
	
}
