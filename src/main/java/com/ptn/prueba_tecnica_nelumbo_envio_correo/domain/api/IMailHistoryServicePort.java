package com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.api;

import java.util.Date;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.model.MailHistoryModel;

public interface IMailHistoryServicePort {
	
	MailHistoryModel save(MailHistoryModel mailHistoryModel);
	
	Date getDayMostMailsSent();

	MailHistoryModel getUserMostMailsSent();

	MailHistoryModel filter(Date dateFrom, Date dateUntil, String email);    
    
}