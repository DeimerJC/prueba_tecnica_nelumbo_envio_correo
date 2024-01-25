package com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.adapter;

import java.util.Date;
import java.util.List;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.model.MailHistoryModel;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.spi.IMailHistoryPersistencePort;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.entity.MailHistoryEntity;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.mapper.IMailHistoryEntityMapper;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.repository.IMailHistoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MailHistoryMongoAdapter implements IMailHistoryPersistencePort {

    private final IMailHistoryRepository iMailHistoryRepository;
    private final IMailHistoryEntityMapper iMailHistoryEntityMapper;
    
	@Override
	public Date getDayMostMailsSent() {
		Integer in = iMailHistoryRepository.findDayWithMostRecords();
		System.err.println("*****"+ in);
		return null;
	}
	@Override
	public MailHistoryModel getUserMostMailsSent() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MailHistoryModel filter(Date dateFrom, Date dateUntil, String email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MailHistoryModel save(MailHistoryModel mailHistoryModel) {
		MailHistoryEntity mailHistoryEntity = iMailHistoryEntityMapper.toEntity(mailHistoryModel); 
		mailHistoryEntity.setCreation(new Date());
		
		return iMailHistoryEntityMapper.toModel(
				iMailHistoryRepository.save(mailHistoryEntity));
	}
    
}