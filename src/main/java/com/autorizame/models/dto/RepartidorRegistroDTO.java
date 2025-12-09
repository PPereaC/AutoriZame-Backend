package com.autorizame.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RepartidorRegistroDTO {

	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;
	
	@NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo no es válido")
	private String correo;
	
	@NotNull(message = "El teléfono no puede estar vacío")
	@Pattern(
		regexp = "^(\\+34|0034|34)?[6|7|9][0-9]{8}$",
		message = "El teléfono debe de ser válido"
	)
	private String telefono;

	@NotBlank(message = "El estado es obligatorio")
	private String estado;

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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
