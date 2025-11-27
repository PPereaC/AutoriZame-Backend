package com.autorizame.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.autorizame.exception.DatosUsuarioNoCoincidenException;
import com.autorizame.exception.EmailDuplicadoException;
import com.autorizame.exception.RecursoNoEncontradoException;
import com.autorizame.models.dto.ClienteRegistroDTO;
import com.autorizame.models.dto.ClienteResponseDTO;
import com.autorizame.models.entity.Cliente;
import com.autorizame.repository.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	// Este método será llamado por el Controlador
	public ClienteResponseDTO registrarCliente(ClienteRegistroDTO dto) {
		
		// Comprobar si el email existe
		if (clienteRepository.buscarPorEmail(dto.getEmail()).isPresent()) {
			throw new EmailDuplicadoException("El correo " + dto.getEmail() + " ya está registrado en el sistema.");
		}
		
		// Creación de una instancia Cliente para setearle los datos que vienen del DTO
		Cliente nuevoCliente = new Cliente();
		
		nuevoCliente.setNombre(dto.getNombre());
		nuevoCliente.setEmail(dto.getEmail());
		nuevoCliente.setContrasena(dto.getContrasena());
		nuevoCliente.setDireccionEthereum(dto.getDireccionEthereum());
		nuevoCliente.setFechaRegistro(LocalDateTime.now());
		
		// Guardar cliente en el repositorio
		Cliente clienteGuardado = clienteRepository.guardar(nuevoCliente);
		
		ClienteResponseDTO respuesta = new ClienteResponseDTO();
		
		respuesta.setId(clienteGuardado.getId());
		respuesta.setNombre(clienteGuardado.getNombre());
		respuesta.setEmail(clienteGuardado.getEmail());
		respuesta.setDireccionEthereum(clienteGuardado.getDireccionEthereum());
		respuesta.setFechaRegistro(clienteGuardado.getFechaRegistro());
        
		return respuesta;
		
	}
	
	public ClienteResponseDTO buscarPorID(Long id) {
		
		Optional<Cliente> clienteEncontrado = clienteRepository.buscarPorID(id);
		if(!clienteEncontrado.isPresent()) {
			throw new RecursoNoEncontradoException(
					"El usuario con el id [" + id + "] no está registrado"
			);
		}

		ClienteResponseDTO respuesta = new ClienteResponseDTO();
		respuesta.setId(clienteEncontrado.get().getId());
		respuesta.setNombre(clienteEncontrado.get().getNombre());
		respuesta.setEmail(clienteEncontrado.get().getEmail());
		respuesta.setDireccionEthereum(clienteEncontrado.get().getDireccionEthereum());
		respuesta.setFechaRegistro(clienteEncontrado.get().getFechaRegistro());
		
		return respuesta;
		
	}
	
	public ClienteResponseDTO actualizarCliente(Long id, ClienteRegistroDTO dto) {
		
		Cliente clienteExistente = clienteRepository.buscarPorID(id).orElseThrow(
				() -> new RecursoNoEncontradoException(
						"El usuario con el id [" + id + "] no está registrado"
		));
		
		
		// Validar email, para que no haya otro usuario con ese correo electrónico
		Optional<Cliente> otroClienteConEseEmail = clienteRepository.buscarPorEmail(dto.getEmail());
		if(otroClienteConEseEmail.isPresent()) {
			if(!otroClienteConEseEmail.get().getId().equals(id)) {
				throw new DatosUsuarioNoCoincidenException(
					"El correo " + dto.getEmail() + " ya está en uso por otro usuario."
				);
			}
		}
		
		// Actualizar los nuevos datos del cliente
		clienteExistente.setNombre(dto.getNombre());
	    clienteExistente.setEmail(dto.getEmail());
	    clienteExistente.setContrasena(dto.getContrasena());
	    clienteExistente.setDireccionEthereum(dto.getDireccionEthereum());
	    
	    // Guardar el nuevo cliente
	    Cliente clienteGuardado = clienteRepository.guardar(clienteExistente);
	    
	    // Respuesta que se manda en la llamada a la API con los datos actualizados del usuario
	    ClienteResponseDTO respuesta = new ClienteResponseDTO();
	    respuesta.setId(clienteGuardado.getId());
	    respuesta.setNombre(clienteGuardado.getNombre());
	    respuesta.setEmail(clienteGuardado.getEmail());
	    respuesta.setDireccionEthereum(clienteGuardado.getDireccionEthereum());
	    respuesta.setFechaRegistro(clienteGuardado.getFechaRegistro());
	    
	    return respuesta;
		
	}
	
}















