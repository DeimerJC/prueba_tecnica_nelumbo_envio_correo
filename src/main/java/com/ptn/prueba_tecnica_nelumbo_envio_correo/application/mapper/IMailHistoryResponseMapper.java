package com.ptn.prueba_tecnica_nelumbo_envio_correo.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.response.MailHistoryResponseDto;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.model.MailHistoryModel;


@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IMailHistoryResponseMapper {
	
	MailHistoryResponseDto toResponse(MailHistoryModel mailHistoryModel);

    List<MailHistoryResponseDto> toResponseList(List<MailHistoryModel> mailHistoryModelList);

}
