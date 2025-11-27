package com.autorizame.models.entity;

import java.time.LocalDateTime;

public class Autorizado {

	private Long id;
	private String nombre;
	private String dni;
	private String direccionEthereum;
	private String telefono;
	private LocalDateTime fechaRegistro;
	private Long clienteId;
	
	public Autorizado() {}

	public Autorizado(Long id, String nombre, String dni, String direccionEthereum, Long clienteId, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.direccionEthereum = direccionEthereum;
		this.clienteId = clienteId;
		this.telefono = telefono;
	}
	
	// Getters y Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccionEthereum() {
		return direccionEthereum;
	}

	public void setDireccionEthereum(String direccionEthereum) {
		this.direccionEthereum = direccionEthereum;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
