package com.postcare.seguimiento_postoperatorio_ms.repository;

import com.postcare.seguimiento_postoperatorio_ms.model.Parametros;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParametrosRepository extends MongoRepository<Parametros, String> {
}
