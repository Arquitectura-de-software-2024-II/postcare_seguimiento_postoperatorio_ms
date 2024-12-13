package com.postcare.seguimiento_postoperatorio_ms.service;

import com.postcare.seguimiento_postoperatorio_ms.model.Parametro;
import com.postcare.seguimiento_postoperatorio_ms.model.SignoVital;
import com.postcare.seguimiento_postoperatorio_ms.model.Sintoma;
import com.postcare.seguimiento_postoperatorio_ms.repository.ParametroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParametroService {

    @Autowired
    private ParametroRepository parametroRepository;

    // Obtener todas las versiones de los parámetros
    public List<Parametro> obtenerTodasLasVersiones() {
        return parametroRepository.findAll();
    }

    // Crear una nueva versión de parámetros
    public Parametro crearParametro(Parametro parametro) {
        return parametroRepository.save(parametro);
    }

    // Obtener un parámetro por su ID
    public Optional<Parametro> obtenerParametroPorId(String id) {
        return parametroRepository.findById(id);
    }

    // Agregar un nuevo signo vital a una versión de parámetros existente
    public Parametro agregarSignoVital(String idParametro, SignoVital signoVital) {
        Optional<Parametro> parametroOpt = parametroRepository.findById(idParametro);
        if (parametroOpt.isPresent()) {
            Parametro parametro = parametroOpt.get();
            parametro.getSignosVitales().add(signoVital);
            return parametroRepository.save(parametro);
        }
        return null; // Retornar null si no se encuentra el parámetro
    }

    // Agregar un nuevo síntoma a una versión de parámetros existente
    public Parametro agregarSintoma(String idParametro, Sintoma sintoma) {
        Optional<Parametro> parametroOpt = parametroRepository.findById(idParametro);
        if (parametroOpt.isPresent()) {
            Parametro parametro = parametroOpt.get();
            parametro.getSintomas().add(sintoma);
            return parametroRepository.save(parametro);
        }
        return null;
    }

    // Actualizar un signo vital existente en una versión de parámetros
    public Parametro actualizarSignoVital(String idParametro, String idSignoVital, SignoVital signoVitalActualizado) {
        Optional<Parametro> parametroOpt = parametroRepository.findById(idParametro);
        if (parametroOpt.isPresent()) {
            Parametro parametro = parametroOpt.get();
            parametro.getSignosVitales().forEach(signoVital -> {
                if (signoVital.getId().equals(idSignoVital)) {
                    signoVital.setNombre(signoVitalActualizado.getNombre());
                    signoVital.setUnidad(signoVitalActualizado.getUnidad());
                    signoVital.setRangoNormal(signoVitalActualizado.getRangoNormal());
                    signoVital.setEsPredeterminado(signoVitalActualizado.getEsPredeterminado());
                }
            });
            return parametroRepository.save(parametro);
        }
        return null;
    }

    // Actualizar un síntoma existente en una versión de parámetros
    public Parametro actualizarSintoma(String idParametro, String idSintoma, Sintoma sintomaActualizado) {
        Optional<Parametro> parametroOpt = parametroRepository.findById(idParametro);
        if (parametroOpt.isPresent()) {
            Parametro parametro = parametroOpt.get();
            parametro.getSintomas().forEach(sintoma -> {
                if (sintoma.getId().equals(idSintoma)) {
                    sintoma.setNombre(sintomaActualizado.getNombre());
                    sintoma.setValores(sintomaActualizado.getValores());
                    sintoma.setTieneEscala(sintomaActualizado.getTieneEscala());
                    sintoma.setEscala(sintomaActualizado.getEscala());
                    sintoma.setEsPredeterminado(sintomaActualizado.getEsPredeterminado());
                }
            });
            return parametroRepository.save(parametro);
        }
        return null;
    }

    // Eliminar un signo vital de un parámetro
    public Parametro eliminarSignoVital(String idParametro, String idSignoVital) {
        Optional<Parametro> parametroOpt = parametroRepository.findById(idParametro);
        if (parametroOpt.isPresent()) {
            Parametro parametro = parametroOpt.get();
            parametro.getSignosVitales().removeIf(signoVital -> signoVital.getId().equals(idSignoVital));
            return parametroRepository.save(parametro);  // Guardar el parámetro actualizado
        }
        return null;  // Si no se encuentra el parámetro, devolver null
    }

    // Eliminar un síntoma de un parámetro
    public Parametro eliminarSintoma(String idParametro, String idSintoma) {
        Optional<Parametro> parametroOpt = parametroRepository.findById(idParametro);
        if (parametroOpt.isPresent()) {
            Parametro parametro = parametroOpt.get();
            parametro.getSintomas().removeIf(sintoma -> sintoma.getId().equals(idSintoma));
            return parametroRepository.save(parametro);  // Guardar el parámetro actualizado
        }
        return null;  // Si no se encuentra el parámetro, devolver null
    }
}
