package com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.adapter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;

import com.mongodb.client.AggregateIterable;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.model.MailHistoryModel;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.domain.spi.IMailHistoryPersistencePort;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.adapter.MailHistoryMongoAdapter.FechaConMasRegistros;
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

//		MatchOperation matchOperation = Aggregation.match(Criteria.where("creation").exists(true).ne(null));
//
//        GroupOperation groupOperation = Aggregation.group(
//                Fields.from(
//                        Fields.field("year", DateOperators.Year.year("$creation").toString()),
//                        Fields.field("month", DateOperators.Month.month("$creation").toString()),
//                        Fields.field("day", DateOperators.DayOfMonth.dayOfMonth("$creation").toString())
//                )
//        ).count().as("count");
//
//        SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC, "count");
//
//        LimitOperation limitOperation = Aggregation.limit(1);
//
//        ProjectionOperation projectionOperation = Aggregation.project()
//                .andExclude("_id")
//                .andExpression("year").as("year")
//                .andExpression("month").as("month")
//                .andExpression("day").as("day")
//                .and(
//                        DateOperators.DateToString.dateToString(
//                                DateOperators.DateFromParts.dateFromParts()
//                                        .year("$year")
//                                        .month("$month")
//                                        .day("$day")
//                                        .hour(0)
//                                        .minute(0)
//                                        .second(0)
//                                        .millisecond(0)
//                        ).toString()).as("mostRecordsDate");
//
//        Aggregation aggregation = Aggregation.newAggregation(
//                matchOperation,
//                groupOperation,
//                sortOperation,
//                limitOperation
//        );
//
//        AggregationResults<MostRecordsDateProjection> results = mongoTemplate.aggregate(aggregation, "mail_history", MostRecordsDateProjection.class);
//        
//        for (MostRecordsDateProjection document : results) {
//            System.err.println("fecha : "+ document.getDay());
//        }
		
		
		
//        GroupOperation groupOperation = Aggregation.group(
//                Fields.from(Fields.field("fechaFormateada", Aggregation.project().andExpression("{$dateToString: {format: '%Y-%m-%d', date: '$fecha'}}").toString())
//        )).count().as("cantidadRegistros");
//
//        TypedAggregation<MailHistoryEntity> aggregation = Aggregation.newAggregation(MailHistoryEntity.class, groupOperation);
//
//        AggregationResults<ReportePorDia> results = mongoTemplate.aggregate(aggregation, "mail_history", ReportePorDia.class);
		
		
		
//		AggregationOperation group = Aggregation.group("creation")
//                .count()
//                .as("cantidad");
//		
//		AggregationOperation project = Aggregation.project("creation", "cantidad")
//                .andExpression("dateToString('%Y-%m-%d', creation)")
//                .as("fecha_sin_hora");
//		
//        AggregationOperation sort = Aggregation.sort(Sort.Direction.DESC, "cantidad");
//        
//        AggregationOperation limit = Aggregation.limit(1);
//        
//        Aggregation aggregation = Aggregation.newAggregation(group, sort, limit, project);
//        
//        AggregationResults<FechaConMasRegistros> result =
//                mongoTemplate.aggregate(aggregation, "mail_history", FechaConMasRegistros.class);
//        
//        List<FechaConMasRegistros> results = result.getMappedResults();
//        
//        System.err.println(" "+results.get(0).getFecha());
//        System.err.println(" "+results.get(0).getFecha_sin_hora());
//        System.err.println(" "+results.get(0).getCantidad());
		
		
		
		// Proyección para extraer solo la fecha de la creación (sin la hora)
        ProjectionOperation projection = Aggregation.project()
        .andExpression("{$dateToString: { format: '%Y-%m-%d', date: '$creation' }}").as("fecha");

        // Agrupación por fecha y contando los documentos por cada fecha
        GroupOperation group = Aggregation.group("fecha").count().as("cantidad");

        // Ordenar por cantidad descendente para obtener el día con más registros primero
        SortOperation sort = Aggregation.sort(Sort.by(Sort.Order.desc("cantidad")));

        // Agregación final
        Aggregation aggregation = Aggregation.newAggregation(projection, group, sort);

        // Ejecutar la agregación y obtener los resultados
        AggregationResults<ConteoPorDia> results = mongoTemplate.aggregate(aggregation, "mail_history", ConteoPorDia.class);

        
        for (ConteoPorDia conteoPorDia : results) {
        	System.err.println(" "+conteoPorDia.getFecha());
        	System.err.println(" "+conteoPorDia.getCantidad());
		}
        
		
		
