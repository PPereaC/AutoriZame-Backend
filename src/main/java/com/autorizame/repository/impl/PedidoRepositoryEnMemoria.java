package com.autorizame.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.autorizame.models.entity.Pedido;
import com.autorizame.repository.PedidoRepository;

@Repository
public class PedidoRepositoryEnMemoria implements PedidoRepository {

	private Map<Long, Pedido> pedidos = new HashMap<>();
	private Long generadorId = 1L;

	@Override
	public Pedido guardar(Pedido pedido) {
		
		// Si el pedido es nuevo (no tiene ID), le asignamos uno
		if (pedido.getId() == null) {
			pedido.setId(generadorId);
			generadorId++;
		}
		
		pedidos.put(pedido.getId(), pedido);
		
		return pedido;
	}

	@Override
	public Optional<Pedido> buscarPorID(Long id) {
		return Optional.ofNullable(pedidos.get(id));
	}

	@Override
	public List<Pedido> buscarPorClienteId(Long clienteId) {
		
		List<Pedido> pedidosDelCliente = new ArrayList<>();
		
		for (Pedido p : pedidos.values()) {
			if (p.getClienteId().equals(clienteId)) {
				pedidosDelCliente.add(p);
			}
		}
		
		return pedidosDelCliente;
	}

	@Override
	public List<Pedido> obtenerTodos() {
		return new ArrayList<>(pedidos.values());
	}

}