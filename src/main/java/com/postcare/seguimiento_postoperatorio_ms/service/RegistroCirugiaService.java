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
public class RegistroCirugiaService {

    @Autowired
    private RegistroDePacienteRepository pacienteRepository;

    // Crear un nuevo registro de cirugía para un paciente
    public RegistroDePaciente crearCirugia(String idPaciente, RegistroCirugia registroCirugia) {
        // Buscar o crear el paciente
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente)
                .orElseGet(() -> {
                    RegistroDePaciente nuevoPaciente = new RegistroDePaciente();
                    nuevoPaciente.setId(idPaciente);
                    return nuevoPaciente;
                });

        if (paciente.getCirugias() == null) {
            paciente.setCirugias(new ArrayList<>());
        }

        if (registroCirugia.getId() == null || registroCirugia.getId().isEmpty()) {
            registroCirugia.setId(UUID.randomUUID().toString());
        }
        paciente.getCirugias().add(registroCirugia);
        return pacienteRepository.save(paciente);
    }

    // Obtener todas las cirugías de un paciente
    public List<RegistroCirugia> obtenerCirugias(String idPaciente) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente).orElse(null);

        // Retornar lista vacía si no se encuentra el paciente
        return paciente != null ? paciente.getCirugias() : new ArrayList<>();
    }

    // Obtener una cirugía específica de un paciente
    public Optional<RegistroCirugia> obtenerCirugiaPorId(String idPaciente, String idCirugia) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente).orElse(null);

        // Si no se encuentra el paciente, retornamos Optional vacío
        if (paciente != null) {
            return paciente.getCirugias().stream()
                    .filter(cirugia -> cirugia.getId().equals(idCirugia))
                    .findFirst();  // Optional.empty() si no se encuentra la cirugía
        }
        return Optional.empty();  // Retornamos Optional vacío si no se encuentra el paciente
    }

    // Actualizar una cirugía de un paciente
    public Optional<RegistroCirugia> actualizarCirugia(String idPaciente, String idCirugia, RegistroCirugia cirugiaActualizada) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente).orElse(null);

        if (paciente != null) {
            // Buscar la cirugía existente
            RegistroCirugia cirugiaExistente = paciente.getCirugias().stream()
                    .filter(cirugia -> cirugia.getId().equals(idCirugia))
                    .findFirst()
                    .orElse(null);  // Si no se encuentra la cirugía, retorna null

            if (cirugiaExistente != null) {
                // Actualizar la cirugía
                cirugiaExistente.setNombreCirugia(cirugiaActualizada.getNombreCirugia());
                cirugiaExistente.setIdCirugia(cirugiaActualizada.getIdCirugia());
                cirugiaExistente.setNombreMedico(cirugiaActualizada.getNombreMedico());
                cirugiaExistente.setDescripcion(cirugiaActualizada.getDescripcion());
                cirugiaExistente.setFechaCirugia(cirugiaActualizada.getFechaCirugia());
                cirugiaExistente.setTipoCirugia(cirugiaActualizada.getTipoCirugia());

                pacienteRepository.save(paciente);
                return Optional.of(cirugiaExistente);  // Retornamos la cirugía actualizada
            }
        }

        return Optional.empty();  // Retornamos Optional vacío si no se encuentra el paciente o la cirugía
    }

    // Eliminar una cirugía de un paciente
    public boolean eliminarCirugia(String idPaciente, String idCirugia) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente).orElse(null);

        if (paciente != null) {
            // Buscar la cirugía en el listado del paciente
            RegistroCirugia cirugiaExistente = paciente.getCirugias().stream()
                    .filter(cirugia -> cirugia.getId().equals(idCirugia))
                    .findFirst()
                    .orElse(null);  // Si no se encuentra la cirugía, retorna null

            if (cirugiaExistente != null) {
                paciente.getCirugias().remove(cirugiaExistente);
                pacienteRepository.save(paciente);
                return true;  // Cirugía eliminada exitosamente
            }
        }

        return false;  // Retornamos false si no se encuentra la cirugía
    }
}
