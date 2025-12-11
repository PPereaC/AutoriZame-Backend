package com.autorizame.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ActualizarEstadoPedidoDTO {

    @NotNull(message = "Debes identificarte con tu ID de repartidor")
    private Long repartidorId;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;
    
    public ActualizarEstadoPedidoDTO() {}

	public ActualizarEstadoPedidoDTO(Long repartidorId, String nuevoEstado) {
		this.repartidorId = repartidorId;
		this.estado = nuevoEstado;
	}
	
	// Getters y Setters

	public Long getRepartidorId() {
		return repartidorId;
	}

	public void setRepartidorId(Long repartidorId) {
		this.repartidorId = repartidorId;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}   

}