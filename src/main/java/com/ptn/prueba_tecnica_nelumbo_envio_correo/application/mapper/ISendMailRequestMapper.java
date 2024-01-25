package com.ptn.prueba_tecnica_nelumbo_envio_correo.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.request.SendMailRequestDto;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.model.SendMailModel;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ISendMailRequestMapper {
	
	SendMailModel toModel(SendMailRequestDto sendMailRequestDto);

}
