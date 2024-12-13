package com.postcare.seguimiento_postoperatorio_ms.controller;

import com.postcare.seguimiento_postoperatorio_ms.model.*;
import com.postcare.seguimiento_postoperatorio_ms.service.RegistroCirugiaService;
import com.postcare.seguimiento_postoperatorio_ms.service.RegistroParametrosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes/{idPaciente}")
public class RegistroDePacienteController {
    @Autowired
    private RegistroCirugiaService registroCirugiaService;

    @Autowired
    private RegistroParametrosService registroParametrosService;

    // CRUD de Cirugías

    @GetMapping("/test")
    @Operation(summary = "Verificar que el servicio está en funcionamiento", description = "Este endpoint permite comprobar que el servicio está operativo.")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("El endpoint está funcionando correctamente");
    }

    @Operation(summary = "Crear una nueva cirugía para un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cirugía creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping("/cirugias")
    public ResponseEntity<RegistroDePaciente> crearCirugia(@PathVariable String idPaciente, @RequestBody RegistroCirugia registroCirugia) {
        RegistroDePaciente paciente = registroCirugiaService.crearCirugia(idPaciente, registroCirugia);
        return new ResponseEntity<>(paciente, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todas las cirugías de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cirugías obtenida correctamente")
    })
    @GetMapping("/cirugias")
    public ResponseEntity<List<RegistroCirugia>> obtenerCirugias(@PathVariable String idPaciente) {
        List<RegistroCirugia> cirugias = registroCirugiaService.obtenerCirugias(idPaciente);
        return new ResponseEntity<>(cirugias, HttpStatus.OK);
    }

    @Operation(summary = "Obtener una cirugía específica de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cirugía obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "Cirugía no encontrada")
    })
    @GetMapping("/cirugias/{idCirugia}")
    public ResponseEntity<RegistroCirugia> obtenerCirugiaPorId(@PathVariable String idPaciente, @PathVariable String idCirugia) {
        RegistroCirugia cirugia = registroCirugiaService.obtenerCirugiaPorId(idPaciente, idCirugia);
        return new ResponseEntity<>(cirugia, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar una cirugía de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cirugía actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Cirugía no encontrada")
    })
    @PutMapping("/cirugias/{idCirugia}")
    public ResponseEntity<RegistroCirugia> actualizarCirugia(@PathVariable String idPaciente, @PathVariable String idCirugia, @RequestBody RegistroCirugia registroCirugia) {
        RegistroCirugia cirugiaActualizada = registroCirugiaService.actualizarCirugia(idPaciente, idCirugia, registroCirugia);
        return new ResponseEntity<>(cirugiaActualizada, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar una cirugía de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cirugía eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Cirugía no encontrada")
    })
    @DeleteMapping("/cirugias/{idCirugia}")
    public ResponseEntity<Void> eliminarCirugia(@PathVariable String idPaciente, @PathVariable String idCirugia) {
        registroCirugiaService.eliminarCirugia(idPaciente, idCirugia);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // CRUD de Registros de Parámetros (signos vitales y síntomas)

    @Operation(summary = "Crear un nuevo registro de parámetros para un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping("/registros")
    public ResponseEntity<RegistroParametros> crearRegistroParametros(@PathVariable String idPaciente, @RequestBody RegistroParametros registro) {
        RegistroParametros nuevoRegistro = registroParametrosService.crearRegistroParametros(idPaciente, registro);
        return new ResponseEntity<>(nuevoRegistro, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todos los registros de parámetros de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de registros obtenida correctamente")
    })
    @GetMapping("/registros")
    public ResponseEntity<List<RegistroParametros>> obtenerRegistrosParametros(@PathVariable String idPaciente) {
        List<RegistroParametros> registros = registroParametrosService.obtenerRegistrosParametros(idPaciente);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un registro específico de parámetros de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro obtenido correctamente"),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    @GetMapping("/registros/{idRegistro}")
    public ResponseEntity<RegistroParametros> obtenerRegistroParametro(@PathVariable String idPaciente, @PathVariable String idRegistro) {
        RegistroParametros registro = registroParametrosService.obtenerRegistroParametroPorId(idPaciente, idRegistro);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar un registro de parámetros de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    @PutMapping("/registros/{idRegistro}")
    public ResponseEntity<RegistroParametros> actualizarRegistroParametros(@PathVariable String idPaciente, @PathVariable String idRegistro, @RequestBody RegistroParametros registro) {
        RegistroParametros registroActualizado = registroParametrosService.actualizarRegistroParametros(idPaciente, idRegistro, registro);
        return new ResponseEntity<>(registroActualizado, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un registro de parámetros de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    @DeleteMapping("/registros/{idRegistro}")
    public ResponseEntity<Void> eliminarRegistroParametros(@PathVariable String idPaciente, @PathVariable String idRegistro) {
        registroParametrosService.eliminarRegistroParametros(idPaciente, idRegistro);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
