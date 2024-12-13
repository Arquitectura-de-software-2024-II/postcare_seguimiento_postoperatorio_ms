package com.postcare.seguimiento_postoperatorio_ms.model;


import org.springframework.data.mongodb.core.mapping.Field;

public class SignoVital {
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
