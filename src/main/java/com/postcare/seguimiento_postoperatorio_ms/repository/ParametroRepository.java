package com.postcare.seguimiento_postoperatorio_ms.repository;

import com.postcare.seguimiento_postoperatorio_ms.model.Parametro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParametroRepository extends MongoRepository<Parametro, String> {
}
