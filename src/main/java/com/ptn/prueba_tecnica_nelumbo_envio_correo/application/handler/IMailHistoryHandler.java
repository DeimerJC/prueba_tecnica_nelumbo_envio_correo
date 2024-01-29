package com.ptn.prueba_tecnica_nelumbo_envio_correo.application.handler;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.response.MailHistoryResponseDto;

public interface IMailHistoryHandler {
	
	Map<String, Date> getDayMostMailsSent();

	Map<String, String> getUserMostMailsSent();

	List<MailHistoryResponseDto> filter(Date dateFrom, Date dateUntil, String email);   

}