package com.postcare.seguimiento_postoperatorio_ms.repository;

import com.postcare.seguimiento_postoperatorio_ms.model.Cirugia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CirugiaRepository extends MongoRepository<Cirugia, String>{
}
