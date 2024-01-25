package com.ptn.prueba_tecnica_nelumbo_envio_correo.application.handler.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.response.MailHistoryResponseDto;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.handler.IMailHistoryHandler;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.mapper.IMailHistoryResponseMapper;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.api.IMailHistoryServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailHistoryHandler implements IMailHistoryHandler{

    private final IMailHistoryServicePort iMailHistoryServicePort;
    private final IMailHistoryResponseMapper iMailHistoryResponseMapper;
	
	@Override
	public Map<String, Date> getDayMostMailsSent() {
		Map<String, Date> resultMap = new HashMap<String, Date>();
		resultMap.put("Date", iMailHistoryServicePort.getDayMostMailsSent());
		return resultMap;
	}

	@Override
	public MailHistoryResponseDto getUserMostMailsSent() {
		return iMailHistoryResponseMapper.toResponse( 
				iMailHistoryServicePort.getUserMostMailsSent() 
			);
	}

	@Override
	public MailHistoryResponseDto filter(Date dateFrom, Date dateUntil, String email) {
		return iMailHistoryResponseMapper.toResponse( 
				iMailHistoryServicePort.filter(dateFrom, dateUntil, email) 
			);
	}

}
