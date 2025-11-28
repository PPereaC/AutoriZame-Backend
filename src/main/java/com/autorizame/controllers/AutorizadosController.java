package com.autorizame.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autorizame.models.dto.AutorizadoRegistroDTO;
import com.autorizame.models.dto.AutorizadoResponseDTO;
import com.autorizame.models.dto.ListarAutorizadosDTO;
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
	
	// Endpoint para listar los autorizados de un cliente
	@GetMapping("/clientes/{clienteId}/autorizados")
	public ResponseEntity<?> listarAutorizadosPorCliente(@Valid @PathVariable Long clienteId) {
		
		List<ListarAutorizadosDTO> autorizados = autorizadoService.listarAutorizados(clienteId);
		
		if(autorizados.isEmpty()) {
			Map<String, String> mensaje = new HashMap<>();
	        mensaje.put("mensaje", "No hay autorizados para este cliente");
	        return ResponseEntity.ok(mensaje); // Devuelve Map
		}
		
		return new ResponseEntity<>(autorizados, HttpStatus.OK);
		
	}
	
	// Endpoint para actualizar los datos de un autorizado
	@PutMapping("/clientes/{clienteId}/autorizados")
	public ResponseEntity<AutorizadoResponseDTO> actualizarAutorizado(@Valid @PathVariable Long clienteId, @Valid @RequestBody AutorizadoRegistroDTO dto) {
		
		AutorizadoResponseDTO autorizadoActualizado = autorizadoService.actualizarDatosAutorizado(clienteId, dto.getDni(), dto);
		
		return new ResponseEntity<>(autorizadoActualizado, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
