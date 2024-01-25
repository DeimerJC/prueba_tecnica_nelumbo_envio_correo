package com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.api;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.model.SendMailModel;

public interface ISendMailServicePort {
	
	String sendMail(SendMailModel sendMailModel);   
    
}