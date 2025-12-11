package com.autorizame.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autorizame.models.dto.ActualizarEstadoPedidoDTO;
import com.autorizame.models.dto.AsignarRepartidorDTO;
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
	
	// Endpoint para asignar un repartidor a un pedido
    @PutMapping("/empresas/{empresaId}/pedidos/{pedidoId}/asignar")
    public ResponseEntity<PedidoResponseDTO> asignarRepartidor(
            @PathVariable Long empresaId,
            @PathVariable Long pedidoId,
            @Valid @RequestBody AsignarRepartidorDTO dto) {

        PedidoResponseDTO pedidoActualizado = pedidoService.asignarRepartidor(empresaId, pedidoId, dto);

        return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
    }
    
    // Endpoint para obtener todos los pedidos de un cliente
    @GetMapping("/clientes/{clienteId}/pedidos")
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidosPorCliente(@PathVariable Long clienteId) {
    	
    	List<PedidoResponseDTO> respuesta = pedidoService.listarPedidosPorCliente(clienteId);
    	
    	return new ResponseEntity<>(respuesta, HttpStatus.OK);
    	
    }
    
    // Endpoint para actualizar el estado de un pedido (repartidor)
    @PutMapping("/pedidos/{pedidoId}/estado")
    public ResponseEntity<PedidoResponseDTO> actualizarEstado(
            @PathVariable Long pedidoId,
            @Valid @RequestBody ActualizarEstadoPedidoDTO dto) {

        PedidoResponseDTO pedidoActualizado = pedidoService.actualizarEstadoPedido(pedidoId, dto);
        
        return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
    }
    
    // Endpoint para cancelar un pedido con estado "PENDIENTE"
    @DeleteMapping("/clientes/{clienteId}/pedidos/{pedidoId}")
    public ResponseEntity<Void> cancelarPedido(
            @PathVariable Long clienteId, 
            @PathVariable Long pedidoId) {

        pedidoService.cancelarPedido(clienteId, pedidoId);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}