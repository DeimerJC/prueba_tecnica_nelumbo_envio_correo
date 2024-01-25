package com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.api.IMailHistoryServicePort;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.api.ISendMailServicePort;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.model.SendMailModel;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.mapper.IMailHistoryEntityMapper;

public class SendMailUseCase implements ISendMailServicePort {

	private static final Logger logger = LoggerFactory.getLogger(SendMailUseCase.class);
	
	private final IMailHistoryServicePort iMailHistoryServicePort;
	private final IMailHistoryEntityMapper iMailHistoryEntityMapper;

	public SendMailUseCase(IMailHistoryServicePort iMailHistoryServicePort,
			IMailHistoryEntityMapper iMailHistoryEntityMapper) {
		this.iMailHistoryServicePort = iMailHistoryServicePort;
		this.iMailHistoryEntityMapper = iMailHistoryEntityMapper;
	}

	@Override
	public String sendMail(SendMailModel sendMailModel) {
		logger.info("Objeto recibido: {}", sendMailModel);
		
		iMailHistoryServicePort.save(iMailHistoryEntityMapper.toModel(sendMailModel));
		
		return "Correo Enviado";
	}

}