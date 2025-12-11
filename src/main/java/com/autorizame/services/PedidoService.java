package com.autorizame.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.autorizame.exception.DatosUsuarioNoCoincidenException;
import com.autorizame.exception.RecursoNoEncontradoException;
import com.autorizame.models.dto.ActualizarEstadoPedidoDTO;
import com.autorizame.models.dto.AsignarRepartidorDTO;
import com.autorizame.models.dto.PedidoRegistroDTO;
import com.autorizame.models.dto.PedidoResponseDTO;
import com.autorizame.models.entity.Autorizado;
import com.autorizame.models.entity.Pedido;
import com.autorizame.models.entity.Repartidor;
import com.autorizame.repository.AutorizadoRepository;
import com.autorizame.repository.ClienteRepository;
import com.autorizame.repository.EmpresaRepository;
import com.autorizame.repository.PedidoRepository;
import com.autorizame.repository.RepartidoresRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final AutorizadoRepository autorizadoRepository;
    private final EmpresaRepository empresaRepository;
    private final RepartidoresRepository repartidorRepository;

    public PedidoService(PedidoRepository pedidoRepository,
    		ClienteRepository clienteRepository,
    		AutorizadoRepository autorizadoRepository, EmpresaRepository empresaRepository, RepartidoresRepository repartidorRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.autorizadoRepository = autorizadoRepository;
        this.empresaRepository = empresaRepository;
        this.repartidorRepository = repartidorRepository;
    }

    public PedidoResponseDTO crearPedido(Long clienteId, PedidoRegistroDTO dto) {

        // Validar que el Cliente existe
        if (!clienteRepository.buscarPorID(clienteId).isPresent()) {
            throw new RecursoNoEncontradoException("Cliente no encontrado");
        }

        // Validar autorizado
        Autorizado autorizado = autorizadoRepository.buscarPorID(dto.getAutorizadoId())
                .orElseThrow(() -> new RecursoNoEncontradoException("El autorizado indicado no existe"));

        // Verificar Pertenencia
        if (!autorizado.getClienteId().equals(clienteId)) {
            throw new DatosUsuarioNoCoincidenException("Error: Este autorizado no pertenece a su lista de confianza");
        }

        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setClienteId(clienteId);
        nuevoPedido.setAutorizadoId(dto.getAutorizadoId());
        nuevoPedido.setDescripcion(dto.getDescripcion());
        nuevoPedido.setDireccion(dto.getDireccion());
        nuevoPedido.setEstado("PENDIENTE");
        nuevoPedido.setFechaAlta(LocalDateTime.now());
        nuevoPedido.setNombreAutorizado(autorizado.getNombre());

        Pedido pedidoGuardado = pedidoRepository.guardar(nuevoPedido);

        PedidoResponseDTO respuesta = new PedidoResponseDTO();
        
        respuesta.setId(pedidoGuardado.getId());
        respuesta.setDescripcion(pedidoGuardado.getDescripcion());
        respuesta.setDireccion(pedidoGuardado.getDireccion());
        respuesta.setEstado(pedidoGuardado.getEstado());
        respuesta.setFechaAlta(pedidoGuardado.getFechaAlta());
        respuesta.setClienteId(pedidoGuardado.getClienteId());
        respuesta.setAutorizadoId(pedidoGuardado.getAutorizadoId());
        
        respuesta.setNombreAutorizado(autorizado.getNombre());

        return respuesta;
    }
    
    public PedidoResponseDTO asignarRepartidor(Long empresaId, Long pedidoId, AsignarRepartidorDTO dto) {

        // Validar que la Empresa existe
        if (!empresaRepository.buscarPorID(empresaId).isPresent()) {
            throw new RecursoNoEncontradoException("Empresa con id " + empresaId + " no encontrada");
        }

        // Validar que el Pedido existe
        Pedido pedido = pedidoRepository.buscarPorID(pedidoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("El pedido con id " + pedidoId + " no existe"));

        // Validar que el Repartidor existe
        Repartidor repartidor = repartidorRepository.buscarPorID(dto.getRepartidorId())
                .orElseThrow(() -> new RecursoNoEncontradoException("El repartidor con id " + dto.getRepartidorId() + " no existe"));
        
        // El repartidor trabaja para esta empresa??
        if (!repartidor.getEmpresaId().equals(empresaId)) {
            throw new DatosUsuarioNoCoincidenException("Error: Este repartidor no pertenece a tu empresa.");
        }

        // El repartidor está activo??
        if (!repartidor.getEstado().equalsIgnoreCase("activo")) {
            throw new IllegalStateException("Error: El repartidor no está activo y no puede recibir pedidos.");
        }

        // Asignar y cambiar el estado
        pedido.setRepartidorId(repartidor.getId());
        pedido.setEstado("EN_REPARTO");
        
        Pedido pedidoGuardado = pedidoRepository.guardar(pedido);
        
        PedidoResponseDTO respuesta = new PedidoResponseDTO();
        respuesta.setId(pedidoGuardado.getId());
        respuesta.setAutorizadoId(pedidoGuardado.getAutorizadoId());
        respuesta.setClienteId(pedidoGuardado.getClienteId());
        respuesta.setDescripcion(pedidoGuardado.getDescripcion());
        respuesta.setDireccion(pedidoGuardado.getDireccion());
        respuesta.setEstado(pedidoGuardado.getEstado());
        respuesta.setFechaAlta(pedidoGuardado.getFechaAlta());
        respuesta.setNombreAutorizado(pedidoGuardado.getNombreAutorizado());
        
        return respuesta;
    }
    
    public List<PedidoResponseDTO> listarPedidosPorCliente(Long clienteId) {
    	
    	// Validar que el cliente existe
    	if(!clienteRepository.buscarPorID(clienteId).isPresent()) {
    		throw new RecursoNoEncontradoException("El cliente con id " + clienteId + " no existe");
    	}
    	
    	// Obtener los pedidos
    	List<Pedido> pedidos = pedidoRepository.obtenerTodos();

    	List<PedidoResponseDTO> listaRespuesta = new ArrayList<>();
    	
    	// Coger solo los pedidos de este cliente
    	for (Pedido p : pedidos) {
			if(p.getClienteId().equals(clienteId)) {
				PedidoResponseDTO respuesta = new PedidoResponseDTO();
				respuesta.setId(p.getId());
				respuesta.setAutorizadoId(p.getAutorizadoId());
				respuesta.setClienteId(p.getClienteId());
				respuesta.setDescripcion(p.getDescripcion());
				respuesta.setDireccion(p.getDireccion());
				respuesta.setEstado(p.getEstado());
				respuesta.setFechaAlta(p.getFechaAlta());
				respuesta.setNombreAutorizado(p.getNombreAutorizado());
				
				listaRespuesta.add(respuesta);
			}
		}
    	
    	return listaRespuesta;
    	
    }
    
    
    public PedidoResponseDTO actualizarEstadoPedido(Long pedidoId, ActualizarEstadoPedidoDTO dto) {

    	Optional<Pedido> pedido = pedidoRepository.buscarPorID(pedidoId);
    	
        // Buscar el pedido
        if(!pedido.isPresent()) {
        	throw new RecursoNoEncontradoException("El pedido con id " + pedidoId + " no existe");
        }

        // El que intenta cambiar el estado es el repartidor asignado a este pedido??
        if (pedido.get().getRepartidorId() == null || !pedido.get().getRepartidorId().equals(dto.getRepartidorId())) {
            throw new DatosUsuarioNoCoincidenException("Error: No tienes permiso para modificar este pedido (o no está asignado a ti).");
        }

        // Validar el estado
        String estadoLimpio = dto.getEstado().toUpperCase();
        
        if (!estadoLimpio.equals("PENDIENTE") && 
            !estadoLimpio.equals("EN_REPARTO") && 
            !estadoLimpio.equals("ENTREGADO")) {
            throw new IllegalArgumentException("Estado no válido. Solo se permite: PENDIENTE, EN_REPARTO, ENTREGADO.");
        }

        // Actualizar estado
        pedido.get().setEstado(estadoLimpio);
        Pedido pedidoGuardado = pedidoRepository.guardar(pedido.get());

        Autorizado autorizado = autorizadoRepository.buscarPorID(pedido.get().getAutorizadoId()).orElse(null);
        
        PedidoResponseDTO respuesta = new PedidoResponseDTO();
        respuesta.setId(pedidoGuardado.getId());
        respuesta.setAutorizadoId(pedidoGuardado.getAutorizadoId());
        respuesta.setClienteId(pedidoGuardado.getClienteId());
        respuesta.setDescripcion(pedidoGuardado.getDescripcion());
        respuesta.setDireccion(pedidoGuardado.getDireccion());
        respuesta.setEstado(pedidoGuardado.getEstado());
        respuesta.setFechaAlta(pedidoGuardado.getFechaAlta());
        respuesta.setNombreAutorizado(autorizado.getNombre());
        
        return respuesta;
        
    }
    
}
