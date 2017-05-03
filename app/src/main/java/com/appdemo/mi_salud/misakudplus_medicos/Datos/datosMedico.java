package com.appdemo.mi_salud.misakudplus_medicos.Datos;

//Clase para la informacion de los medicos
public class datosMedico {

    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private int fnDia;
    private int fnMes;
    private int fnAnio;
    private String tpDoc;
    private String numDoc;
    private int feDia;
    private int feMes;
    private int feAnio;
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
    private int fiDia_exp;
    private int fiMes_exp;
    private int fiAnio_exp;
    private int ffDia_exp;
    private int ffMes_exp;
    private int ffAnio_exp;

    private String curso;
    private String institucion_curso;
    private int fiDia_curso;
    private int fiMes_curso;
    private int fiAnio_curso;
    private int ffDia_curso;
    private int ffMes_curso;
    private int ffAnio_curso;

    private String direccion_sede;
    private String horai;
    private String horaf;

    private String[] consulta;
    private String[] atencion;
    private String[] medios;
    private String[] servicios;

    public datosMedico() {
        this.nombre1 = "";
        this.nombre2 = "";
        this.apellido1 = "";
        this.apellido2 = "";
        this.fnDia = 0;
        this.fnMes = 0;
        this.fnAnio = 0;
        this.tpDoc = "";
        this.numDoc ="";
        this.feDia =0;
        this.feMes =0;
        this.feAnio =0;
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
        this.fiDia_exp = 0;
        this.fiMes_exp =0;
        this.fiAnio_exp = 0;
        this.ffDia_exp = 0;
        this.ffMes_exp = 0;
        this.ffAnio_exp = 0;
        this.curso = "";
        this.institucion_curso ="";
        this.fiDia_curso = 0;
        this.fiMes_curso = 0;
        this.fiAnio_curso = 0;
        this.ffDia_curso = 0;
        this.ffMes_curso = 0;
        this.ffAnio_curso =0;
        this.direccion_sede ="";
        this.horai = "";
        this.horaf = "";
        this.consulta = new String[]{"", "", ""};
        this.atencion =new String[]{"","",""};
        this.medios =new String[]{"","",""};
        this.servicios =new String[]{"","",""};
    }
/*
    public datosMedico(String nombre1, String nombre2, String apellido1, String apellido2, int fnDia, int fnMes, int fnAnio, String tpDoc, String numDoc, int feDia, int feMes, int feAnio, String genero, String departamento, String municipio, String direccion, String celular, String fijo1, String fijo2, String correo1, String correo2, String psswrd, String foto, String slogan, String profesion, String registro_medico, String tarjeta_prof, String pregrado, String pre_diploma, String pre_acta, String resolucion, String ttl_posgrado, String pos_diploma, String pos_acta, String certificado_exp, String institucion_exp, String cargo_exp, int fiDia_exp, int fiMes_exp, int fiAnio_exp, int ffDia_exp, int ffMes_exp, int ffAnio_exp, String curso, String institucion_curso, int fiDia_curso, int fiMes_curso, int fiAnio_curso, int ffDia_curso, int ffMes_curso, int ffAnio_curso, String direccion_sede, String horai, String horaf, String[] consulta, String[] atencion, String[] medios, String[] servicios) {
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fnDia = fnDia;
        this.fnMes = fnMes;
        this.fnAnio = fnAnio;
        this.tpDoc = tpDoc;
        this.numDoc = numDoc;
        this.feDia = feDia;
        this.feMes = feMes;
        this.feAnio = feAnio;
        this.genero = genero;
        this.departamento = departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        this.celular = celular;
        this.fijo1 = fijo1;
        this.fijo2 = fijo2;
        this.correo1 = correo1;
        this.correo2 = correo2;
        this.psswrd = psswrd;
        this.foto = foto;
        this.slogan = slogan;
        this.profesion = profesion;
        this.registro_medico = registro_medico;
        this.tarjeta_prof = tarjeta_prof;
        this.pregrado = pregrado;
        this.pre_diploma = pre_diploma;
        this.pre_acta = pre_acta;
        this.resolucion = resolucion;
        this.ttl_posgrado = ttl_posgrado;
        this.pos_diploma = pos_diploma;
        this.pos_acta = pos_acta;
        this.certificado_exp = certificado_exp;
        this.institucion_exp = institucion_exp;
        this.cargo_exp = cargo_exp;
        this.fiDia_exp = fiDia_exp;
        this.fiMes_exp = fiMes_exp;
        this.fiAnio_exp = fiAnio_exp;
        this.ffDia_exp = ffDia_exp;
        this.ffMes_exp = ffMes_exp;
        this.ffAnio_exp = ffAnio_exp;
        this.curso = curso;
        this.institucion_curso = institucion_curso;
        this.fiDia_curso = fiDia_curso;
        this.fiMes_curso = fiMes_curso;
        this.fiAnio_curso = fiAnio_curso;
        this.ffDia_curso = ffDia_curso;
        this.ffMes_curso = ffMes_curso;
        this.ffAnio_curso = ffAnio_curso;
        this.direccion_sede = direccion_sede;
        this.horai = horai;
        this.horaf = horaf;
        this.consulta = consulta;
        this.atencion = atencion;
        this.medios = medios;
        this.servicios = servicios;
    }
*/
    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
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

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getFnDia() {
        return fnDia;
    }

