package com.autorizame.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.autorizame.exception.AutorizadoDuplicadoException;
import com.autorizame.exception.RecursoNoEncontradoException;
import com.autorizame.models.dto.AutorizadoRegistroDTO;
import com.autorizame.models.dto.AutorizadoResponseDTO;
import com.autorizame.models.entity.Autorizado;
import com.autorizame.repository.AutorizadoRepository;
import com.autorizame.repository.ClienteRepository;

@Service
public class AutorizadoService {

	private final AutorizadoRepository autorizadoRepository;
	private final ClienteRepository clienteRepository;

	public AutorizadoService(AutorizadoRepository autorizadoRepository, ClienteRepository clienteRepository) {
		this.autorizadoRepository = autorizadoRepository;
		this.clienteRepository = clienteRepository;
	}

	public AutorizadoResponseDTO crearAutorizado(Long clienteID, AutorizadoRegistroDTO dto) {
		
		// Validar que exista el ID del cliente
		if(!clienteRepository.buscarPorID(clienteID).isPresent()) {
			throw new RecursoNoEncontradoException(
					"El cliente con el id [" + clienteID + "] no está registrado"
			);
		}

		Optional<Autorizado> duplicado = autorizadoRepository.buscarPorClienteIdYDni(clienteID, dto.getDni());

		if (duplicado.isPresent()) {
		    throw new AutorizadoDuplicadoException("Esta persona ya está autorizada por el usuario");
		}
		
		// Creación de una instancia Autorizado para setearle los datos que vienen del DTO
		Autorizado nuevoAutorizado = new Autorizado();
		
		nuevoAutorizado.setNombre(dto.getNombre());
		nuevoAutorizado.setDni(dto.getDni());
		nuevoAutorizado.setClienteId(clienteID);
		nuevoAutorizado.setDireccionEthereum(dto.getDireccionEthereum());
		nuevoAutorizado.setTelefono(dto.getTelefono());
		nuevoAutorizado.setFechaRegistro(LocalDateTime.now());
		
		// Guardar autorizado en el repositorio
		Autorizado autorizadoGuardado = autorizadoRepository.guardar(nuevoAutorizado);
		
		AutorizadoResponseDTO respuesta = new AutorizadoResponseDTO();
		
		respuesta.setId(autorizadoGuardado.getId());
		respuesta.setNombre(autorizadoGuardado.getNombre());
		respuesta.setDni(autorizadoGuardado.getDni());
		respuesta.setDireccionEthereum(autorizadoGuardado.getDireccionEthereum());
		respuesta.setTelefono(autorizadoGuardado.getTelefono());
		respuesta.setClienteId(autorizadoGuardado.getClienteId());
		respuesta.setFechaRegistro(autorizadoGuardado.getFechaRegistro());
        
		return respuesta;
		
	}
	
}
