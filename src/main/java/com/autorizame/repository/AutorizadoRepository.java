package com.autorizame.repository;

import java.util.Optional;

import com.autorizame.models.entity.Autorizado;

public interface AutorizadoRepository {

	Autorizado guardar(Autorizado autorizado);
	Optional<Autorizado> buscarPorIDCliente(Long clienteID);
	Optional<Autorizado> buscarPorDni(String dni);
	Optional<Autorizado> buscarPorID(Long id);
	Optional<Autorizado> buscarPorClienteIdYDni(Long clienteId, String dni);
	void borrarAutorizadoPorID(Long id);
}

