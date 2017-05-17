package com.appdemo.mi_salud.misakudplus_medicos.Datos;

//Clase para la informacion de los medicos
public class datosMedico {

    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String fnDia;
    private String fnMes;
    private String fnAnio;
    private String tpDoc;
    private String numDoc;
    private String feDia;
    private String feMes;
    private String feAnio;
    private String genero;
    private String departamento;
    private String municipio;
    private String direccion;
    private String celular;
    private String fijo1;
    private String fijo2;
    private String correo1;
    private String correo2;
    private String psswrd;
    private String estado;
    /*
        * -1:inactivo
        * 0:pendiente
        * 1:activo
        * 2:suspendido
        * */
    private String puntaje;

    private String foto;
    private String slogan;
    private String profesion;/*AGREGAR EL GET PARA QUE SE PUEDA GUARDAR EN LA BASE DE DATOS*/

    private String registro_medico;
    private String tarjeta_prof;

    private String pregrado;
    private String pre_diploma;
    private String pre_acta;
    private String resolucion;

    public datosMedico() {
        this.nombre1 = "";
        this.nombre2 = "";
        this.apellido1 = "";
        this.apellido2 = "";
        this.fnDia = "";
        this.fnMes = "";
        this.fnAnio = "";
        this.tpDoc = "";
        this.numDoc ="";
        this.feDia ="";
        this.feMes ="";
        this.feAnio ="";
        this.genero ="";
        this.departamento ="";
        this.municipio ="";
        this.direccion ="";
        this.celular ="";
        this.fijo1 = "";
        this.fijo2 ="";
        this.correo1 ="";
        this.correo2 ="";
        this.psswrd ="";
        this.estado="-1";
        this.puntaje="0";
        this.foto ="";
        this.slogan ="";
        this.profesion ="";
        this.registro_medico ="";
        this.tarjeta_prof ="";
        this.pregrado ="";
        this.pre_diploma = "";
        this.pre_acta = "";
        this.resolucion = "";
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getFnDia() {
        return fnDia;
    }

    public void setFnDia(String fnDia) {
        this.fnDia = fnDia;
    }

    public String getFnMes() {
        return fnMes;
    }

    public void setFnMes(String fnMes) {
        this.fnMes = fnMes;
    }

    public String getFnAnio() {
        return fnAnio;
    }

    public void setFnAnio(String fnAnio) {
        this.fnAnio = fnAnio;
    }

    public String getTpDoc() {
        return tpDoc;
    }

    public void setTpDoc(String tpDoc) {
        this.tpDoc = tpDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getFeDia() {
        return feDia;
    }

    public void setFeDia(String feDia) {
        this.feDia = feDia;
    }

    public String getFeMes() {
        return feMes;
    }

    public void setFeMes(String feMes) {
        this.feMes = feMes;
    }

    public String getFeAnio() {
        return feAnio;
    }

    public void setFeAnio(String feAnio) {
        this.feAnio = feAnio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFijo1() {
        return fijo1;
    }

    public void setFijo1(String fijo1) {
        this.fijo1 = fijo1;
    }

    public void setFijo2(String fijo2) {
        this.fijo2 = fijo2;
    }

    public String getCorreo1() {
        return correo1;
    }

    public void setCorreo1(String correo1) {
        this.correo1 = correo1;
    }

    public void setCorreo2(String correo2) {
        this.correo2 = correo2;
    }

    public String getPsswrd() {
        return psswrd;
    }

    public void setPsswrd(String psswrd) {
        this.psswrd = psswrd;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getRegistro_medico() {
        return registro_medico;
    }

    public void setRegistro_medico(String registro_medico) {
        this.registro_medico = registro_medico;
    }

    public String getTarjeta_prof() {
        return tarjeta_prof;
    }

    public void setTarjeta_prof(String tarjeta_prof) {
        this.tarjeta_prof = tarjeta_prof;
    }

    public String getPregrado() {
        return pregrado;
    }

    public void setPregrado(String pregrado) {
        this.pregrado = pregrado;
    }

    public String getPre_diploma() {
        return pre_diploma;
    }

    public void setPre_diploma(String pre_diploma) {
        this.pre_diploma = pre_diploma;
    }

    public String getPre_acta() {
        return pre_acta;
    }

    public void setPre_acta(String pre_acta) {
        this.pre_acta = pre_acta;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getNombre2() {
        return nombre2;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getFijo2() {
        return fijo2;
    }

    public String getCorreo2() {
        return correo2;
    }

    public String getEstado() {
        return estado;
    }

    public String getPuntaje() {
        return puntaje;
    }

}
