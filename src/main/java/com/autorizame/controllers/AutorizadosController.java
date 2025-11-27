package com.autorizame.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autorizame.models.dto.AutorizadoRegistroDTO;
import com.autorizame.models.dto.AutorizadoResponseDTO;
import com.autorizame.services.AutorizadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AutorizadosController {
	
	private final AutorizadoService autorizadoService;
	
	public AutorizadosController(AutorizadoService autorizadoService) {
		this.autorizadoService = autorizadoService;
	}
	
	// Endpoint para registrar un nuevo autorizado
	@PostMapping("/clientes/{clienteId}/autorizados")
	public ResponseEntity<AutorizadoResponseDTO> registrar(
			@PathVariable Long clienteId,
			@Valid @RequestBody AutorizadoRegistroDTO dto) {
		
		AutorizadoResponseDTO nuevoAutorizado = autorizadoService.crearAutorizado(clienteId, dto);
		
		// Se devuelve el objeto creado y el código HTTP 201 CREATED
		return new ResponseEntity<>(nuevoAutorizado, HttpStatus.CREATED);
	}
	
}
