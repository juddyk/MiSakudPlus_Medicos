package com.appdemo.mi_salud.misakudplus_medicos.Datos;

//Clase para la informacion de las citas programadas

public class datosCita {
    private String fechaDia;
    private String fechaDiaNum;
    private String fechaMes;
    private String fechaAnio;
    private String hora;
    private String tipoConsulta;
    private String modalidadAtencion;
    private String sede;
    private String documPaciente;

    public datosCita() {
        this.fechaDia = "";
        this.fechaDiaNum="";
        this.fechaMes = "";
        this.fechaAnio = "";
        this.hora = "";
        this.tipoConsulta = "";
        this.modalidadAtencion = "";
        this.sede = "";
        this.documPaciente = "";
    }


    public String getFechaDia() {
        return fechaDia;
    }

    public void setFechaDia(String fechaDia) {
        this.fechaDia = fechaDia;
    }

    public String getFechaMes() {
        return fechaMes;
    }

    public void setFechaMes(String fechaMes) {
        this.fechaMes = fechaMes;
    }

    public String getFechaAnio() {
        return fechaAnio;
    }

    public void setFechaAnio(String fechaAnio) {
        this.fechaAnio = fechaAnio;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getModalidadAtencion() {
        return modalidadAtencion;
    }

    public void setModalidadAtencion(String modalidadAtencion) {
        this.modalidadAtencion = modalidadAtencion;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getDocumPaciente() {
        return documPaciente;
    }

    public void setDocumPaciente(String documPaciente) {
        this.documPaciente = documPaciente;
    }

    public String getFechaDiaNum() {
        return fechaDiaNum;
    }

    public void setFechaDiaNum(String fechaDiaNum) {
        this.fechaDiaNum = fechaDiaNum;
    }

}
