package com.autorizame.exception;

public class AutorizadoDuplicadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AutorizadoDuplicadoException(String mensaje) {
		super(mensaje);
	}

}
