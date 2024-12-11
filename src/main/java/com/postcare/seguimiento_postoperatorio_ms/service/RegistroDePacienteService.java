package com.postcare.seguimiento_postoperatorio_ms.service;

import com.postcare.seguimiento_postoperatorio_ms.model.RegistroDePaciente;
import com.postcare.seguimiento_postoperatorio_ms.repository.RegistroDePacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroDePacienteService {

    @Autowired
    private RegistroDePacienteRepository registroDePacienteRepository;

    // Método para registrar un paciente
    public RegistroDePaciente registrarPaciente(RegistroDePaciente registroDePaciente) {
        return registroDePacienteRepository.save(registroDePaciente);
    }

    // Método para obtener un registro de paciente por ID
    /*public RegistroDePaciente obtenerRegistroPorId(int idPaciente) {
        return registroDePacienteRepository.findByIdPaciente(idPaciente);
    }*/

    // Método para obtener todos los registros
    public List<RegistroDePaciente> obtenerTodosLosRegistros() {
        return registroDePacienteRepository.findAll();
    }
}
