package com.appdemo.mi_salud.misakudplus_medicos.Datos;


public class horarioDoctor {

    private int dia;
    private int tiempoXcita;
    private int horaI;
    private int minutoI;
    private int horaF;
    private int minutoF;
    private long id;

    public horarioDoctor() {
        this.dia = 0;
        this.tiempoXcita = 0;
        this.horaI = 0;
        this.minutoI = 0;
        this.horaF = 0;
        this.minutoF = 0;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getTiempoXcita() {
        return tiempoXcita;
    }

    public void setTiempoXcita(int tiempoXcita) {
        this.tiempoXcita = tiempoXcita;
    }

    public int getHoraI() {
        return horaI;
    }

    public void setHoraI(int horaI) {
        this.horaI = horaI;
    }

    public int getMinutoI() {
        return minutoI;
    }

    public void setMinutoI(int minutoI) {
        this.minutoI = minutoI;
    }

    public int getHoraF() {
        return horaF;
    }

    public void setHoraF(int horaF) {
        this.horaF = horaF;
    }

    public int getMinutoF() {
        return minutoF;
    }

    public void setMinutoF(int minutoF) {
        this.minutoF = minutoF;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
