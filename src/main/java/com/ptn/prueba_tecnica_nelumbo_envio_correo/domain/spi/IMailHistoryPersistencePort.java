package com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.spi;

import java.util.Date;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.model.MailHistoryModel;

public interface IMailHistoryPersistencePort {
	
	Date getDayMostMailsSent();

	MailHistoryModel getUserMostMailsSent();

	MailHistoryModel filter(Date dateFrom, Date dateUntil, String email); 
	
	MailHistoryModel save(MailHistoryModel mailHistoryModel);

}
