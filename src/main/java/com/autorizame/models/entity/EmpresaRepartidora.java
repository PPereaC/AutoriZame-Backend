package com.autorizame.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresas_repartidoras")
public class EmpresaRepartidora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 150)
	private String nombre;

	@Column(nullable = false, unique = true, length = 150)
	private String correo;

	@Column(length = 20)
	private String telefono;
	
	public EmpresaRepartidora() {}

	public EmpresaRepartidora(Long id, String nombre, String correo, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
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
	
}
