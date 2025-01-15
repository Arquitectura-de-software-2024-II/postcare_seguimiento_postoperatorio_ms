package com.postcare.seguimiento_postoperatorio_ms.service;

import com.postcare.seguimiento_postoperatorio_ms.model.RegistroParametros;
import com.postcare.seguimiento_postoperatorio_ms.model.RegistroDePaciente;
import com.postcare.seguimiento_postoperatorio_ms.repository.RegistroDePacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistroParametrosService {

    @Autowired
    private RegistroDePacienteRepository pacienteRepository;


    // Crear un nuevo registro de parámetros (signos vitales y síntomas) para un paciente
    public Optional<RegistroParametros> crearRegistroParametros(String idPaciente, RegistroParametros registro) {
        Optional<RegistroDePaciente> pacienteOpt = Optional.ofNullable(pacienteRepository.findById(idPaciente).orElse(null));

        if (pacienteOpt.isEmpty()) {
            return Optional.empty();  // El paciente no existe
        }

        RegistroDePaciente paciente = pacienteOpt.get();
        if (paciente.getRegistros() == null) {
            paciente.setRegistros(List.of());  // Inicializa la lista de registros si es nula
        }

        // Asignar un ID único al registro si no tiene uno
        if (registro.getId() == null || registro.getId().isEmpty()) {
            registro.setId(UUID.randomUUID().toString());
        }

        paciente.getRegistros().add(registro);
        pacienteRepository.save(paciente);

        return Optional.of(registro);
    }

    // Obtener todos los registros de parámetros de un paciente
    public List<RegistroParametros> obtenerRegistrosParametros(String idPaciente) {
        Optional<RegistroDePaciente> pacienteOpt = Optional.ofNullable(pacienteRepository.findById(idPaciente).orElse(null));

        if (pacienteOpt.isEmpty() || pacienteOpt.get().getRegistros() == null) {
            return List.of();
        }

        return pacienteOpt.get().getRegistros();
    }

    // Obtener un registro de parámetros específico de un paciente
    public Optional<RegistroParametros> obtenerRegistroParametroPorId(String idPaciente, String idRegistro) {
        Optional<RegistroDePaciente> pacienteOpt = Optional.ofNullable(pacienteRepository.findById(idPaciente).orElse(null));

        if (pacienteOpt.isEmpty() || pacienteOpt.get().getRegistros() == null) {
            return Optional.empty();  // No se encuentra el paciente o no tiene registros
        }

        return pacienteOpt.get().getRegistros().stream()
                .filter(registro -> registro.getId().equals(idRegistro))
                .findFirst();
    }

    // Actualizar un registro de parámetros de un paciente
    public Optional<RegistroParametros> actualizarRegistroParametros(String idPaciente, String idRegistro, RegistroParametros registroActualizado) {
        Optional<RegistroDePaciente> pacienteOpt = Optional.ofNullable(pacienteRepository.findById(idPaciente).orElse(null));

        if (pacienteOpt.isEmpty() || pacienteOpt.get().getRegistros() == null) {
            return Optional.empty();  // No se encuentra el paciente o no tiene registros
        }

        RegistroDePaciente paciente = pacienteOpt.get();
        Optional<RegistroParametros> registroExistenteOpt = paciente.getRegistros().stream()
                .filter(registro -> registro.getId().equals(idRegistro))  // Comparar por ID del registro
                .findFirst();

        if (registroExistenteOpt.isEmpty()) {
            return Optional.empty();  // No se encuentra el registro
        }

        RegistroParametros registroExistente = registroExistenteOpt.get();
        registroExistente.setParametrosControl(registroActualizado.getParametrosControl());

        pacienteRepository.save(paciente);
        return Optional.of(registroExistente);  // Retornar el registro actualizado
    }

    // Eliminar un registro de parámetros de un paciente
    public boolean eliminarRegistroParametros(String idPaciente, String idRegistro) {
        Optional<RegistroDePaciente> pacienteOpt = Optional.ofNullable(pacienteRepository.findById(idPaciente).orElse(null));

        if (pacienteOpt.isEmpty() || pacienteOpt.get().getRegistros() == null) {
            return false;  // No se encuentra el paciente o no tiene registros
        }

        RegistroDePaciente paciente = pacienteOpt.get();
        List<RegistroParametros> registros = paciente.getRegistros();
        Optional<RegistroParametros> registroExistenteOpt = registros.stream()
                .filter(registro -> registro.getId().equals(idRegistro))
                .findFirst();

        if (registroExistenteOpt.isEmpty()) {
            return false;  // No se encuentra el registro
        }

        registros.remove(registroExistenteOpt.get());
        pacienteRepository.save(paciente);
        return true;  // Registro eliminado con éxito
    }
}
