package com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.request.SendMailRequestDto;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.api.ISendMailServicePort;

public class SendMailUseCase implements ISendMailServicePort {

	private static final Logger logger = LoggerFactory.getLogger(SendMailUseCase.class);

	@Override
	public String sendMail(SendMailRequestDto sendMailRequestDto) {
		logger.info("Objeto recibido: {}", sendMailRequestDto);
		return "Correo Enviado";
	}

}