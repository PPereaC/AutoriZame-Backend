package com.autorizame.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	
}
