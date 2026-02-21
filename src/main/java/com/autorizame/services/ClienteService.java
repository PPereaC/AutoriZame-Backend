package com.autorizame.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	public ClienteResponseDTO registrarCliente(ClienteRegistroDTO dto) {
		
		System.out.println("[CLIENTE-SERVICE] Iniciando registro de cliente: " + dto.getEmail());
		
		// Comprobar si el email existe
		if (clienteRepository.findByEmail(dto.getEmail()).isPresent()) {
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
		Cliente clienteGuardado = clienteRepository.save(nuevoCliente);
		System.out.println("[CLIENTE-SERVICE] Cliente guardado en BD con ID: " + clienteGuardado.getId());
		
		ClienteResponseDTO respuesta = new ClienteResponseDTO();
		
		respuesta.setId(clienteGuardado.getId());
		respuesta.setNombre(clienteGuardado.getNombre());
		respuesta.setEmail(clienteGuardado.getEmail());
		respuesta.setDireccionEthereum(clienteGuardado.getDireccionEthereum());
		respuesta.setFechaRegistro(clienteGuardado.getFechaRegistro());
        
		System.out.println("[CLIENTE-SERVICE] Registro completado exitosamente");
		return respuesta;
		
	}
	
	public ClienteResponseDTO buscarPorID(Long id) {
		
		Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);
		if(!clienteEncontrado.isPresent()) {
			throw new RecursoNoEncontradoException(
					"El usuario con el id [" + id + "] no está registrado"
			);
		}

		return convertirAResponseDTO(clienteEncontrado.get());
	}
	
	// Listar todos los clientes
	public List<ClienteResponseDTO> listarTodos() {
		System.out.println("[CLIENTE-SERVICE] Consultando todos los clientes en BD");
		List<Cliente> clientes = clienteRepository.findAll();
		System.out.println("[CLIENTE-SERVICE] Total de clientes encontrados: " + clientes.size());
		return clientes.stream()
			.map(this::convertirAResponseDTO)
			.collect(Collectors.toList());
	}
	
	private ClienteResponseDTO convertirAResponseDTO(Cliente cliente) {
		ClienteResponseDTO respuesta = new ClienteResponseDTO();
		respuesta.setId(cliente.getId());
		respuesta.setNombre(cliente.getNombre());
		respuesta.setEmail(cliente.getEmail());
		respuesta.setDireccionEthereum(cliente.getDireccionEthereum());
		respuesta.setFechaRegistro(cliente.getFechaRegistro());
		return respuesta;
	}
	
	public ClienteResponseDTO actualizarCliente(Long id, ClienteRegistroDTO dto) {
		
		Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(
				() -> new RecursoNoEncontradoException(
						"El usuario con el id [" + id + "] no está registrado"
		));
		
		
		// Validar email, para que no haya otro usuario con ese correo electrónico
		Optional<Cliente> otroClienteConEseEmail = clienteRepository.findByEmail(dto.getEmail());
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
	    Cliente clienteGuardado = clienteRepository.save(clienteExistente);
	    
	    // Respuesta que se manda en la llamada a la API con los datos actualizados del usuario
	    ClienteResponseDTO respuesta = new ClienteResponseDTO();
	    respuesta.setId(clienteGuardado.getId());
	    respuesta.setNombre(clienteGuardado.getNombre());
	    respuesta.setEmail(clienteGuardado.getEmail());
	    respuesta.setDireccionEthereum(clienteGuardado.getDireccionEthereum());
	    respuesta.setFechaRegistro(clienteGuardado.getFechaRegistro());
	    
	    return respuesta;
		
	}
	
	// Método para eliminar un usuario del sistema
	public void eliminarCliente(Long id) {
		
		System.out.println("[CLIENTE-SERVICE] Solicitando eliminación de cliente ID: " + id);
		
		if(!clienteRepository.findById(id).isPresent()) {
			throw new RecursoNoEncontradoException("No se puede eliminar. El usuario con id " + id + " no existe.");
		}
		
		clienteRepository.deleteById(id);
		System.out.println("[CLIENTE-SERVICE] Cliente ID " + id + " eliminado de la BD");
		
		// TODO: Quedaría mandar el correo pero no puedo ahora mismo
		
	}
	
}















