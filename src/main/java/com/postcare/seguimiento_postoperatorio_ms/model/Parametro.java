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

 class SignoVital {
    @Field("_id_signo_vital")
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("unidad")
    private String unidad;

    @Field("rango_normal")
    private Rango rangoNormal;

    @Field("es_predeterminado")
    private Boolean esPredeterminado;

     public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }

     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Rango getRangoNormal() {
        return rangoNormal;
    }

    public void setRangoNormal(Rango rangoNormal) {
        this.rangoNormal = rangoNormal;
    }

    public Boolean getEsPredeterminado() {
        return esPredeterminado;
    }

    public void setEsPredeterminado(Boolean esPredeterminado) {
        this.esPredeterminado = esPredeterminado;
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

class Sintoma {
    @Field("_id_sintoma")
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("valores")
    private List<ValorSintoma> valores;

    @Field("tiene_escala")
    private Boolean tieneEscala;

    @Field("escala")
    private Rango escala;

    @Field("es_predeterminado")
    private Boolean esPredeterminado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ValorSintoma> getValores() {
        return valores;
    }

    public void setValores(List<ValorSintoma> valores) {
        this.valores = valores;
    }

    public Boolean getTieneEscala() {
        return tieneEscala;
    }

    public void setTieneEscala(Boolean tieneEscala) {
        this.tieneEscala = tieneEscala;
    }

    public Rango getEscala() {
        return escala;
    }

    public void setEscala(Rango escala) {
        this.escala = escala;
    }

    public Boolean getEsPredeterminado() {
        return esPredeterminado;
    }

    public void setEsPredeterminado(Boolean esPredeterminado) {
        this.esPredeterminado = esPredeterminado;
    }

}

class ValorSintoma {
    @Field("id_valor")
    private String idValor;

    @Field("nombre")
    private String nombre;

    public String getIdValor() {
        return idValor;
    }

    public void setIdValor(String idValor) {
        this.idValor = idValor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}