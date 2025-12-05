package com.autorizame.exception;

public class EmpresaDuplicadaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmpresaDuplicadaException(String mensaje) {
		super(mensaje);
	}

}
