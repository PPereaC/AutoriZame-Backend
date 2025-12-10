package com.autorizame.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autorizame.models.dto.PedidoRegistroDTO;
import com.autorizame.models.dto.PedidoResponseDTO;
import com.autorizame.services.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PedidosController {

	private final PedidoService pedidoService;

	public PedidosController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	// Endpoint para crear un nuevo pedido para un cliente
	@PostMapping("/clientes/{clienteId}/pedidos")
	public ResponseEntity<PedidoResponseDTO> crearPedido(
			@PathVariable Long clienteId,
			@Valid @RequestBody PedidoRegistroDTO dto) {

		PedidoResponseDTO nuevoPedido = pedidoService.crearPedido(clienteId, dto);

		return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
	}

}