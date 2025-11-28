package com.autorizame.models.dto;

public class ListarAutorizadosDTO {

	private Long id;
	private String nombre;
	private String dni;
	private String telefono;
	
	public ListarAutorizadosDTO() {}

	public ListarAutorizadosDTO(Long id, String nombre, String dni, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
	}

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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
