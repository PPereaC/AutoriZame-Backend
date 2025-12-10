package com.autorizame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.autorizame.exception.EmailDuplicadoException;
import com.autorizame.exception.EmpresaDuplicadaException;
import com.autorizame.exception.RecursoNoEncontradoException;
import com.autorizame.models.dto.ListarRepartidoresEmpresaDTO;
import com.autorizame.models.dto.RepartidorActualizacionDTO;
import com.autorizame.models.dto.RepartidorRegistroDTO;
import com.autorizame.models.dto.RepartidorResponseDTO;
import com.autorizame.models.entity.Repartidor;
import com.autorizame.repository.EmpresaRepository;
import com.autorizame.repository.RepartidoresRepository;

@Service
public class RepartidorService {

	private final RepartidoresRepository repartidorRepository;
	private final EmpresaRepository empresaRepository;

	public RepartidorService(RepartidoresRepository repartidorRepository, EmpresaRepository empresaRepository) {
		this.repartidorRepository = repartidorRepository;
		this.empresaRepository = empresaRepository;
	}
	
	public RepartidorResponseDTO registrarRepartidor(Long idEmpresa, RepartidorRegistroDTO dto) {
		
		// Comprobar si el email existe
		if (repartidorRepository.buscarPorCorreo(dto.getCorreo()).isPresent()) {
			throw new EmailDuplicadoException("El correo " + dto.getCorreo() + " ya está registrado en el sistema.");
		}
		
		// Comprobar si el id de la empresa existe
		if(!empresaRepository.buscarPorID(idEmpresa).isPresent()) {
			throw new RecursoNoEncontradoException("La empresa con id " + idEmpresa + " no existe en el sistema");
		}
		
		// Creación de una instancia Repartidor para setearle los datos que vienen del DTO
		Repartidor nuevoRepartidor = new Repartidor();
		
		nuevoRepartidor.setNombre(dto.getNombre());
		nuevoRepartidor.setCorreo(dto.getCorreo());
		nuevoRepartidor.setTelefono(dto.getTelefono());
		nuevoRepartidor.setEstado(dto.getEstado());
		nuevoRepartidor.setEmpresaId(idEmpresa);
		
		// Guardar repartidor en el repositorio
		Repartidor repartidorGuardado = repartidorRepository.guardar(nuevoRepartidor);
		
		RepartidorResponseDTO respuesta = new RepartidorResponseDTO();
		
		respuesta.setId(repartidorGuardado.getId());
		respuesta.setNombre(repartidorGuardado.getNombre());
		respuesta.setCorreo(repartidorGuardado.getCorreo());
		respuesta.setTelefono(repartidorGuardado.getTelefono());
		respuesta.setEmpresaId(repartidorGuardado.getEmpresaId());
		respuesta.setEstado(repartidorGuardado.getEstado());
        
		return respuesta;
		
	}
	
	public List<ListarRepartidoresEmpresaDTO> listarRepartidoresPorEmpresa(Long id) {
		
		List<Repartidor> repartidoresEncontrados = repartidorRepository.listarRepartidoresPorEmpresa(id);
		List<ListarRepartidoresEmpresaDTO> repartidoresRespuesta = new ArrayList<>();
		
		for (Repartidor r : repartidoresEncontrados) {
			ListarRepartidoresEmpresaDTO respuesta = new ListarRepartidoresEmpresaDTO();
			respuesta.setId(r.getId());
			respuesta.setNombre(r.getNombre());
			respuesta.setCorreo(r.getCorreo());
			respuesta.setEstado(r.getEstado());
			
			repartidoresRespuesta.add(respuesta);
		}
		
		return repartidoresRespuesta;
		
	}
	
	public RepartidorResponseDTO modificarRepartidor(Long id, RepartidorActualizacionDTO dto) {
	    
	    Repartidor repartidorExistente = repartidorRepository.buscarPorID(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("No existe el reparidor con id " + id));

	    Optional<Repartidor> coincidencia = repartidorRepository.buscarPorCorreo(dto.getCorreo());
	    
	    if (coincidencia.isPresent()) {
	        if (!coincidencia.get().getId().equals(id)) {
	            throw new EmpresaDuplicadaException(
	                "El correo " + dto.getCorreo() + " ya está en uso por otro repartidor."
	            );
	        }
	    }
	                    
	    repartidorExistente.setCorreo(dto.getCorreo());
	    repartidorExistente.setTelefono(dto.getTelefono());
	                    
	    Repartidor repartidorGuardado = repartidorRepository.guardar(repartidorExistente);
	                    
	    RepartidorResponseDTO respuesta = new RepartidorResponseDTO();
	    respuesta.setId(repartidorGuardado.getId());
	    respuesta.setNombre(repartidorGuardado.getNombre());
	    respuesta.setCorreo(repartidorGuardado.getCorreo());
	    respuesta.setTelefono(repartidorGuardado.getTelefono());
	    respuesta.setEstado(repartidorGuardado.getEstado());
	    respuesta.setEmpresaId(repartidorGuardado.getEmpresaId());
	            
	    return respuesta;
	}
	
}
