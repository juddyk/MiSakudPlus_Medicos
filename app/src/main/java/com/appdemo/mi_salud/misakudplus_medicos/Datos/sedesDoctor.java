package com.appdemo.mi_salud.misakudplus_medicos.Datos;

public class sedesDoctor {

    private String nombre;
    private String direccion;

    public sedesDoctor() {
        this.nombre = "";
        this.direccion = "";
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
}
