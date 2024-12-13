package com.postcare.seguimiento_postoperatorio_ms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "parametros")
public class Parametro {
    @Id
    private String id;

    @Field("signos_vitales")
    private List<SignoVital> signosVitales;

    @Field("sintomas")
    private List<Sintoma> sintomas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SignoVital> getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(List<SignoVital> signosVitales) {
        this.signosVitales = signosVitales;
    }

    public List<Sintoma> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<Sintoma> sintomas) {
        this.sintomas = sintomas;
    }
}

 class Rango {
    @Field("min")
    private Double min;

    @Field("max")
    private Double max;

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
