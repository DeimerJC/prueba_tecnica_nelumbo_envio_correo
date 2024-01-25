package com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "mail_history")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MailHistoryEntity {
	
    private String email;

    private String message;
    
    private String plate;
    
    @Field(name = "parking_name")
    private String parkingName;

    private Date creation;
    
}
