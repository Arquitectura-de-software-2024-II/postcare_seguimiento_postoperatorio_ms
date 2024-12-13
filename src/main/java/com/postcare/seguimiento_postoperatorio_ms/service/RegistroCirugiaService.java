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
        // Verificar si existe el arreglo de cirugias
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
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        return paciente.getCirugias();
    }

    // Obtener una cirugía específica de un paciente
    public RegistroCirugia obtenerCirugiaPorId(String idPaciente, String idCirugia) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        return paciente.getCirugias().stream()
                .filter(cirugia -> cirugia.getId().equals(idCirugia))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cirugía no encontrada"));
    }

    // Actualizar una cirugía de un paciente
    public RegistroCirugia actualizarCirugia(String idPaciente, String idCirugia, RegistroCirugia cirugiaActualizada) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        RegistroCirugia cirugiaExistente = paciente.getCirugias().stream()
                .filter(cirugia -> cirugia.getId().equals(idCirugia))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cirugía no encontrada"));

        // Actualizar la cirugía
        cirugiaExistente.setNombreCirugia(cirugiaActualizada.getNombreCirugia());
        cirugiaExistente.setIdCirugia(cirugiaActualizada.getIdCirugia());
        cirugiaExistente.setNombreMedico(cirugiaActualizada.getNombreMedico());
        cirugiaExistente.setDescripcion(cirugiaActualizada.getDescripcion());
        cirugiaExistente.setFechaCirugia(cirugiaActualizada.getFechaCirugia());
        cirugiaExistente.setTipoCirugia(cirugiaActualizada.getTipoCirugia());

        pacienteRepository.save(paciente);
        return cirugiaExistente;
    }

    // Eliminar una cirugía de un paciente
    public void eliminarCirugia(String idPaciente, String idCirugia) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        RegistroCirugia cirugiaExistente = paciente.getCirugias().stream()
                .filter(cirugia -> cirugia.getId().equals(idCirugia))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cirugía no encontrada"));

        paciente.getCirugias().remove(cirugiaExistente);
        pacienteRepository.save(paciente);
    }
}
