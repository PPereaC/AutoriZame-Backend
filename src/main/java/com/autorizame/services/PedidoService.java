package com.autorizame.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.autorizame.exception.DatosUsuarioNoCoincidenException;
import com.autorizame.exception.RecursoNoEncontradoException;
import com.autorizame.models.dto.PedidoRegistroDTO;
import com.autorizame.models.dto.PedidoResponseDTO;
import com.autorizame.models.entity.Autorizado;
import com.autorizame.models.entity.Pedido;
import com.autorizame.repository.AutorizadoRepository;
import com.autorizame.repository.ClienteRepository;
import com.autorizame.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final AutorizadoRepository autorizadoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, AutorizadoRepository autorizadoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.autorizadoRepository = autorizadoRepository;
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
}
