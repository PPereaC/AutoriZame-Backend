package com.autorizame.models.dto;

import java.time.LocalDateTime;

public class PedidoResponseDTO {

    private Long id;
    private String descripcion;
    private String direccion;
    private String estado;
    private LocalDateTime fechaAlta;
    private Long clienteId;
    private Long autorizadoId;
    private String nombreAutorizado;

    public PedidoResponseDTO() {}

	public PedidoResponseDTO(Long id, String descripcion, String direccion, String estado, LocalDateTime fechaAlta,
			Long clienteId, Long autorizadoId, String nombreAutorizado) {
		this.id = id;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.estado = estado;
		this.fechaAlta = fechaAlta;
		this.clienteId = clienteId;
		this.autorizadoId = autorizadoId;
		this.nombreAutorizado = nombreAutorizado;
	}
	
	// Getters y Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getAutorizadoId() {
		return autorizadoId;
	}

	public void setAutorizadoId(Long autorizadoId) {
		this.autorizadoId = autorizadoId;
	}

	public String getNombreAutorizado() {
		return nombreAutorizado;
	}

	public void setNombreAutorizado(String nombreAutorizado) {
		this.nombreAutorizado = nombreAutorizado;
	}
    
}
