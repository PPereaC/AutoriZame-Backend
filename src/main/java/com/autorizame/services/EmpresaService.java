package com.autorizame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.autorizame.exception.EmpresaDuplicadaException;
import com.autorizame.exception.RecursoNoEncontradoException;
import com.autorizame.models.dto.EmpresaRegistroDTO;
import com.autorizame.models.dto.EmpresaResponseDTO;
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
	
	public List<EmpresaResponseDTO> listarEmpresas() {
	    
	    List<EmpresaRepartidora> empresasEntidad = empresaRepository.listarEmpresas();
	    
	    List<EmpresaResponseDTO> listaRespuesta = new ArrayList<>();
	    
	    for (EmpresaRepartidora empresa : empresasEntidad) {

	        EmpresaResponseDTO dto = new EmpresaResponseDTO();
	        dto.setId(empresa.getId());
	        dto.setNombre(empresa.getNombre());
	        dto.setCorreo(empresa.getCorreo());
	        dto.setTelefono(empresa.getTelefono());

	        listaRespuesta.add(dto);
	    }
	    
	    return listaRespuesta;
	}
	
	public EmpresaResponseDTO modificarEmpresa(Long id, EmpresaRegistroDTO dto) {
	    
	    EmpresaRepartidora empresaExistente = empresaRepository.buscarPorID(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("No existe la empresa con id " + id));

	    Optional<EmpresaRepartidora> coincidencia = empresaRepository.buscarPorNombre(dto.getNombre());
	    
	    if (coincidencia.isPresent()) {
	        if (!coincidencia.get().getId().equals(id)) {
	            throw new EmpresaDuplicadaException(
	                "El nombre " + dto.getNombre() + " ya está en uso por otra empresa."
	            );
	        }
	    }
	                    
	    empresaExistente.setNombre(dto.getNombre());
	    empresaExistente.setCorreo(dto.getCorreo());
	    empresaExistente.setTelefono(dto.getTelefono());
	                    
	    EmpresaRepartidora empresaGuardada = empresaRepository.guardar(empresaExistente);
	                    
	    EmpresaResponseDTO respuesta = new EmpresaResponseDTO();
	    respuesta.setId(empresaGuardada.getId());
	    respuesta.setNombre(empresaGuardada.getNombre());
	    respuesta.setCorreo(empresaGuardada.getCorreo());
	    respuesta.setTelefono(empresaGuardada.getTelefono());
	            
	    return respuesta;
	}
	
	public void eliminarEmpresa(Long id) {
		
		if(!empresaRepository.buscarPorID(id).isPresent()) {
			throw new RecursoNoEncontradoException("No se puede eliminar. La empresa con id " + id + " no existe.");
		}
		
		empresaRepository.borrarEmpresa(id);
	}
	
}
