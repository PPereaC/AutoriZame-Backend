package com.autorizame.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "repartidores")
public class Repartidor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(nullable = false, unique = true, length = 150)
	private String correo;

	@Column(length = 20)
	private String telefono;

	@Column(name = "empresa_id", nullable = false)
	private Long empresaId;

	@Column(nullable = false, length = 20)
	private String estado;
	
	public Repartidor() {}

	public Repartidor(Long id, String nombre, String correo, String telefono, Long empresaId, String estado) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
		this.empresaId = empresaId;
		this.estado = estado;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Long getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
