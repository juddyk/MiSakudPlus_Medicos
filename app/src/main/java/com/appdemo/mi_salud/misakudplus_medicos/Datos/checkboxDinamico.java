package com.appdemo.mi_salud.misakudplus_medicos.Datos;

public class checkboxDinamico {
    private String texto;
    private boolean checked;

    public checkboxDinamico() {
        this.texto = "";
        this.checked=false;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