    public void setFnDia(int fnDia) {
        this.fnDia = fnDia;
    }

    public int getFnMes() {
        return fnMes;
    }

    public void setFnMes(int fnMes) {
        this.fnMes = fnMes;
    }

    public int getFnAnio() {
        return fnAnio;
    }

    public void setFnAnio(int fnAnio) {
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

    public int getFeDia() {
        return feDia;
    }

    public void setFeDia(int feDia) {
        this.feDia = feDia;
    }

    public int getFeMes() {
        return feMes;
    }

    public void setFeMes(int feMes) {
        this.feMes = feMes;
    }

    public int getFeAnio() {
        return feAnio;
    }

    public void setFeAnio(int feAnio) {
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

    public String getFijo2() {
        return fijo2;
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

    public String getCorreo2() {
        return correo2;
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

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
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

    public int getFiDia_exp() {
        return fiDia_exp;
    }

    public void setFiDia_exp(int fiDia_exp) {
        this.fiDia_exp = fiDia_exp;
    }

    public int getFiMes_exp() {
        return fiMes_exp;
    }

    public void setFiMes_exp(int fiMes_exp) {
        this.fiMes_exp = fiMes_exp;
    }

    public int getFiAnio_exp() {
        return fiAnio_exp;
    }

    public void setFiAnio_exp(int fiAnio_exp) {
        this.fiAnio_exp = fiAnio_exp;
    }

    public int getFfDia_exp() {
        return ffDia_exp;
    }

    public void setFfDia_exp(int ffDia_exp) {
        this.ffDia_exp = ffDia_exp;
    }

    public int getFfMes_exp() {
        return ffMes_exp;
    }

    public void setFfMes_exp(int ffMes_exp) {
        this.ffMes_exp = ffMes_exp;
    }

    public int getFfAnio_exp() {
        return ffAnio_exp;
    }

    public void setFfAnio_exp(int ffAnio_exp) {
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

    public int getFiDia_curso() {
        return fiDia_curso;
    }

    public void setFiDia_curso(int fiDia_curso) {
        this.fiDia_curso = fiDia_curso;
    }

    public int getFiMes_curso() {
        return fiMes_curso;
    }

    public void setFiMes_curso(int fiMes_curso) {
        this.fiMes_curso = fiMes_curso;
    }

    public int getFiAnio_curso() {
        return fiAnio_curso;
    }

    public void setFiAnio_curso(int fiAnio_curso) {
        this.fiAnio_curso = fiAnio_curso;
    }

    public int getFfDia_curso() {
        return ffDia_curso;
    }

    public void setFfDia_curso(int ffDia_curso) {
        this.ffDia_curso = ffDia_curso;
    }

    public int getFfMes_curso() {
        return ffMes_curso;
    }

    public void setFfMes_curso(int ffMes_curso) {
        this.ffMes_curso = ffMes_curso;
    }

    public int getFfAnio_curso() {
        return ffAnio_curso;
    }

    public void setFfAnio_curso(int ffAnio_curso) {
        this.ffAnio_curso = ffAnio_curso;
    }

    public String getDireccion_sede() {
        return direccion_sede;
    }

    public void setDireccion_sede(String direccion_sede) {
        this.direccion_sede = direccion_sede;
    }

    public String getHorai() {
        return horai;
    }

    public void setHorai(String horai) {
        this.horai = horai;
    }

    public String getHoraf() {
        return horaf;
    }

    public void setHoraf(String horaf) {
        this.horaf = horaf;
    }

    public String[] getConsulta() {
        return consulta;
    }

    public void setConsulta(String[] consulta) {
        this.consulta = consulta;
    }

    public String[] getAtencion() {
        return atencion;
    }

    public void setAtencion(String[] atencion) {
        this.atencion = atencion;
    }

    public String[] getMedios() {
        return medios;
    }

    public void setMedios(String[] medios) {
        this.medios = medios;
    }

    public String[] getServicios() {
        return servicios;
    }

    public void setServicios(String[] servicios) {
        this.servicios = servicios;
    }
}
