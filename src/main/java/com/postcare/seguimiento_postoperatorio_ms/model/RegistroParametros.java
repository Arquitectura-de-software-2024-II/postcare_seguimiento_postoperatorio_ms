package com.postcare.seguimiento_postoperatorio_ms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

public class RegistroParametros {

    @Id
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Field("fecha_registro")
    private Date fechaRegistro;
    @Field("parametros_control")
    private ParametrosControl parametrosControl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public ParametrosControl getParametrosControl() {
        return parametrosControl;
    }

    public void setParametrosControl(ParametrosControl parametrosControl) {
        this.parametrosControl = parametrosControl;
    }
}



class ParametrosControl {
    @Field("signos_vitales")
    private List<RegistroSignoVital> signosVitales;  // Lista de signos vitales registrados
    @Field("sintomas")
    private List<RegistroSintoma> sintomas;  // Lista de síntomas registrados

    @Field("sintomas_no_listados")
    private List<SintomaNoListados> sintomasNoListados;  // Sintomas no listados en el sistema

    // Getters y Setters

    public List<RegistroSignoVital> getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(List<RegistroSignoVital> signosVitales) {
        this.signosVitales = signosVitales;
    }

    public List<RegistroSintoma> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<RegistroSintoma> sintomas) {
        this.sintomas = sintomas;
    }

    public List<SintomaNoListados> getSintomasNoListados() {
        return sintomasNoListados;
    }

    public void setSintomasNoListados(List<SintomaNoListados> sintomasNoListados) {
        this.sintomasNoListados = sintomasNoListados;
    }


}


class RegistroSignoVital {
    @Field("nombre")
    private String nombre;
    private String unidad;
    private float valor;
    // Getters y Setters
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}

class RegistroSintoma {
    @Field("nombre")
    private String nombre;

    @Field("valor")
    private String valor;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}

class SintomaNoListados {
    private String nombre;
    private String descripcion;

    // Getters y Setters
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}



