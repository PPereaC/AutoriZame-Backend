package com.autorizame.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autorizame.models.dto.ClienteRegistroDTO;
import com.autorizame.models.dto.ClienteResponseDTO;
import com.autorizame.models.dto.RepartidorRegistroDTO;
import com.autorizame.models.dto.RepartidorResponseDTO;
import com.autorizame.services.RepartidorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/empresas")
public class RepartidoresController {

	private final RepartidorService repartidorService;
	
	public RepartidoresController(RepartidorService repartidorService) {
		this.repartidorService = repartidorService;
	}
	
	// Endpoint para registrar un nuevo repartidor
	@PostMapping("/{idEmpresa}/repartidores")
	public ResponseEntity<RepartidorResponseDTO> registrar(@PathVariable Long idEmpresa, @Valid @RequestBody RepartidorRegistroDTO dto) {
			
		RepartidorResponseDTO nuevoRepartidor = repartidorService.registrarRepartidor(idEmpresa, dto);
			
		// Se devuelve el objeto creado y el código HTTP 201 CREATED
		return new ResponseEntity<>(nuevoRepartidor, HttpStatus.CREATED);
	}
	
}
