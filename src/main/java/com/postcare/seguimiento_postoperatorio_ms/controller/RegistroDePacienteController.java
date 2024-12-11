package com.postcare.seguimiento_postoperatorio_ms.controller;

import com.postcare.seguimiento_postoperatorio_ms.model.RegistroDePaciente;
import com.postcare.seguimiento_postoperatorio_ms.repository.RegistroDePacienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Registro de Pacientes", description = "API para gestionar los registros de pacientes postoperatorios")
public class RegistroDePacienteController {

    @Autowired
    private RegistroDePacienteRepository registroDePacienteRepository;

    @GetMapping("/test")
    @Operation(summary = "Verificar que el servicio está en funcionamiento", description = "Este endpoint permite comprobar que el servicio está operativo.")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("El endpoint está funcionando correctamente");
    }

    @GetMapping("/")
    @Operation(
            summary = "Obtener todos los registros de pacientes",
            description = "Este endpoint devuelve una lista de todos los registros de pacientes almacenados en el sistema."
    )
    public List<RegistroDePaciente> obtenerTodosLosRegistros() {
        return registroDePacienteRepository.findAll();
    }

}
