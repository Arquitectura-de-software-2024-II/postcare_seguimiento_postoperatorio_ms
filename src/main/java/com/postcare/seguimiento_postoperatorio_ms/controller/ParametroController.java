package com.postcare.seguimiento_postoperatorio_ms.controller;

import com.postcare.seguimiento_postoperatorio_ms.model.Parametro;
import com.postcare.seguimiento_postoperatorio_ms.model.SignoVital;
import com.postcare.seguimiento_postoperatorio_ms.model.Sintoma;
import com.postcare.seguimiento_postoperatorio_ms.service.ParametroService;
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
@RequestMapping("/api/parametros")
public class ParametroController {

    @Autowired
    private ParametroService parametroService;

    // Obtener todas las versiones de los parámetros
    @Operation(summary = "Obtener todas las versiones de los parámetros",
            description = "Recupera todas las versiones almacenadas de los parámetros.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Versiones de parámetros encontradas"),
            @ApiResponse(responseCode = "404", description = "No se encontraron versiones de parámetros"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/versiones")
    public ResponseEntity<List<Parametro>> obtenerTodasLasVersiones() {
        List<Parametro> parametros = parametroService.obtenerTodasLasVersiones();
        return parametros.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(parametros, HttpStatus.OK);
    }

    // Crear una nueva versión de parámetros
    @Operation(summary = "Crear un nuevo conjunto de parámetros (signos vitales y síntomas)",
            description = "Permite crear una nueva versión de los parámetros de signos vitales y síntomas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Parámetro creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping
    public ResponseEntity<Parametro> crearParametro(@RequestBody Parametro parametro) {
        Parametro nuevoParametro = parametroService.crearParametro(parametro);
        return new ResponseEntity<>(nuevoParametro, HttpStatus.CREATED);
    }

    // Obtener un parámetro por ID
    @Operation(summary = "Obtener un conjunto de parámetros por su ID",
            description = "Permite obtener una versión específica de los parámetros a partir de su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parámetro encontrado"),
            @ApiResponse(responseCode = "404", description = "Parámetro no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Parametro> obtenerParametro(@PathVariable String id) {
        Optional<Parametro> parametroOpt = parametroService.obtenerParametroPorId(id);
        return parametroOpt.map(parametro -> new ResponseEntity<>(parametro, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Agregar un nuevo signo vital a un parámetro existente
    @Operation(summary = "Agregar un signo vital a un conjunto de parámetros",
            description = "Permite agregar un nuevo signo vital a una versión existente de los parámetros.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Signo vital agregado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Parámetro no encontrado")
    })
    @PostMapping("/{idParametro}/signos-vitales")
    public ResponseEntity<Parametro> agregarSignoVital(@PathVariable String idParametro, @RequestBody SignoVital signoVital) {
        Parametro parametroActualizado = parametroService.agregarSignoVital(idParametro, signoVital);
        return parametroActualizado != null ? new ResponseEntity<>(parametroActualizado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Agregar un nuevo síntoma a un parámetro existente
    @Operation(summary = "Agregar un síntoma a un conjunto de parámetros",
            description = "Permite agregar un nuevo síntoma a una versión existente de los parámetros.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Síntoma agregado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Parámetro no encontrado")
    })
    @PostMapping("/{idParametro}/sintomas")
    public ResponseEntity<Parametro> agregarSintoma(@PathVariable String idParametro, @RequestBody Sintoma sintoma) {
        Parametro parametroActualizado = parametroService.agregarSintoma(idParametro, sintoma);
        return parametroActualizado != null ? new ResponseEntity<>(parametroActualizado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Actualizar un signo vital en un parámetro existente
    @Operation(summary = "Actualizar un signo vital de un conjunto de parámetros",
            description = "Permite actualizar un signo vital en una versión existente de los parámetros.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Signo vital actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Parámetro o signo vital no encontrado")
    })
    @PutMapping("/{idParametro}/signos-vitales/{idSignoVital}")
    public ResponseEntity<Parametro> actualizarSignoVital(@PathVariable String idParametro, @PathVariable String idSignoVital, @RequestBody SignoVital signoVitalActualizado) {
        Parametro parametroActualizado = parametroService.actualizarSignoVital(idParametro, idSignoVital, signoVitalActualizado);
        return parametroActualizado != null ? new ResponseEntity<>(parametroActualizado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Actualizar un síntoma en un parámetro existente
    @Operation(summary = "Actualizar un síntoma de un conjunto de parámetros",
            description = "Permite actualizar un síntoma en una versión existente de los parámetros.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Síntoma actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Parámetro o síntoma no encontrado")
    })
    @PutMapping("/{idParametro}/sintomas/{idSintoma}")
    public ResponseEntity<Parametro> actualizarSintoma(@PathVariable String idParametro, @PathVariable String idSintoma, @RequestBody Sintoma sintomaActualizado) {
        Parametro parametroActualizado = parametroService.actualizarSintoma(idParametro, idSintoma, sintomaActualizado);
        return parametroActualizado != null ? new ResponseEntity<>(parametroActualizado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Eliminar un signo vital de un parámetro
    @Operation(summary = "Eliminar un signo vital de un conjunto de parámetros",
            description = "Permite eliminar un signo vital de un conjunto de parámetros existentes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Signo vital eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Parámetro o signo vital no encontrado")
    })
    @DeleteMapping("/{idParametro}/signos-vitales/{idSignoVital}")
    public ResponseEntity<Parametro> eliminarSignoVital(@PathVariable String idParametro, @PathVariable String idSignoVital) {
        Parametro parametroActualizado = parametroService.eliminarSignoVital(idParametro, idSignoVital);
        return parametroActualizado != null ? new ResponseEntity<>(parametroActualizado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Eliminar un síntoma de un parámetro
    @Operation(summary = "Eliminar un síntoma de un conjunto de parámetros",
            description = "Permite eliminar un síntoma de un conjunto de parámetros existentes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Síntoma eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Parámetro o síntoma no encontrado")
    })
    @DeleteMapping("/{idParametro}/sintomas/{idSintoma}")
    public ResponseEntity<Parametro> eliminarSintoma(@PathVariable String idParametro, @PathVariable String idSintoma) {
        Parametro parametroActualizado = parametroService.eliminarSintoma(idParametro, idSintoma);
        return parametroActualizado != null ? new ResponseEntity<>(parametroActualizado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
