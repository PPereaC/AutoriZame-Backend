package com.autorizame.repository;

import java.util.List;
import java.util.Optional;

import com.autorizame.models.entity.Autorizado;

public interface AutorizadoRepository {

	Autorizado guardar(Autorizado autorizado);
	List<Autorizado> buscarPorIDCliente(Long clienteID);
	Optional<Autorizado> buscarPorDni(String dni);
	Optional<Autorizado> buscarPorID(Long id);
	Optional<Autorizado> buscarPorClienteIdYDni(Long clienteId, String dni);
	void borrarAutorizado(Long clienteID, String dni);
}

