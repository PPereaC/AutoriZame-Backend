package com.autorizame.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AutorizadoRegistroDTO {

	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;
	
	@Pattern(
            regexp = "^[0-9]{8}[A-Z]$",
            message = "El DNI debe de ser válido"
    )
	private String dni;
	
	@NotBlank(message = "La dirección de Ethereum es obligatoria")
	private String direccionEthereum;
	
	@NotNull(message = "El teléfono no puede estar vacío")
	@Pattern(
		regexp = "^(\\+34|0034|34)?[6|7|9][0-9]{8}$",
		message = "El teléfono debe de ser válido"
	)
	private String telefono;

	// Getters y Setters
	
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
