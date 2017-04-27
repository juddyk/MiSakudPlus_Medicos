package com.appdemo.mi_salud.misakudplus_medicos;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.appdemo.mi_salud.misakudplus_medicos.UI_otras.*;


public class MainActivity extends AppCompatActivity {

    Button btnAction_Agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAction_Agenda=(Button) findViewById(R.id.idBtnAgenda);

        //SE CARGA EL FRAGMENT PARA MOSTRAR LAS NOTICIAS
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        noticias fragment = new noticias();
        ft.add(R.id.idFragmentNoticias, fragment);
        ft.commit();


        //Se asocia la acción de ver la agenda cuando se da CLICK en el boton de la Agenda
        btnAction_Agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this, agenda.class);
                startActivity(i);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_registro_medicos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actPerfil:
                startActivity(new Intent(MainActivity.this, perfilMedico.class));
                return true;
            case R.id.actSalir:
                Intent i=new Intent(MainActivity.this, logueo.class);
                i.putExtra("close", true);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