//		List<ConteoPorDia> n =iMailHistoryRepository.findFechaConMasRegistros();
//		for (ConteoPorDia fechaConMasRegistros : n) {
//			System.err.println("2 "+fechaConMasRegistros.getFecha());
//			System.err.println(" "+fechaConMasRegistros.getCantidad());
//		}
		
		return null;
	}
	
	@Override
	public MailHistoryModel getUserMostMailsSent() {
		
		AggregateIterable<Document> result = mongoTemplate.getCollection("mail_history").aggregate(
                Arrays.asList(
                		new Document("$group",
                				new Document("_id", "$email")
                					.append("count", new Document("$sum", 1))),
                        new Document("$sort", new Document("count", -1)), 
                        new Document("$limit", 1)
                        )
                );
        
        for (Document document : result) {
            String email = document.getString("_id");
            int cantidad = document.getInteger("count");
            System.err.println(email+" : "+ cantidad);
        }
        
		return null;
	}
	
	@Override
	public MailHistoryModel filter(Date dateFrom, Date dateUntil, String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public MailHistoryModel save(MailHistoryModel mailHistoryModel) {
		MailHistoryEntity mailHistoryEntity = iMailHistoryEntityMapper.toEntity(mailHistoryModel); 
		mailHistoryEntity.setCreation(new Date());
		
		return iMailHistoryEntityMapper.toModel(
				mongoTemplate.insert(mailHistoryEntity));
	}
	
	public class ConteoPorDia {

	    private Date fecha;
	    private Long cantidad;
	    
		public Date getFecha() {
			return fecha;
		}
		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}
		public Long getCantidad() {
			return cantidad;
		}
		public void setCantidad(Long cantidad) {
			this.cantidad = cantidad;
		}

	}
	
	public class FechaConMasRegistros {
	    private Date creation;
	    private String fecha_sin_hora;
	    private Integer cantidad;
	    
		public Date getFecha() {
			return creation;
		}
		public void setFecha(Date creation) {
			this.creation = creation;
		}
		public Integer getCantidad() {
			return cantidad;
		}
		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}
		public String getFecha_sin_hora() {
			return fecha_sin_hora;
		}
		public void setFecha_sin_hora(String fecha_sin_hora) {
			this.fecha_sin_hora = fecha_sin_hora;
		}

	}
	
	public class ReportePorDia {
	    private String fechaFormateada;
	    private long cantidadRegistros;
		public String getFechaFormateada() {
			return fechaFormateada;
		}
		public void setFechaFormateada(String fechaFormateada) {
			this.fechaFormateada = fechaFormateada;
		}
		public long getCantidadRegistros() {
			return cantidadRegistros;
		}
		public void setCantidadRegistros(long cantidadRegistros) {
			this.cantidadRegistros = cantidadRegistros;
		}

	}
	
	public class MostRecordsDateProjection {
		private Integer year;
		private Integer month;
		private Integer day;
		private Integer count;

		public Integer getYear() {
			return year;
		}

		public void setYear(Integer year) {
			this.year = year;
		}

		public Integer getMonth() {
			return month;
		}

		public void setMonth(Integer month) {
			this.month = month;
		}

		public Integer getDay() {
			return day;
		}

		public void setDay(Integer day) {
			this.day = day;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}
	}
}
