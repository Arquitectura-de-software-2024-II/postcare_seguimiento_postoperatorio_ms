package com.postcare.seguimiento_postoperatorio_ms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "registros_de_pacientes")
public class RegistroDePaciente {

    @Id
    private String id;


    @Field("en_recuperacion")
    private boolean enRecuperacion;

    private List<RegistroCirugia> cirugias;

    private List<RegistroParametros> registros;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEnRecuperacion() {
        return enRecuperacion;
    }

    public void setEnRecuperacion(boolean enRecuperacion) {
        this.enRecuperacion = enRecuperacion;
    }

    public List<RegistroCirugia> getCirugias() {
        return cirugias;
    }

    public void setCirugias(List<RegistroCirugia> cirugias) {
        this.cirugias = cirugias;
    }

    public List<RegistroParametros> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RegistroParametros> registros) {
        this.registros = registros;
    }

}
