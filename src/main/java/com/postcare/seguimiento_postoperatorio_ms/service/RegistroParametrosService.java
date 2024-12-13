package com.postcare.seguimiento_postoperatorio_ms.service;

import com.postcare.seguimiento_postoperatorio_ms.model.RegistroParametros;
import com.postcare.seguimiento_postoperatorio_ms.model.RegistroDePaciente;
import com.postcare.seguimiento_postoperatorio_ms.repository.RegistroDePacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistroParametrosService {

    @Autowired
    private RegistroDePacienteRepository pacienteRepository;

    // Crear un nuevo registro de parámetros (signos vitales y síntomas) para un paciente
    public RegistroParametros crearRegistroParametros(String idPaciente, RegistroParametros registro) {
        // Obtener el paciente
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if (paciente.getRegistros() == null) {
            paciente.setRegistros(new ArrayList<>());
        }
        if (registro.getId() == null || registro.getId().isEmpty()) {
            registro.setId(UUID.randomUUID().toString());
        }
        // Agregar el nuevo registro de parámetros
        paciente.getRegistros().add(registro);

        // Guardar el paciente con el nuevo registro
        pacienteRepository.save(paciente);

        return registro;
    }

    // Obtener todos los registros de parámetros de un paciente
    public List<RegistroParametros> obtenerRegistrosParametros(String idPaciente) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        return paciente.getRegistros();
    }

    // Obtener un registro de parámetros específico de un paciente
    public RegistroParametros obtenerRegistroParametroPorId(String idPaciente, String idRegistro) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        return paciente.getRegistros().stream()
                .filter(registro -> registro.getFechaRegistro().toString().equals(idRegistro))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    }

    // Actualizar un registro de parámetros de un paciente
    public RegistroParametros actualizarRegistroParametros(String idPaciente, String idRegistro, RegistroParametros registroActualizado) {
        // Buscar el paciente por id
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        RegistroParametros registroExistente = paciente.getRegistros().stream()
                .filter(registro -> registro.getId().equals(idRegistro))  // Comparar por ID del registro
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        registroExistente.setParametrosControl(registroActualizado.getParametrosControl());

        pacienteRepository.save(paciente);

        return registroExistente;
    }


    // Eliminar un registro de parámetros de un paciente
    public void eliminarRegistroParametros(String idPaciente, String idRegistro) {
        RegistroDePaciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        RegistroParametros registroExistente = paciente.getRegistros().stream()
                .filter(registro -> registro.getId().equals(idRegistro))  // Comparar por ID del registro
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        paciente.getRegistros().remove(registroExistente);

        pacienteRepository.save(paciente);
    }

}
