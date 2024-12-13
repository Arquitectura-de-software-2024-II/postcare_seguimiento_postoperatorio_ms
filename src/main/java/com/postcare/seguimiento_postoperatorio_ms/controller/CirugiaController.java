package com.postcare.seguimiento_postoperatorio_ms.controller;

import com.postcare.seguimiento_postoperatorio_ms.model.Cirugia;
import com.postcare.seguimiento_postoperatorio_ms.service.CirugiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cirugias")
public class CirugiaController {

    @Autowired
    private CirugiaService cirugiaService;

    // Consultar todas las cirugías
    @Operation(summary = "Obtener todas las cirugías registradas en el sistema",
            description = "Devuelve una lista de todas las cirugías registradas en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cirugías encontrada"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    @GetMapping
    public ResponseEntity<List<Cirugia>> obtenerTodasLasCirugias() {
        List<Cirugia> cirugias = cirugiaService.obtenerTodasLasCirugias();
        return new ResponseEntity<>(cirugias, HttpStatus.OK);
    }

    // Consultar una cirugía específica
    @Operation(summary = "Obtener una cirugía específica por su ID",
            description = "Devuelve los detalles de una cirugía específica utilizando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cirugía encontrada"),
            @ApiResponse(responseCode = "404", description = "Cirugía no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cirugia> obtenerCirugia(@PathVariable String id) {
        Optional<Cirugia> cirugiaOpt = cirugiaService.obtenerCirugiaPorId(id);
        return cirugiaOpt.map(cirugia -> new ResponseEntity<>(cirugia, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear una nueva cirugía
    @Operation(summary = "Crear una nueva cirugía",
            description = "Permite crear una nueva cirugía en el sistema (incluyendo los tipos de cirugía asociados).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cirugía creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping
    public ResponseEntity<Cirugia> crearCirugia(@RequestBody Cirugia cirugia) {
        Cirugia nuevaCirugia = cirugiaService.crearCirugia(cirugia);
        return new ResponseEntity<>(nuevaCirugia, HttpStatus.CREATED);
    }

    // Actualizar una cirugía
    @Operation(summary = "Actualizar una cirugía existente",
            description = "Permite actualizar los detalles de una cirugía existente, incluyendo los tipos de cirugía.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cirugía actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cirugía no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Cirugia> actualizarCirugia(@PathVariable String id, @RequestBody Cirugia cirugiaActualizada) {
        Cirugia cirugia = cirugiaService.actualizarCirugia(id, cirugiaActualizada);
        return cirugia != null ? new ResponseEntity<>(cirugia, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Eliminar una cirugía
    @Operation(summary = "Eliminar una cirugía",
            description = "Permite eliminar una cirugía del sistema utilizando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cirugía eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cirugía no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCirugia(@PathVariable String id) {
        boolean eliminado = cirugiaService.eliminarCirugia(id);
        return eliminado ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
