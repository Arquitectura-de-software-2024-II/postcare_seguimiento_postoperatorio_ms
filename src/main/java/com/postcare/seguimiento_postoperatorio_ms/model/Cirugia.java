package com.postcare.seguimiento_postoperatorio_ms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "cirugias")
public class Cirugia {

    @Id
    private String id;

    private String nombre;
    @Field("tipo")
    private List<TipoCirugia> tipo;

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

    public List<TipoCirugia> getTipo() {
        return tipo;
    }

    public void setTipo(List<TipoCirugia> tipo) {
        this.tipo = tipo;
    }
}

class TipoCirugia {
    @Field("_id_tipo_cirugia")
    private String id;

    @Field("nombre")
    private String nombre;

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
}
