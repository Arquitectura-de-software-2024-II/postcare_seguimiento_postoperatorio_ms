package com.postcare.seguimiento_postoperatorio_ms.service;

import com.postcare.seguimiento_postoperatorio_ms.model.Cirugia;
import com.postcare.seguimiento_postoperatorio_ms.repository.CirugiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CirugiaService {

    @Autowired
    private CirugiaRepository cirugiaRepository;

    // Obtener todas las cirugías
    public List<Cirugia> obtenerTodasLasCirugias() {
        return cirugiaRepository.findAll();
    }

    // Obtener una cirugía por ID
    public Optional<Cirugia> obtenerCirugiaPorId(String id) {
        return cirugiaRepository.findById(id);
    }

    // Crear una nueva cirugía
    public Cirugia crearCirugia(Cirugia cirugia) {
        if (cirugia.getId() == null || cirugia.getId().isEmpty()) {
            cirugia.setId(UUID.randomUUID().toString());
        }
        return cirugiaRepository.save(cirugia);
    }

    // Actualizar una cirugía existente
    public Cirugia actualizarCirugia(String id, Cirugia cirugiaActualizada) {
        Optional<Cirugia> cirugiaOpt = cirugiaRepository.findById(id);
        if (cirugiaOpt.isPresent()) {
            Cirugia cirugia = cirugiaOpt.get();
            cirugia.setNombre(cirugiaActualizada.getNombre());
            cirugia.setTipo(cirugiaActualizada.getTipo());
            return cirugiaRepository.save(cirugia);
        }
        return null;
    }

    // Eliminar una cirugía por ID
    public boolean eliminarCirugia(String id) {
        Optional<Cirugia> cirugiaOpt = cirugiaRepository.findById(id);
        if (cirugiaOpt.isPresent()) {
            cirugiaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
