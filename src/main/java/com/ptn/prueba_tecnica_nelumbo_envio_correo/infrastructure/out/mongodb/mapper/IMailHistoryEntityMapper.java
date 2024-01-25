package com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.model.MailHistoryModel;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.model.SendMailModel;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.entity.MailHistoryEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IMailHistoryEntityMapper {

	MailHistoryEntity toEntity(MailHistoryModel mailHistoryModel);
	
	MailHistoryModel toModel(MailHistoryEntity mailHistoryEntity);
	
	MailHistoryModel toModel(SendMailModel sendMailModel);
	
    List<MailHistoryModel> toModelList(List<MailHistoryEntity> mailHistoryEntityList);
}