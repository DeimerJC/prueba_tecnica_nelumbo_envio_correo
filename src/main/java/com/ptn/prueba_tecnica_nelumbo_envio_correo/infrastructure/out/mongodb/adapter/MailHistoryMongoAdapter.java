package com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.adapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import com.mongodb.client.AggregateIterable;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.exception.NoDataFoundException;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.model.MailHistoryModel;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.spi.IMailHistoryPersistencePort;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.entity.MailHistoryEntity;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.mapper.IMailHistoryEntityMapper;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.repository.IMailHistoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MailHistoryMongoAdapter implements IMailHistoryPersistencePort {

    private final IMailHistoryRepository iMailHistoryRepository;
    private final IMailHistoryEntityMapper iMailHistoryEntityMapper;
    private final MongoTemplate mongoTemplate;
    
	@Override
	public Date getDayMostMailsSent() {

		// Proyección para extraer solo la fecha de la creación (sin la hora)
        ProjectionOperation projection = Aggregation.project()
        		.andExpression("{$dateToString: { format: '%Y-%m-%d', date: '$creation' }}").as("_id");
		
        // Agrupación por fecha y contando los documentos por cada fecha
        GroupOperation group = Aggregation.group("_id").count().as("cantidad");

        // Ordenar por cantidad descendente para obtener el día con más registros primero
        SortOperation sort = Aggregation.sort(Sort.by(Sort.Order.desc("cantidad")));
      
        //limitar a un registro
        AggregationOperation limit = Aggregation.limit(1);

        // Agregación final
        Aggregation aggregation = Aggregation.newAggregation(projection, group, sort, limit);

        // Ejecutar la agregación y obtener los resultados
        AggregationResults<ConteoEnvio> results = mongoTemplate.aggregate(aggregation, "mail_history", ConteoEnvio.class);

        String date = "";
        
        if ((results.getMappedResults() != null) && (results.getMappedResults().size() > 0)) {
        	date = results.getMappedResults().get(0).get_id();
			return formatearFecha(date);
		} else {
			throw new NoDataFoundException("No se pudo encontrar informacion.");
		}
	}
	
	@Override
	public String getUserMostMailsSent() {
		
		AggregateIterable<Document> result = mongoTemplate.getCollection("mail_history").aggregate(
                Arrays.asList(
                		new Document("$group",
                				new Document("_id", "$email")
                					.append("cantidad", new Document("$sum", 1))),
                        new Document("$sort", new Document("cantidad", -1)), 
                        new Document("$limit", 1)
                        )
                );
        
        if (result.first() != null) {
        	return result.first().getString("_id");
		} else {
			throw new NoDataFoundException("No se pudo encontrar informacion.");
		}
	}
	
	@Override
	public List<MailHistoryModel> filter(Date dateFrom, Date dateUntil, String email) {
		List<AggregationOperation> aggregationOperations = new ArrayList<>();

		// Construir la expresión de filtrado
		Criteria criteria = new Criteria();
        if (dateFrom != null || dateUntil != null) {
            Criteria dateCriteria = Criteria.where("creation");
            if (dateFrom != null) {
                dateCriteria = dateCriteria.gte(dateFrom);
            }
            if (dateUntil != null) {
                dateCriteria = dateCriteria.lte(dateUntil);
            }
            criteria = criteria.andOperator(dateCriteria);
        }
        if (email != null) {
            criteria = criteria.orOperator(
                    Criteria.where("email").is(email),
                    Criteria.where("email").exists(false)
            );
        }

		// Agregar la operación de filtrado
		aggregationOperations.add(Aggregation.match(criteria));

		// Agregación final
		Aggregation aggregation = Aggregation.newAggregation(aggregationOperations);
		
		List<MailHistoryEntity> historyEntities = new ArrayList<MailHistoryEntity>();
		
		historyEntities = mongoTemplate
				.aggregate(aggregation, "mail_history", MailHistoryEntity.class).getMappedResults();

		if (historyEntities.size() > 0) {
			return iMailHistoryEntityMapper.toModelList(historyEntities);
		} else {
			throw new NoDataFoundException("No se pudo encontrar informacion.");
		}
	}
	
	@Override
	public MailHistoryModel save(MailHistoryModel mailHistoryModel) {
		MailHistoryEntity mailHistoryEntity = iMailHistoryEntityMapper.toEntity(mailHistoryModel); 
		mailHistoryEntity.setCreation(new Date());
		
		return iMailHistoryEntityMapper.toModel(
				mongoTemplate.insert(mailHistoryEntity));
	}
	
	public Date formatearFecha(String dateString) {
		// Crear un formateador para el string de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            // Parsear el string de fecha a LocalDate
            LocalDate localDate = LocalDate.parse(dateString, formatter);

            // Convertir LocalDate a java.util.Date
            Date utilDate = java.sql.Date.valueOf(localDate);

            return utilDate;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	public class ConteoEnvio {

	    private String _id;
	    private Long cantidad;
	    
	    public String get_id() {
	        return _id;
	    }
	    public void set_id(String _id) {
	        this._id = _id;
	    }
		public Long getCantidad() {
			return cantidad;
		}
		public void setCantidad(Long cantidad) {
			this.cantidad = cantidad;
		}

	}
	
}
