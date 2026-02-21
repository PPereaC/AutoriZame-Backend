package com.autorizame.models.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(nullable = false, unique = true, length = 150)
	private String email;

	@Column(nullable = false, length = 255)
	private String contrasena;

	@Column(name = "direccion_ethereum", length = 100)
	private String direccionEthereum;

	@Column(name = "fecha_registro", nullable = false)
	private LocalDateTime fechaRegistro;

	public Cliente() {
		this.fechaRegistro = LocalDateTime.now();
	}

	public Cliente(Long id, String nombre, String email, String contrasena, String direccionEthereum,
			LocalDateTime fechaRegistro) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.contrasena = contrasena;
		this.direccionEthereum = direccionEthereum;
		this.fechaRegistro = fechaRegistro != null ? fechaRegistro : LocalDateTime.now();
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

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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
