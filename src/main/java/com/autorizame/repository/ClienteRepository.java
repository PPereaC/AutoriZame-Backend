package com.autorizame.repository;

import java.util.Optional;

import com.autorizame.models.entity.Cliente;

public interface ClienteRepository {

	Cliente guardar(Cliente cliente);
	Optional<Cliente> buscarPorEmail(String email);
	Optional<Cliente> buscarPorID(Long id);
	void borrarClientePorID(Long id);
}
