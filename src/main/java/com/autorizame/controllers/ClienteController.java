package com.autorizame.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autorizame.models.dto.ClienteRegistroDTO;
import com.autorizame.models.dto.ClienteResponseDTO;
import com.autorizame.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
	
	private final ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	// Endpoint para registrar un nuevo cliente
	@PostMapping
	public ResponseEntity<ClienteResponseDTO> registrar(@Valid @RequestBody ClienteRegistroDTO dto) {
		
		ClienteResponseDTO nuevoCliente = clienteService.registrarCliente(dto);
		
		// Se devuelve el objeto creado y el código HTTP 201 CREATED
		return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
	}
	
	// Obtener un usuario mediante su ID
	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponseDTO> buscarPorID(@PathVariable Long id) {
		
		ClienteResponseDTO clienteBuscado = clienteService.buscarPorID(id);
		
		// Se devuelve el objeto creado y el código HTTP 200 OK
		return new ResponseEntity<>(clienteBuscado, HttpStatus.OK);
		
	}
	
}
