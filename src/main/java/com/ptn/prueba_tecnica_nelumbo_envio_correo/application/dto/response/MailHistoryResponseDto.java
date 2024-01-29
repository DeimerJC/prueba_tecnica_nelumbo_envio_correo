package com.ptn.prueba_tecnica_nelumbo_envio_correo.application.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailHistoryResponseDto {
	
    private String email;

	private String plate;
	
	private String message;
	
	private String parkingName;

    private Date creation;

}
