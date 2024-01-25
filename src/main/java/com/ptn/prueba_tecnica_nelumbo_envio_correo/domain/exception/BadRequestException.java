package com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.exception;

public class BadRequestException extends RuntimeException {
	
    private static final long serialVersionUID = 4982913246781781042L;

	public BadRequestException() {
		super();
    }
	
	public BadRequestException(String message) {
        super(message);
    }
    
}
