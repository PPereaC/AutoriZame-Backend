package com.autorizame.models.dto;

import jakarta.validation.constraints.NotNull;

public class AsignarRepartidorDTO {

	@NotNull(message = "El ID del repartidor es obligatorio")
    private Long repartidorId;
	
	public AsignarRepartidorDTO() {}
	
	public AsignarRepartidorDTO(Long repartidorId) {
		this.repartidorId = repartidorId;
	}

	public Long getRepartidorId() {
		return repartidorId;
	}

	public void setRepartidorId(Long repartidorId) {
		this.repartidorId = repartidorId;
	}
	
}
