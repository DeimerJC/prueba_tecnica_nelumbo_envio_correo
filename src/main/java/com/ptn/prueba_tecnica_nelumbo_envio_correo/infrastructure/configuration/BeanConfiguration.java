package com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.api.ISendMailServicePort;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.usecase.SendMailUseCase;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
	
//    private final IUserRepository iUserRepository;
    
    @Bean
    public ISendMailServicePort iUserServicePort() {
        return new SendMailUseCase();
    }
    
}