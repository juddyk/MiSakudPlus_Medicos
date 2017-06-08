package com.appdemo.mi_salud.misakudplus_medicos.Datos;

public class cursosDoctor {
    private String curso;
    private String institucion_curso;
    private String fiDia_curso;
    private String fiMes_curso;
    private String fiAnio_curso;
    private String ffDia_curso;
    private String ffMes_curso;
    private String ffAnio_curso;
    private long id;

    public cursosDoctor() {
        this.curso = "";
        this.institucion_curso ="";
        this.fiDia_curso ="";
        this.fiMes_curso = "";
        this.fiAnio_curso = "";
        this.ffDia_curso = "";
        this.ffMes_curso = "";
        this.ffAnio_curso = "";
        this.id=0;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}


