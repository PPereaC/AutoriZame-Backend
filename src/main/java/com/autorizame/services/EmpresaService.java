package com.autorizame.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.autorizame.exception.EmailDuplicadoException;
import com.autorizame.exception.EmpresaDuplicadaException;
import com.autorizame.models.dto.ClienteResponseDTO;
import com.autorizame.models.dto.EmpresaRegistroDTO;
import com.autorizame.models.dto.EmpresaResponseDTO;
import com.autorizame.models.entity.Cliente;
import com.autorizame.models.entity.EmpresaRepartidora;
import com.autorizame.repository.EmpresaRepository;

@Service
public class EmpresaService {

	private final EmpresaRepository empresaRepository;

	public EmpresaService(EmpresaRepository empresaRepository) {
		this.empresaRepository = empresaRepository;
	}
	
	public EmpresaResponseDTO registrarEmpresa(EmpresaRegistroDTO dto) {
		
		// Comprobar si el nombre de la empresa existe
		if (empresaRepository.buscarPorNombre(dto.getNombre()).isPresent()) {
			throw new EmpresaDuplicadaException("El nombre de la empresa " + dto.getNombre() + " ya está registrada en el sistema.");
		}
				
		// Creación de una instancia EmpresaRepartidora para setearle los datos que vienen del DTO
		EmpresaRepartidora nuevaEmpresa = new EmpresaRepartidora();
				
		nuevaEmpresa.setNombre(dto.getNombre());
		nuevaEmpresa.setCorreo(dto.getCorreo());
		nuevaEmpresa.setTelefono(dto.getTelefono());
				
		// Guardar empresa en el repositorio
		EmpresaRepartidora empresaGuardada = empresaRepository.guardar(nuevaEmpresa);
				
		EmpresaResponseDTO respuesta = new EmpresaResponseDTO();
				
		respuesta.setId(empresaGuardada.getId());
		respuesta.setNombre(empresaGuardada.getNombre());
		respuesta.setCorreo(empresaGuardada.getCorreo());
		respuesta.setTelefono(empresaGuardada.getTelefono());
		        
		return respuesta;
		
	}
	
}
