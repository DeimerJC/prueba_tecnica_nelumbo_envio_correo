package com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.spi;

import java.util.Date;
import java.util.List;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.model.MailHistoryModel;

public interface IMailHistoryPersistencePort {
	
	Date getDayMostMailsSent();

	String getUserMostMailsSent();

	List<MailHistoryModel> filter(Date dateFrom, Date dateUntil, String email); 
	
	MailHistoryModel save(MailHistoryModel mailHistoryModel);

}
