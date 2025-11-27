package com.autorizame.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmailDuplicadoException.class)
	public ResponseEntity<Map<String, Object>> manejarEmailDuplicado(EmailDuplicadoException ex) {
		
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("timestamp", LocalDateTime.now());
		respuesta.put("mensaje", ex.getMessage());
		respuesta.put("estado", HttpStatus.CONFLICT.value());
		
		return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarNotFound(RecursoNoEncontradoException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("estado", HttpStatus.NOT_FOUND.value()); // 404
        
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(DatosUsuarioNoCoincidenException.class)
    public ResponseEntity<Map<String, Object>> manejarDatosUsuarioNoCoincide(DatosUsuarioNoCoincidenException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("estado", HttpStatus.CONFLICT); // 409
        
        return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(AutorizadoDuplicadoException.class)
    public ResponseEntity<Map<String, Object>> manejarAutorizadosDuplicados(AutorizadoDuplicadoException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("estado", HttpStatus.CONFLICT); // 409
        
        return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
    }

}
