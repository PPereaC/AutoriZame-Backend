package com.autorizame.repository;

import java.util.List;
import java.util.Optional;

import com.autorizame.models.entity.Pedido;

public interface PedidoRepository {

	Pedido guardar(Pedido pedido);
	Optional<Pedido> buscarPorID(Long id);
	List<Pedido> buscarPorClienteId(Long clienteId);
	List<Pedido> obtenerTodos();
	void eliminar(Long id);
	
}
