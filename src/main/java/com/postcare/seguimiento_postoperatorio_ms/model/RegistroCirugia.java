package com.postcare.seguimiento_postoperatorio_ms.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class RegistroCirugia {
    @Id
    private String id;

    @Field("nombre_cirugia")
    private String nombreCirugia;

    @Field("tipo_cirugia")
    private String tipoCirugia;
    @Field("nombre_medico")
    private String nombreMedico;
    private String descripcion;

    @Field("id_cirugia")
    private String idCirugia;


    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Field("fecha_cirugia")  // Mapea "fecha_cirugia" a "fechaCirugia"
    private Date fechaCirugia;

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCirugia() {
        return idCirugia;
    }

    public void setIdCirugia(String idCirugia) {
        this.idCirugia = idCirugia;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCirugia() {
        return fechaCirugia;
    }

    public void setFechaCirugia(Date fechaCirugia) {
        this.fechaCirugia = fechaCirugia;
    }

    public String getNombreCirugia() {
        return nombreCirugia;
    }

    public void setNombreCirugia(String nombreCirugia) {
        this.nombreCirugia = nombreCirugia;
    }

    public String getTipoCirugia() {
        return tipoCirugia;
    }

    public void setTipoCirugia(String tipoCirugia) {
        this.tipoCirugia = tipoCirugia;
    }


}