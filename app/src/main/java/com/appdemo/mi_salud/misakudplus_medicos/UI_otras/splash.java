package com.appdemo.mi_salud.misakudplus_medicos.UI_otras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.appdemo.mi_salud.misakudplus_medicos.R;

import java.util.Timer;
import java.util.TimerTask;

public class splash extends AppCompatActivity {
    private static final long SPLASH_DELAY=3000;//Tiempo que dura el Splash en ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//Se esconde el Titulo de la app
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//Pantalla Completa
        setContentView(R.layout.activity_splash);//Establece el Layou Splash

        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                    startActivity(new Intent(splash.this,logueo.class));
                    finish();
            }
        };
        Timer timer=new Timer();
        timer.schedule(task,SPLASH_DELAY);

    }
}
