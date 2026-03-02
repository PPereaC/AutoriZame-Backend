package com.autorizame.models.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "autorizado_id")
    private Long autorizadoId;

    @Column(length = 500)
    private String descripcion;

    @Column(length = 255)
    private String direccion;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;

    @Column(name = "repartidor_id")
    private Long repartidorId;

    @Column(name = "nombre_autorizado", length = 100)
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
