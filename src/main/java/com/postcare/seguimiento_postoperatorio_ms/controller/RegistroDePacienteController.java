package com.postcare.seguimiento_postoperatorio_ms.controller;

import com.postcare.seguimiento_postoperatorio_ms.model.*;
import com.postcare.seguimiento_postoperatorio_ms.service.RegistroCirugiaService;
import com.postcare.seguimiento_postoperatorio_ms.service.RegistroDePacienteService;
import com.postcare.seguimiento_postoperatorio_ms.service.RegistroParametrosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
public class RegistroDePacienteController {
    @Autowired
    private RegistroCirugiaService registroCirugiaService;

    @Autowired
    private RegistroParametrosService registroParametrosService;

    @Autowired
    private RegistroDePacienteService registroDePacienteService;

    @Operation(summary = "Crear un nuevo paciente (usuario)", description = "Este endpoint crea un nuevo paciente, que será tratado como un usuario con un ID único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping("/{idPaciente}")
    public ResponseEntity<RegistroDePaciente> crearPaciente(@PathVariable String idPaciente) {
        // Crear un nuevo paciente con el ID proporcionado o generado automáticamente
        RegistroDePaciente nuevoPaciente = registroDePacienteService.crearPaciente(idPaciente);
        return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener un paciente por ID", description = "Este endpoint permite obtener un paciente existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
    })
    @GetMapping("/{idPaciente}")
    public ResponseEntity<RegistroDePaciente> obtenerPacientePorId(@PathVariable String idPaciente) {
        return registroDePacienteService.obtenerPacientePorId(idPaciente)
                .map(paciente -> new ResponseEntity<>(paciente, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{idPaciente}/estado-recuperacion")
    @Operation(summary = "Actualizar el estado de recuperación de un paciente", description = "Este endpoint permite actualizar el estado de recuperación de un paciente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de recuperación actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
    })
    public ResponseEntity<RegistroDePaciente> actualizarEstadoRecuperacion(
            @PathVariable String idPaciente,
            @RequestBody boolean enRecuperacion) {

        try {
            RegistroDePaciente pacienteActualizado = registroDePacienteService.actualizarEstadoRecuperacion(idPaciente, enRecuperacion);
            return new ResponseEntity<>(pacienteActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
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
    @PostMapping("/{idPaciente}/cirugias")
    public ResponseEntity<RegistroDePaciente> crearCirugia(@PathVariable String idPaciente, @RequestBody RegistroCirugia registroCirugia) {
        RegistroDePaciente paciente = registroCirugiaService.crearCirugia(idPaciente, registroCirugia);
        return new ResponseEntity<>(paciente, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todas las cirugías de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cirugías obtenida correctamente")
    })
    @GetMapping("/{idPaciente}/cirugias")
    public ResponseEntity<List<RegistroCirugia>> obtenerCirugias(@PathVariable String idPaciente) {
        List<RegistroCirugia> cirugias = registroCirugiaService.obtenerCirugias(idPaciente);
        return new ResponseEntity<>(cirugias, HttpStatus.OK);
    }

    @Operation(summary = "Obtener una cirugía específica de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cirugía obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "Cirugía no encontrada")
    })
    @GetMapping("/{idPaciente}/cirugias/{idCirugia}")
    public ResponseEntity<RegistroCirugia> obtenerCirugiaPorId(@PathVariable String idPaciente, @PathVariable String idCirugia) {
        Optional<RegistroCirugia> cirugiaOpt = registroCirugiaService.obtenerCirugiaPorId(idPaciente, idCirugia);
        return cirugiaOpt.map(cirugia -> new ResponseEntity<>(cirugia, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Actualizar una cirugía de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cirugía actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Cirugía no encontrada")
    })
    @PutMapping("/{idPaciente}/cirugias/{idCirugia}")
    public ResponseEntity<RegistroCirugia> actualizarCirugia(@PathVariable String idPaciente, @PathVariable String idCirugia, @RequestBody RegistroCirugia registroCirugia) {
        Optional<RegistroCirugia> cirugiaOpt = registroCirugiaService.actualizarCirugia(idPaciente, idCirugia, registroCirugia);
        return cirugiaOpt.map(cirugia -> new ResponseEntity<>(cirugia, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Eliminar una cirugía de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cirugía eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Cirugía no encontrada")
    })
    @DeleteMapping("/{idPaciente}/cirugias/{idCirugia}")
    public ResponseEntity<Void> eliminarCirugia(@PathVariable String idPaciente, @PathVariable String idCirugia) {
        boolean eliminado = registroCirugiaService.eliminarCirugia(idPaciente, idCirugia);
        return eliminado ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // CRUD de Registros de Parámetros (signos vitales y síntomas)

    @Operation(summary = "Crear un nuevo registro de parámetros para un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping("/{idPaciente}/registros")
    public ResponseEntity<RegistroParametros> crearRegistroParametros(
            @PathVariable String idPaciente,
            @RequestBody RegistroParametros registro,
            HttpServletRequest request) {

        // Obtener todas las cookies
        String cookies = request.getHeader("Cookie");
        Optional<RegistroParametros> nuevoRegistroOpt = registroParametrosService.crearRegistroParametros(idPaciente, registro, cookies);
        return nuevoRegistroOpt.map(nuevoRegistro -> new ResponseEntity<>(nuevoRegistro, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Operation(summary = "Obtener todos los registros de parámetros de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de registros obtenida correctamente")
    })
    @GetMapping("/{idPaciente}/registros")
    public ResponseEntity<List<RegistroParametros>> obtenerRegistrosParametros(@PathVariable String idPaciente) {
        List<RegistroParametros> registros = registroParametrosService.obtenerRegistrosParametros(idPaciente);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un registro específico de parámetros de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro obtenido correctamente"),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    @GetMapping("/{idPaciente}/registros/{idRegistro}")
    public ResponseEntity<RegistroParametros> obtenerRegistroParametro(@PathVariable String idPaciente, @PathVariable String idRegistro) {
        Optional<RegistroParametros> registroOpt = registroParametrosService.obtenerRegistroParametroPorId(idPaciente, idRegistro);
        return registroOpt.map(registro -> new ResponseEntity<>(registro, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Actualizar un registro de parámetros de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    @PutMapping("/{idPaciente}/registros/{idRegistro}")
    public ResponseEntity<RegistroParametros> actualizarRegistroParametros(@PathVariable String idPaciente, @PathVariable String idRegistro, @RequestBody RegistroParametros registro) {
        Optional<RegistroParametros> registroOpt = registroParametrosService.actualizarRegistroParametros(idPaciente, idRegistro, registro);
        return registroOpt.map(registroActualizado -> new ResponseEntity<>(registroActualizado, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Eliminar un registro de parámetros de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    @DeleteMapping("/{idPaciente}/registros/{idRegistro}")
    public ResponseEntity<Void> eliminarRegistroParametros(@PathVariable String idPaciente, @PathVariable String idRegistro) {
        boolean eliminado = registroParametrosService.eliminarRegistroParametros(idPaciente, idRegistro);
        return eliminado ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
