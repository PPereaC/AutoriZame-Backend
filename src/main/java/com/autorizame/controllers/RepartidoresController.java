package com.autorizame.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.autorizame.models.dto.ClienteRegistroDTO;
import com.autorizame.models.dto.ClienteResponseDTO;
import com.autorizame.models.dto.ListarRepartidoresEmpresaDTO;
import com.autorizame.models.dto.RepartidorActualizacionDTO;
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
	
	// Endpoint para listar todos los repartidores de una empresa
	@GetMapping("/{idEmpresa}/repartidores")
	public ResponseEntity<List<ListarRepartidoresEmpresaDTO>> listarRepartidoresPorEmpresa(@PathVariable Long idEmpresa) {
		
		List<ListarRepartidoresEmpresaDTO> listaRespuesta = repartidorService.listarRepartidoresPorEmpresa(idEmpresa);
		
		return new ResponseEntity<>(listaRespuesta, HttpStatus.OK);
		
	}
	
	// Endpoint para actualizar el correo y telefono de un repartidor
	@PutMapping("/{idEmpresa}/repartidores/{idRepartidor}")
	public ResponseEntity<RepartidorResponseDTO> modificarRepartidor(
			@PathVariable Long idEmpresa, @PathVariable Long idRepartidor,
			@Valid @RequestBody RepartidorActualizacionDTO dto) {
		
		RepartidorResponseDTO respuesta = repartidorService.modificarRepartidor(idRepartidor, dto);
		
		return new ResponseEntity<>(respuesta, HttpStatus.OK);
		
	}
	
	// Endpoint para eliminar un repartidor
	@DeleteMapping("/{idEmpresa}/repartidores/{idRepartidor}")
	public ResponseEntity<Map<String, String>> eliminarRepartidor
		(@PathVariable Long idEmpresa, @PathVariable Long idRepartidor) {
		
		repartidorService.eliminarRepartidor(idRepartidor);
		
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("mensaje", "Repartidor eliminado correctamente del sistema");
		
		return new ResponseEntity<>(respuesta, HttpStatus.OK);
		
	}
	
}
