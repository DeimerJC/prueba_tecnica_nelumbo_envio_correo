package com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.entity.MailHistoryEntity;

public interface IMailHistoryRepository extends MongoRepository<MailHistoryEntity, Long> {

	@Query("{$group: {_id: {$dayOfMonth: '$creation'}, count: {$sum: 1}}}, {$sort: {count: -1}}, {$limit: 1}")
    Integer findDayWithMostRecords();

}