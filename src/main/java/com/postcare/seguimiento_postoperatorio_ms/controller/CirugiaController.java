package com.postcare.seguimiento_postoperatorio_ms.controller;

import com.postcare.seguimiento_postoperatorio_ms.model.Cirugia;
import com.postcare.seguimiento_postoperatorio_ms.model.RegistroDePaciente;
import com.postcare.seguimiento_postoperatorio_ms.repository.CirugiaRepository;
import com.postcare.seguimiento_postoperatorio_ms.repository.RegistroDePacienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Registro de Cirugias", description = "API para gestionar las cirugias preestablecidas.")
public class CirugiaController {
    @Autowired
    private CirugiaRepository cirugiaRepository;
    @GetMapping("/cirugias")
    @Operation(
            summary = "Obtener todas las posibles cirugias",
            description = "Este endpoint devuelve una lista de todas las posibles cirugias (estándar) y sus tipos."
    )
    public List<Cirugia> obtenerTodosLosRegistros() {
        return cirugiaRepository.findAll();
    }

}
