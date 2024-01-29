package com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.entity.MailHistoryEntity;

public interface IMailHistoryRepository extends MongoRepository<MailHistoryEntity, Long> {
	
	
	
}