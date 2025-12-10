package com.autorizame.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PedidoRegistroDTO {

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "Debes indicar el ID del autorizado que recogerá el pedido")
    private Long autorizadoId; // El "Destinatario"

    @NotBlank(message = "La dirección de recogida es obligatoria")
    private String direccion;
    
    private String nombreAutorizado;

    public PedidoRegistroDTO() {}

	public PedidoRegistroDTO(String descripcion, Long autorizadoId, String direccion, String nombreAutorizado) {
		this.descripcion = descripcion;
		this.autorizadoId = autorizadoId;
		this.direccion = direccion;
		this.nombreAutorizado = nombreAutorizado;
	}

	// Getters y Setters
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getAutorizadoId() {
		return autorizadoId;
	}

	public void setAutorizadoId(Long autorizadoId) {
		this.autorizadoId = autorizadoId;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombreAutorizado() {
		return nombreAutorizado;
	}

	public void setNombreAutorizado(String nombreAutorizado) {
		this.nombreAutorizado = nombreAutorizado;
	}
	
}
