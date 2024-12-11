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

    @Field("nombre_paciente")
    private String nombrePaciente;

    @Field("en_recuperacion")
    private boolean enRecuperacion;

    private List<Cirugia> cirugias;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
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

    @Override
    public String toString() {
        return "RegistroDePaciente{" +
                "id='" + id + '\'' +
                ", nombrePaciente='" + nombrePaciente + '\'' +
                ", enRecuperacion=" + enRecuperacion +
                ", cirugias=" + cirugias +
                '}';
    }
}

class Cirugia {
    private String tipo;
    private String nombre;
    private String nombreMedico;
    private String descripcion;


    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Field("fecha_cirugia")  // Mapea "fecha_cirugia" a "fechaCirugia"
    private Date fechaCirugia;

    @Field("parametros_control")
    private ParametrosControl parametrosControl;  // Información de los parámetros de control para esta cirugía

    // Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public ParametrosControl getParametrosControl() {
        return parametrosControl;
    }

    public void setParametrosControl(ParametrosControl parametrosControl) {
        this.parametrosControl = parametrosControl;
    }

    @Override
    public String toString() {
        return "Cirugia{" +
                "tipo='" + tipo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nombreMedico='" + nombreMedico + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaCirugia=" + fechaCirugia +
                ", parametrosControl=" + parametrosControl +
                '}';
    }
}

class ParametrosControl {
    @Field("signos_vitales")
    private List<SignoVital> signosVitales;  // Lista de signos vitales registrados
    private List<Sintoma> sintomas;  // Lista de síntomas registrados

    @Field("sintomas_no_listados")
    private List<SintomaNoListados> sintomasNoListados;  // Sintomas no listados en el sistema

    // Getters y Setters
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

    public List<SintomaNoListados> getSintomasNoListados() {
        return sintomasNoListados;
    }

    public void setSintomasNoListados(List<SintomaNoListados> sintomasNoListados) {
        this.sintomasNoListados = sintomasNoListados;
    }

    @Override
    public String toString() {
        return "ParametrosControl{" +
                "signosVitales=" + signosVitales +
                ", sintomas=" + sintomas +
                ", sintomasNoListados=" + sintomasNoListados +
                '}';
    }
}

class SignoVital {
    private int idSignoVital;
    private String unidad;
    private List<Registro> registros;  // Lista de registros del signo vital

    // Getters y Setters
    public int getIdSignoVital() {
        return idSignoVital;
    }

    public void setIdSignoVital(int idSignoVital) {
        this.idSignoVital = idSignoVital;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    @Override
    public String toString() {
        return "SignoVital{" +
                "idSignoVital=" + idSignoVital +
                ", unidad='" + unidad + '\'' +
                ", registros=" + registros +
                '}';
    }
}

class Sintoma {
    private int idSintoma;
    private List<Registro> registros;  // Lista de registros del síntoma

    // Getters y Setters
    public int getIdSintoma() {
        return idSintoma;
    }

    public void setIdSintoma(int idSintoma) {
        this.idSintoma = idSintoma;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    @Override
    public String toString() {
        return "Sintoma{" +
                "idSintoma=" + idSintoma +
                ", registros=" + registros +
                '}';
    }
}

class SintomaNoListados {
    private String descripcion;
    @Field("fecha_registro")
    private Date fechaRegistro;

    // Getters y Setters
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "SintomaNoListados{" +
                "descripcion='" + descripcion + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}

class Registro {
    private Object valor;
    @Field("fecha_registro")
    private Date fechaRegistro;

    // Getters y Setters
    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "valor=" + valor +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
