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

    private List<Cirugia> cirugias;

    private List<Registro> registros;

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

    public List<Cirugia> getCirugias() {
        return cirugias;
    }

    public void setCirugias(List<Cirugia> cirugias) {
        this.cirugias = cirugias;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

}

class RegistroCirugia {
    @Id
    private String id;

    @Field("_id_cirugia")
    private String idCirugia;

    @Field("_id_tipo_cirugia")
    private String _id_tipo_cirugia;
    @Field("nombre_medico")
    private String nombreMedico;
    private String descripcion;

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

    public String get_id_tipo_cirugia() {
        return _id_tipo_cirugia;
    }

    public void set_id_tipo_cirugia(String _id_tipo_cirugia) {
        this._id_tipo_cirugia = _id_tipo_cirugia;
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
    @Field("_id_signo_vital")
    private String idSignoVital;
    private String unidad;
    private float valor;
    // Getters y Setters

    public String getIdSignoVital() {
        return idSignoVital;
    }

    public void setIdSignoVital(String idSignoVital) {
        this.idSignoVital = idSignoVital;
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
    @Field("_id_sintoma")
    private String idSintoma;

    @Field("_id_valor")
    private String idValor;

    public String getIdSintoma() {
        return idSintoma;
    }

    public void setIdSintoma(String idSintoma) {
        this.idSintoma = idSintoma;
    }

    public String getIdValor() {
        return idValor;
    }

    public void setIdValor(String idValor) {
        this.idValor = idValor;
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

class Registro {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Field("fecha_registro")
    private Date fechaRegistro;
    @Field("parametros_control")
    private ParametrosControl parametrosControl;


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


