package com.autorizame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autorizame.models.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findByClienteId(Long clienteId);
}
