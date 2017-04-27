package com.appdemo.mi_salud.misakudplus_medicos.UI_otras;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.appdemo.mi_salud.misakudplus_medicos.MainActivity;
import com.appdemo.mi_salud.misakudplus_medicos.R;
import com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos.DialogAvisoRegistro;
import com.appdemo.mi_salud.misakudplus_medicos.UI_registro.registro;

public class logueo extends AppCompatActivity implements DialogAvisoRegistro.AvisoListener {
    Button ActionBtn_logueo;//Se crea boton de logueo
    Button ActionBtn_registro;//Se crea boton de registro
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//Se esconde el Titulo de la app
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//Pantalla Completa
        setContentView(R.layout.activity_logueo);

        //Instancias de la interfaz
        ActionBtn_logueo = (Button) findViewById(R.id.idBtnLogueo);//Se asocia con el boton de la interfaz
        ActionBtn_registro = (Button) findViewById(R.id.idBtnRegister);//Se asocia con el boton de la interfaz

        //Se asocia la acción de abrir la vista principal cuando se da CLICK en el boton de Iniciar Sesión
        ActionBtn_logueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(logueo.this, MainActivity.class);
                startActivity(i);
            }
        });

        //Se asocia la acción de abrir la vista de Registro cuando se da CLICK en el boton de Registrarse
        ActionBtn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAvisoDialog();
            }
        });


    }

    @Override
    public void onClicAvisoPositive(DialogFragment dialog) {
        Intent i =new Intent(logueo.this, registro.class);
        startActivity(i);
    }

    @Override
    public void onClicAvisoNegative(DialogFragment dialog) {

    }

    public void showAvisoDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogAvisoRegistro();
        Bundle bundle = new Bundle();
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "ADVERTENCIA");
    }
}
