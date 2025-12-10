package com.autorizame.models.entity;

import java.time.LocalDateTime;

public class Pedido {
	
    private Long id;
    private Long clienteId;
    private Long autorizadoId;
    private String descripcion;
    private String direccion;
    private String estado;
    private LocalDateTime fechaAlta;
    private Long repartidorId;
    private String nombreAutorizado;
    
    public Pedido() {}
    
	public Pedido(Long id, Long clienteId, Long autorizadoId, String descripcion, String direccion, String estado,
			LocalDateTime fechaAlta, Long repartidorId, String nombreAutorizado) {
		this.id = id;
		this.clienteId = clienteId;
		this.autorizadoId = autorizadoId;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.estado = estado;
		this.fechaAlta = fechaAlta;
		this.repartidorId = repartidorId;
	}
	
	// Getters y Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getRepartidorId() {
		return repartidorId;
	}

	public void setRepartidorId(Long repartidorId) {
		this.repartidorId = repartidorId;
	}

	public String getNombreAutorizado() {
		return nombreAutorizado;
	}

	public void setNombreAutorizado(String nombreAutorizado) {
		this.nombreAutorizado = nombreAutorizado;
	}
    
}
