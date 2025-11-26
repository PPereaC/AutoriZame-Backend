package com.autorizame.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.autorizame.exception.EmailDuplicadoException;
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
	
}
