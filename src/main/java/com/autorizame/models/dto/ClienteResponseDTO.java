package com.autorizame.models.dto;

import java.time.LocalDateTime;

public class ClienteResponseDTO {

	private Long id;
	private String nombre;
	private String email;
	private String direccionEthereum;
	private LocalDateTime fechaRegistro;
	
	public ClienteResponseDTO() {}

	public ClienteResponseDTO(Long id, String nombre, String email, String direccionEthereum,
			LocalDateTime fechaRegistro) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.direccionEthereum = direccionEthereum;
		this.fechaRegistro = fechaRegistro;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccionEthereum() {
		return direccionEthereum;
	}

	public void setDireccionEthereum(String direccionEthereum) {
		this.direccionEthereum = direccionEthereum;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
}
