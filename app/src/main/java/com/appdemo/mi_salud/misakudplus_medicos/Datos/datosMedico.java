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
    private String profesion;

    private String registro_medico;
    private String tarjeta_prof;

    private String pregrado;
    private String pre_diploma;
    private String pre_acta;
    private String resolucion;

    private String ttl_posgrado;
    private String pos_diploma;
    private String pos_acta;

    private String certificado_exp;
    private String institucion_exp;
    private String cargo_exp;
    private String fiDia_exp;
    private String fiMes_exp;
    private String fiAnio_exp;
    private String ffDia_exp;
    private String ffMes_exp;
    private String ffAnio_exp;

    private String curso;
    private String institucion_curso;
    private String fiDia_curso;
    private String fiMes_curso;
    private String fiAnio_curso;
    private String ffDia_curso;
    private String ffMes_curso;
    private String ffAnio_curso;

    private String direccion_sede;


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
        this.estado="0";
        this.puntaje="0.1";
        this.foto ="";
        this.slogan ="";
        this.profesion ="";
        this.registro_medico ="";
        this.tarjeta_prof ="";
        this.pregrado ="";
        this.pre_diploma = "";
        this.pre_acta = "";
        this.resolucion = "";
        this.ttl_posgrado = "";
        this.pos_diploma = "";
        this.pos_acta = "";
        this.certificado_exp = "";
        this.institucion_exp = "";
        this.cargo_exp = "";
        this.fiDia_exp = "";
        this.fiMes_exp ="";
        this.fiAnio_exp = "";
        this.ffDia_exp = "";
        this.ffMes_exp = "";
        this.ffAnio_exp = "";
        this.curso = "";
        this.institucion_curso ="";
        this.fiDia_curso = "";
        this.fiMes_curso = "";
        this.fiAnio_curso = "";
        this.ffDia_curso = "";
        this.ffMes_curso = "";
        this.ffAnio_curso ="";
        this.direccion_sede ="";
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

    public String getTtl_posgrado() {
        return ttl_posgrado;
    }

    public void setTtl_posgrado(String ttl_posgrado) {
        this.ttl_posgrado = ttl_posgrado;
    }

    public String getPos_diploma() {
        return pos_diploma;
    }

    public void setPos_diploma(String pos_diploma) {
        this.pos_diploma = pos_diploma;
    }

    public String getPos_acta() {
        return pos_acta;
    }

    public void setPos_acta(String pos_acta) {
        this.pos_acta = pos_acta;
    }

    public String getCertificado_exp() {
        return certificado_exp;
    }

    public void setCertificado_exp(String certificado_exp) {
        this.certificado_exp = certificado_exp;
    }

    public String getInstitucion_exp() {
        return institucion_exp;
    }

    public void setInstitucion_exp(String institucion_exp) {
        this.institucion_exp = institucion_exp;
    }

    public String getCargo_exp() {
        return cargo_exp;
    }

    public void setCargo_exp(String cargo_exp) {
        this.cargo_exp = cargo_exp;
    }

    public String getFiDia_exp() {
        return fiDia_exp;
    }

    public void setFiDia_exp(String fiDia_exp) {
        this.fiDia_exp = fiDia_exp;
    }

    public String getFiMes_exp() {
        return fiMes_exp;
    }

    public void setFiMes_exp(String fiMes_exp) {
        this.fiMes_exp = fiMes_exp;
    }

    public String getFiAnio_exp() {
        return fiAnio_exp;
    }

    public void setFiAnio_exp(String fiAnio_exp) {
        this.fiAnio_exp = fiAnio_exp;
    }

    public String getFfDia_exp() {
        return ffDia_exp;
    }

    public void setFfDia_exp(String ffDia_exp) {
        this.ffDia_exp = ffDia_exp;
    }

    public String getFfMes_exp() {
        return ffMes_exp;
    }

    public void setFfMes_exp(String ffMes_exp) {
        this.ffMes_exp = ffMes_exp;
    }

    public String getFfAnio_exp() {
        return ffAnio_exp;
    }

    public void setFfAnio_exp(String ffAnio_exp) {
        this.ffAnio_exp = ffAnio_exp;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getInstitucion_curso() {
        return institucion_curso;
    }

    public void setInstitucion_curso(String institucion_curso) {
        this.institucion_curso = institucion_curso;
    }

    public String getFiDia_curso() {
        return fiDia_curso;
    }

    public void setFiDia_curso(String fiDia_curso) {
        this.fiDia_curso = fiDia_curso;
    }

    public String getFiMes_curso() {
        return fiMes_curso;
    }

    public void setFiMes_curso(String fiMes_curso) {
        this.fiMes_curso = fiMes_curso;
    }

    public String getFiAnio_curso() {
        return fiAnio_curso;
    }

    public void setFiAnio_curso(String fiAnio_curso) {
        this.fiAnio_curso = fiAnio_curso;
    }

    public String getFfDia_curso() {
        return ffDia_curso;
    }

    public void setFfDia_curso(String ffDia_curso) {
        this.ffDia_curso = ffDia_curso;
    }

    public String getFfMes_curso() {
        return ffMes_curso;
    }

    public void setFfMes_curso(String ffMes_curso) {
        this.ffMes_curso = ffMes_curso;
    }

    public String getFfAnio_curso() {
        return ffAnio_curso;
    }

    public void setFfAnio_curso(String ffAnio_curso) {
        this.ffAnio_curso = ffAnio_curso;
    }

    public String getDireccion_sede() {
        return direccion_sede;
    }

    public void setDireccion_sede(String direccion_sede) {
        this.direccion_sede = direccion_sede;
    }


}
