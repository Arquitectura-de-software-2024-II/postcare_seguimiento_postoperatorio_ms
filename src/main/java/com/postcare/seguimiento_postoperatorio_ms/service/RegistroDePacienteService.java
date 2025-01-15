package com.postcare.seguimiento_postoperatorio_ms.service;

import com.postcare.seguimiento_postoperatorio_ms.model.*;
import com.postcare.seguimiento_postoperatorio_ms.repository.RegistroDePacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistroDePacienteService {

    @Autowired
    private RegistroDePacienteRepository pacienteRepository;

    public RegistroDePaciente crearPaciente(String idPaciente) {
        if (idPaciente == null || idPaciente.isEmpty()) {
            idPaciente = UUID.randomUUID().toString();
        }

        RegistroDePaciente paciente = new RegistroDePaciente();
        paciente.setId(idPaciente);
        paciente.setEnRecuperacion(false);

        paciente.setCirugias(new ArrayList<>());
        paciente.setRegistros(new ArrayList<>());

        return pacienteRepository.save(paciente);
    }

    public Optional<RegistroDePaciente> obtenerPacientePorId(String idPaciente) {
        return pacienteRepository.findById(idPaciente);
    }

    public RegistroDePaciente actualizarEstadoRecuperacion(String idPaciente, boolean enRecuperacion) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        paciente.setEnRecuperacion(enRecuperacion);
        return pacienteRepository.save(paciente);
    }

    // Crear un nuevo registro de cirugía para un paciente
    public RegistroDePaciente crearCirugia(String idPaciente, RegistroCirugia registroCirugia) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente)
                .orElseGet(() -> {
                    RegistroDePaciente nuevoPaciente = new RegistroDePaciente();
                    nuevoPaciente.setId(idPaciente);
                    return nuevoPaciente;
                });

        paciente.getCirugias().add(registroCirugia);
        return pacienteRepository.save(paciente);
    }

    // Obtener todas las cirugías de un paciente
    public List<RegistroCirugia> obtenerCirugias(String idPaciente) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente)
                .orElse(null);  // No lanzamos excepción si no encontramos el paciente

        return paciente != null ? paciente.getCirugias() : new java.util.ArrayList<>();  // Retornamos lista vacía si no existe el paciente
    }

    // Obtener una cirugía específica de un paciente
    public Optional<RegistroCirugia> obtenerCirugiaPorId(String idPaciente, String idCirugia) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente).orElse(null);  // No lanzamos excepción si no encontramos el paciente

        if (paciente != null) {
            // Retornamos un Optional con la cirugía si la encontramos, o un Optional vacío si no la encontramos
            return paciente.getCirugias().stream()
                    .filter(cirugia -> cirugia.getId().equals(idCirugia))
                    .findFirst();
        }

        return Optional.empty();  // Si no se encuentra el paciente, retornamos Optional vacío
    }

    // Actualizar una cirugía de un paciente
    public Optional<RegistroCirugia> actualizarCirugia(String idPaciente, String idCirugia, RegistroCirugia cirugiaActualizada) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente).orElse(null);  // No lanzamos excepción si no encontramos el paciente

        if (paciente != null) {
            RegistroCirugia cirugiaExistente = paciente.getCirugias().stream()
                    .filter(cirugia -> cirugia.getId().equals(idCirugia))
                    .findFirst()
                    .orElse(null);

            if (cirugiaExistente != null) {
                // Actualizamos la cirugía
                cirugiaExistente.setNombreCirugia(cirugiaActualizada.getNombreCirugia());
                cirugiaExistente.setIdCirugia(cirugiaActualizada.getIdCirugia());
                cirugiaExistente.setNombreMedico(cirugiaActualizada.getNombreMedico());
                cirugiaExistente.setDescripcion(cirugiaActualizada.getDescripcion());
                cirugiaExistente.setFechaCirugia(cirugiaActualizada.getFechaCirugia());
                cirugiaExistente.setTipoCirugia(cirugiaActualizada.getTipoCirugia());

                pacienteRepository.save(paciente);
                return Optional.of(cirugiaExistente);
            }
        }

        return Optional.empty();
    }

    // Eliminar una cirugía de un paciente
    public boolean eliminarCirugia(String idPaciente, String idCirugia) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente).orElse(null);  // No lanzamos excepción si no encontramos el paciente

        if (paciente != null) {
            RegistroCirugia cirugiaExistente = paciente.getCirugias().stream()
                    .filter(cirugia -> cirugia.getId().equals(idCirugia))
                    .findFirst()
                    .orElse(null);

            if (cirugiaExistente != null) {
                paciente.getCirugias().remove(cirugiaExistente);
                pacienteRepository.save(paciente);
                return true;
            }
        }

        return false;
    }
}
