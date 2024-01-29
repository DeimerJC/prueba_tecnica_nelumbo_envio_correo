package com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.exceptionhandler;

public enum ExceptionResponse {
	
    NO_DATA_FOUND("No data found for the requested petition."),
    BAD_REQUEST("Invalid request, the request could not be interpreted."),
    CONFLICT("The request has a conflict with the server."),
	DOMAIN("An internal error has occurred.");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
    
}