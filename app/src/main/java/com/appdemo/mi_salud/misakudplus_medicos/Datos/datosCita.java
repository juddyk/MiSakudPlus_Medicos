package com.appdemo.mi_salud.misakudplus_medicos.Datos;

//Clase para la informacion de las citas programadas

public class datosCita {
    private String fechaDia;
    private String fechaMes;
    private String fechaAnio;
    private String hora;
    private String minuto;
    private String tipoConsulta;
    private String modalidadAtencion;
    private String sede;
    private String documPaciente;
    private String documMedico;
    private String nombrePaciente;
    private String tiempo;
    private String estado;
    private long id;

    public datosCita() {
        this.fechaDia = "";
        this.fechaMes = "";
        this.fechaAnio = "";
        this.hora = "";
        this.minuto="";
        this.tipoConsulta = "";
        this.modalidadAtencion = "";
        this.sede = "";
        this.documPaciente = "";
        this.documMedico="";
        this.tiempo="";
        this.estado="";
        this.id=0;
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

    public String getMinuto() {
        return minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
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

    public String getDocumMedico() {
        return documMedico;
    }

    public void setDocumMedico(String documMedico) {
        this.documMedico = documMedico;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
