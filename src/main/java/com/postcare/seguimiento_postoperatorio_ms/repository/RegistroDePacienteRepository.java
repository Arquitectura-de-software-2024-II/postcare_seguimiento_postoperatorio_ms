package com.postcare.seguimiento_postoperatorio_ms.repository;

import com.postcare.seguimiento_postoperatorio_ms.model.RegistroDePaciente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegistroDePacienteRepository extends MongoRepository<RegistroDePaciente, String> {

}
