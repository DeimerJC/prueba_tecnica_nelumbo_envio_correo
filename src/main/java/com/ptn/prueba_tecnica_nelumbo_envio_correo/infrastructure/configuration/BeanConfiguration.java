package com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.api.IMailHistoryServicePort;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.api.ISendMailServicePort;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.spi.IMailHistoryPersistencePort;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.usecase.MailHistoryUseCase;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.usecase.SendMailUseCase;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.adapter.MailHistoryMongoAdapter;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.mapper.IMailHistoryEntityMapper;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.repository.IMailHistoryRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
	
    private final IMailHistoryRepository iMailHistoryRepository;
	private final IMailHistoryEntityMapper iMailHistoryEntityMapper;

    private final MongoTemplate mongoTemplate;
	
	@Bean
    public IMailHistoryPersistencePort iMailHistoryPersistencePort() {
        return new MailHistoryMongoAdapter(iMailHistoryRepository, iMailHistoryEntityMapper, mongoTemplate);
    }

    @Bean
    public IMailHistoryServicePort iMailHistoryServicePort() {
        return new MailHistoryUseCase(iMailHistoryPersistencePort());
    }
    
    @Bean
    public ISendMailServicePort iUserServicePort() {
        return new SendMailUseCase(iMailHistoryServicePort(), iMailHistoryEntityMapper);
    }
    
}