package com.ptn.prueba_tecnica_nelumbo_envio_correo.application.handler;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.request.SendMailRequestDto;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.response.MessageResponseDto;

public interface ISendMailHandler {
	
	MessageResponseDto sendMail(SendMailRequestDto sendMailRequestDto);  

}