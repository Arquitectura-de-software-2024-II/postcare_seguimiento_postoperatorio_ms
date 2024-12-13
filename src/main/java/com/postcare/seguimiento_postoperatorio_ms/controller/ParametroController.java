package com.postcare.seguimiento_postoperatorio_ms.controller;

import com.postcare.seguimiento_postoperatorio_ms.model.Parametro;
import com.postcare.seguimiento_postoperatorio_ms.service.ParametroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@Tag(name = "Registro de Parametros", description = "API para gestionar los registros de parámetros de seguimiento postoperatorio")
public class ParametroController {
    @Autowired
    private ParametroService parametroService;

    @GetMapping("/parametros")
    @Operation(
            summary = "Obtener todos los parametros registrados",
            description = "Este endpoint devuelve una lista de todos los parametros registrados en el sistema."
    )
    public ResponseEntity<Iterable<Parametro>> getAllParametros() {
        Iterable<Parametro> parametros = parametroService.getAllParametros();
        return new ResponseEntity<>(parametros, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Parametro> createParametro(@Valid @RequestBody Parametro parametro) {
        Parametro savedParametro = parametroService.createParametro(parametro);
        return new ResponseEntity<>(savedParametro, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParametro(@PathVariable String id) {
        parametroService.deleteParametro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
