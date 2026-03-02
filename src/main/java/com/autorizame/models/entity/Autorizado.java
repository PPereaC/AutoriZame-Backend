package com.autorizame.models.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "autorizados")
public class Autorizado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(nullable = false, unique = true, length = 20)
	private String dni;

	@Column(name = "direccion_ethereum", length = 100)
	private String direccionEthereum;

	@Column(length = 20)
	private String telefono;

	@Column(name = "fecha_registro")
	private LocalDateTime fechaRegistro;

	@Column(name = "cliente_id", nullable = false)
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
