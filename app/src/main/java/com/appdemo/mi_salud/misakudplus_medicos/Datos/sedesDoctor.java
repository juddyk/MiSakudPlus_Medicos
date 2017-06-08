package com.appdemo.mi_salud.misakudplus_medicos.Datos;

public class sedesDoctor {

    private String nombre;
    private String direccion;
    private long id;

    public sedesDoctor(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.id=0;
    }

    public sedesDoctor() {
        this.nombre = "";
        this.direccion = "";
        this.id=0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
