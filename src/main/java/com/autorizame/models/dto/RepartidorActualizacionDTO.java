package com.autorizame.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RepartidorActualizacionDTO {

	private Long id;
	private String correo;
	
	@NotNull(message = "El teléfono no puede estar vacío")
	@Pattern(
		regexp = "^(\\+34|0034|34)?[6|7|9][0-9]{8}$",
		message = "El teléfono debe de ser válido"
	)
	private String telefono;
	
	public RepartidorActualizacionDTO() {}

	public RepartidorActualizacionDTO(Long id, String correo, String telefono) {
		this.id = id;
		this.correo = correo;
		this.telefono = telefono;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
