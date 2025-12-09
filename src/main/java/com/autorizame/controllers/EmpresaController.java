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

import com.autorizame.models.dto.EmpresaRegistroDTO;
import com.autorizame.models.dto.EmpresaResponseDTO;
import com.autorizame.services.EmpresaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

private final EmpresaService empresaService;
	
	public EmpresaController(EmpresaService empresaService) {
		this.empresaService = empresaService;
	}
	
	// Endpoint para registrar una nueva empresa
	@PostMapping
	public ResponseEntity<EmpresaResponseDTO> registrar(@Valid @RequestBody EmpresaRegistroDTO dto) {
			
		EmpresaResponseDTO nuevaEmpresa = empresaService.registrarEmpresa(dto);
			
		// Se devuelve el objeto creado y el código HTTP 201 CREATED
		return new ResponseEntity<>(nuevaEmpresa, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<EmpresaResponseDTO>> listarTodas() {
	    
	    List<EmpresaResponseDTO> lista = empresaService.listarEmpresas();
	    
	    return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmpresaResponseDTO> modificarEmpresa(@PathVariable Long id, @Valid @RequestBody EmpresaRegistroDTO dto) {
		
		EmpresaResponseDTO respuesta = empresaService.modificarEmpresa(id, dto);
		
		return new ResponseEntity<>(respuesta, HttpStatus.OK);
		
	}
	
}
