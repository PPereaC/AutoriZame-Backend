package com.autorizame.exception;

public class DatosUsuarioNoCoincidenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DatosUsuarioNoCoincidenException(String mensaje) {
		super(mensaje);
	}

}
