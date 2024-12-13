package com.postcare.seguimiento_postoperatorio_ms.model;


import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class Sintoma {
    @Field("_id_sintoma")
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("valores")
    private List<String> valores;

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

    public List<String> getValores() {
        return valores;
    }

    public void setValores(List<String> valores) {
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