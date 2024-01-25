package com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.request;

import java.util.Date;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.configuration.Constants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailHistoryRequestDto {
	
	@NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    private String email;
	
	@NotNull(message = Constants.FIELD_NOT_NULL)
	private Date dateFrom;
	
	@NotNull(message = Constants.FIELD_NOT_NULL)
	private Date dateUntil;

}
