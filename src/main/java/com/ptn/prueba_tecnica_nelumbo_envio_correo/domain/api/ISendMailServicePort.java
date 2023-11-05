package com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.api;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.request.SendMailRequestDto;

public interface ISendMailServicePort {
	
	String sendMail(SendMailRequestDto sendMailRequestDto);   
    
}