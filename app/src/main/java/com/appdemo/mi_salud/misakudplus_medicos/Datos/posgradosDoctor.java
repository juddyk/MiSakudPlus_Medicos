package com.appdemo.mi_salud.misakudplus_medicos.Datos;


public class posgradosDoctor {
    private String ttl_posgrado;
    private String pos_diploma;
    private String pos_acta;
    private long id;

    public posgradosDoctor() {
        this.ttl_posgrado = "";
        this.pos_diploma = "";
        this.pos_acta = "";
        this.id=0;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
