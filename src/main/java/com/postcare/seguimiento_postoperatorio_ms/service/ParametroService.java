package com.postcare.seguimiento_postoperatorio_ms.service;

import com.postcare.seguimiento_postoperatorio_ms.model.Parametro;
import com.postcare.seguimiento_postoperatorio_ms.repository.ParametroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParametroService {
    @Autowired
    private ParametroRepository parametroRepository;

    public Parametro createParametro(Parametro parametro) {
        return parametroRepository.save(parametro);
    }

    public Optional<Parametro> getParametroById(String id) {
        return parametroRepository.findById(id);
    }

    public List<Parametro> getAllParametros() {
        return parametroRepository.findAll();
    }

    public Optional<Parametro> updateParametro(Parametro parametro) {
        if (parametroRepository.existsById(parametro.getId())) {
            return Optional.of(parametroRepository.save(parametro));
        } else {
            return Optional.empty();
        }
    }

    public void deleteParametro(String id) {
        parametroRepository.deleteById(id);
    }
}
