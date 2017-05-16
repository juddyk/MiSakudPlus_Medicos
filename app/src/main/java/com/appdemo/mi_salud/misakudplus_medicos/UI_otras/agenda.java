package com.appdemo.mi_salud.misakudplus_medicos.UI_otras;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.appdemo.mi_salud.misakudplus_medicos.Datos.horarioDoctor;
import com.appdemo.mi_salud.misakudplus_medicos.R;
import com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos.DialogAddHorario;
import com.appdemo.mi_salud.misakudplus_medicos.Datos.agendaAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class agenda extends AppCompatActivity implements DialogAddHorario.HourListener {

    boolean flagD = false, flagL = false, flagM = false, flagW = false, flagJ = false, flagV = false, flagS = false;
    ViewGroup.LayoutParams paramsTV;
    FloatingActionButton fab;
    TextView tvDia;
    ListView lstHorario;
    List<horarioDoctor> lstD, lstL, lstM, lstW, lstJ, lstV, lstS;
    LinearLayout ll;
    String[] diasS;
    int lDom = 0, lLun = 0, lMar = 0, lMier = 0, lJue = 0, lVie = 0, lSab = 0;
    private static final int FACTOR = 140;
    String usuarioId;
    //FireBase
    private FirebaseDatabase fbDB;
    private DatabaseReference mDB;
    private static final String TAG = "agenda";
    private static final String TAG_medicos = "Medicos";
    private static final String TAG_horario = "Horario";
    private static final String TAG_domingo = "D";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        setTitle(getResources().getString(R.string.app_agenda));
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        usuarioId=getIntent().getStringExtra("cedula");
        //FIREBASE DATABASE
        fbDB=FirebaseDatabase.getInstance();
        //Instancia de objetos de la interfaz
        fab = (FloatingActionButton) findViewById(R.id.fab);
        tvDia = (TextView) findViewById(R.id.txtLst);
        lstHorario = (ListView) findViewById(R.id.lista);
        ll = (LinearLayout) findViewById(R.id.vista);
        paramsTV = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //Se instancian las listas
        lstD = new ArrayList<>();
        lstL = new ArrayList<>();
        lstM = new ArrayList<>();
        lstW = new ArrayList<>();
        lstJ = new ArrayList<>();
        lstV = new ArrayList<>();
        lstS = new ArrayList<>();

        diasS=getResources().getStringArray(R.array.diaSemana);
        //Acciones para los objetos de la interfaz
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogHorario();
            }
        });



    }

    public void onClicDomingo(View view) {
        flagD = true;
        flagL = false;
        flagM = false;
        flagW = false;
        flagJ = false;
        flagV = false;
        flagS = false;
        tvDia.setText(diasS[1]);
        actualizar_listaD();
    }

    public void onClicLunes(View view) {
        flagL = true;
        flagD = false;
        flagM = false;
        flagW = false;
        flagJ = false;
        flagV = false;
        flagS = false;
        tvDia.setText(diasS[2]);
        actualizar_listaL();
    }

    public void onClicMartes(View view) {
        flagM = true;
        flagL = false;
        flagD = false;
        flagW = false;
        flagJ = false;
        flagV = false;
        flagS = false;
        tvDia.setText(diasS[3]);
        actualizar_listaM();
    }

    public void onClicMiercoles(View view) {
        flagW = true;
        flagL = false;
        flagM = false;
        flagD = false;
        flagJ = false;
        flagV = false;
        flagS = false;
        tvDia.setText(diasS[4]);
        actualizar_listaW();
    }

    public void onClicJueves(View view) {
        flagJ = true;
        flagL = false;
        flagM = false;
        flagW = false;
        flagD = false;
        flagV = false;
        flagS = false;
        tvDia.setText(diasS[5]);
        actualizar_listaJ();
    }

    public void onClicViernes(View view) {
        flagV = true;
        flagL = false;
        flagM = false;
        flagW = false;
        flagJ = false;
        flagD = false;
        flagS = false;
        tvDia.setText(diasS[6]);
        actualizar_listaV();
    }

    public void onClicSabado(View view) {
        flagS = true;
        flagL = false;
        flagM = false;
        flagW = false;
        flagJ = false;
        flagV = false;
        flagD = false;
        tvDia.setText(diasS[7]);
        actualizar_listaS();
    }

    public void showDialogHorario() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogAddHorario();
        dialog.show(getSupportFragmentManager(), getResources().getString(R.string.app_agenda));
    }

    @Override
    public void onHorarioPositive(DialogFragment dialog, int tiempo, int hi, int mi, int hf, int mf) {
        horarioDoctor hD = new horarioDoctor();
        hD.setTiempoXcita(tiempo);
        hD.setHoraI(hi);
        hD.setMinutoI(mi);
        hD.setHoraF(hf);
        hD.setMinutoF(mf);

        if (flagD) {
            hD.setDia(1);
            hD.setId(lDom);
            lstD.add(hD);
            actualizar_listaD();
            mDB= fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");
            mDB.child(TAG_medicos).child(usuarioId).child(TAG_horario).child(TAG_domingo).setValue(lstD);
            lDom++;
        } else if (flagL) {
            hD.setDia(2);
            hD.setId(lLun);
            lstL.add(hD);
            actualizar_listaL();
            lLun++;
        } else if (flagM) {
            hD.setDia(3);
            hD.setId(lMar);
            lstM.add(hD);
            actualizar_listaM();
            lMar++;
        } else if (flagW) {
            hD.setDia(4);
            hD.setId(lMier);
            lstW.add(hD);
            actualizar_listaW();
            lMier++;
        } else if (flagJ) {
            hD.setDia(5);
            hD.setId(lJue);
            lstJ.add(hD);
            actualizar_listaJ();
            lJue++;
        } else if (flagV) {
            hD.setDia(6);
            hD.setId(lVie);
            lstV.add(hD);
            actualizar_listaV();
            lVie++;
        } else if (flagS) {
            hD.setDia(7);
            hD.setId(lSab);
            lstS.add(hD);
            actualizar_listaS();
            lSab++;
        }
        dialog.dismiss();
    }

    public void actualizar_listaD(){
        agendaAdapter adapter = new agendaAdapter(lstD, this);
        ViewGroup.LayoutParams params = lstHorario.getLayoutParams();
        params.height = (lstHorario.getDividerHeight()+FACTOR)* lstD.size();
        lstHorario.setLayoutParams(params);
        lstHorario.setAdapter(adapter);
    }

    public void actualizar_listaL(){
        agendaAdapter adapter = new agendaAdapter(lstL, this);
        ViewGroup.LayoutParams params = lstHorario.getLayoutParams();
        params.height = (lstHorario.getDividerHeight()+FACTOR)* lstL.size();
        lstHorario.setLayoutParams(params);
        lstHorario.setAdapter(adapter);
    }

    public void actualizar_listaM(){
        agendaAdapter adapter = new agendaAdapter(lstM, this);
        ViewGroup.LayoutParams params = lstHorario.getLayoutParams();
        params.height = (lstHorario.getDividerHeight()+FACTOR)* lstM.size();
        lstHorario.setLayoutParams(params);
        lstHorario.setAdapter(adapter);
    }

    public void actualizar_listaW(){
        agendaAdapter adapter = new agendaAdapter(lstW, this);
        ViewGroup.LayoutParams params = lstHorario.getLayoutParams();
        params.height = (lstHorario.getDividerHeight()+FACTOR)* lstW.size();
        lstHorario.setLayoutParams(params);
        lstHorario.setAdapter(adapter);
    }

    public void actualizar_listaJ(){
        agendaAdapter adapter = new agendaAdapter(lstJ, this);
        ViewGroup.LayoutParams params = lstHorario.getLayoutParams();
        params.height = (lstHorario.getDividerHeight()+FACTOR)* lstJ.size();
        lstHorario.setLayoutParams(params);
        lstHorario.setAdapter(adapter);
    }

    public void actualizar_listaV(){
        agendaAdapter adapter = new agendaAdapter(lstV, this);
        ViewGroup.LayoutParams params = lstHorario.getLayoutParams();
        params.height = (lstHorario.getDividerHeight()+FACTOR)* lstV.size();
        lstHorario.setLayoutParams(params);
        lstHorario.setAdapter(adapter);
    }

    public void actualizar_listaS(){
        agendaAdapter adapter = new agendaAdapter(lstS, this);
        ViewGroup.LayoutParams params = lstHorario.getLayoutParams();
        params.height = (lstHorario.getDividerHeight()+FACTOR)* lstS.size();
        lstHorario.setLayoutParams(params);
        lstHorario.setAdapter(adapter);
    }

    @Override
    public void onHorarioNegative(DialogFragment dialog) {
        dialog.dismiss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}