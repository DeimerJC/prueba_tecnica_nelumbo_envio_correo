 package com.ptn.prueba_tecnica_nelumbo_envio_correo.application.handler.impl;

import org.springframework.stereotype.Service;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.request.SendMailRequestDto;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.response.MessageResponseDto;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.handler.ISendMailHandler;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.mapper.ISendMailRequestMapper;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.api.ISendMailServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SendMailHandler implements ISendMailHandler {

    private final ISendMailServicePort iSendMailServicePort;
    private final ISendMailRequestMapper iSendMailRequestMapper;
    
	@Override
	public MessageResponseDto sendMail(SendMailRequestDto sendMailRequestDto) {
		return new MessageResponseDto(
				iSendMailServicePort.sendMail(
						iSendMailRequestMapper.toModel(sendMailRequestDto)));
	}

}