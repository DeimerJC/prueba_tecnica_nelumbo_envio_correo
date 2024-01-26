package com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.adapter.MailHistoryMongoAdapter.ConteoPorDia;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.adapter.MailHistoryMongoAdapter.FechaConMasRegistros;
import com.ptn.prueba_tecnica_nelumbo_envio_correo.infrastructure.out.mongodb.entity.MailHistoryEntity;

public interface IMailHistoryRepository extends MongoRepository<MailHistoryEntity, Long> {

	// Método personalizado para realizar la agregación
//    @Aggregation(pipeline = {
//            "{ $group: { _id: '$creation', cantidad: { $sum: 1 } } }",
//            "{ $project: { _id: 0, fecha_sin_hora: { $dateToString: { format: '%Y-%m-%d', date: '$_id' } }, cantidad: 1 } }",
//            "{ $sort: { cantidad: -1 } }",
//            "{ $limit: 1 }"
//    })
	@Aggregation(pipeline = {"{" +
            "'$group': {" +
            "   '_id': {" +
            "       '$dateToString': {" +
            "           'format': '%Y-%m-%d'," +
            "           'date': '$creation'" +
            "       }" +
            "   }," +
            "   'total': {'$sum': 1}" +
            "}" +
            "}, " +
            "{$sort: {'total': -1} }," +
            "{$limit: 1}"})
    List<ConteoPorDia> findFechaConMasRegistros();
	
}