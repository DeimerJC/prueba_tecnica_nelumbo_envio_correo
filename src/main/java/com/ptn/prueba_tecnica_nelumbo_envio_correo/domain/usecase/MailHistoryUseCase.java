package com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.usecase;

import java.util.Date;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.api.IMailHistoryServicePort;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.model.MailHistoryModel;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.spi.IMailHistoryPersistencePort;

public class MailHistoryUseCase implements IMailHistoryServicePort {

    private final IMailHistoryPersistencePort iMailHistoryPersistencePort;

    public MailHistoryUseCase(IMailHistoryPersistencePort iMailHistoryPersistencePort) {
        this.iMailHistoryPersistencePort = iMailHistoryPersistencePort;
    }

	@Override
	public Date getDayMostMailsSent() {
		return iMailHistoryPersistencePort.getDayMostMailsSent();
	}

	@Override
	public MailHistoryModel getUserMostMailsSent() {
		return iMailHistoryPersistencePort.getUserMostMailsSent();
	}

	@Override
	public MailHistoryModel filter(Date dateFrom, Date dateUntil, String email) {
		return iMailHistoryPersistencePort.filter(dateFrom, dateUntil, email);
	}

	@Override
	public MailHistoryModel save(MailHistoryModel mailHistoryModel) {
		return iMailHistoryPersistencePort.save(mailHistoryModel);
	}

}
