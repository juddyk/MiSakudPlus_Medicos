package com.appdemo.mi_salud.misakudplus_medicos.UI_otras;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.appdemo.mi_salud.misakudplus_medicos.Datos.horarioDoctor;
import com.appdemo.mi_salud.misakudplus_medicos.R;
import com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos.DialogAddHorario;
import com.appdemo.mi_salud.misakudplus_medicos.Datos.agendaAdapter;
import com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos.DialogDelete;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class agenda extends AppCompatActivity implements DialogAddHorario.HourListener,DialogDelete.DeleteListener {

    boolean flagD = false, flagL = false, flagM = false, flagW = false, flagJ = false, flagV = false, flagS = false;
    boolean flagFAB=false;
    ViewGroup.LayoutParams paramsTV;
    FloatingActionButton fab;
    TextView tvDia;
    ListView lstHorario;
    List<horarioDoctor> lstD, lstL, lstM, lstW, lstJ, lstV, lstS;
    LinearLayout ll;
    String[] diasS;
    long lDom = 0, lLun = 0, lMar = 0, lMier = 0, lJue = 0, lVie = 0, lSab = 0;
    private static final int FACTOR = 140;
    String usuarioId;
    //FireBase
    private FirebaseDatabase fbDB;
    private DatabaseReference mDB;
    private static final String TAG = "agenda";
    private static final String TAG_medicos = "Medicos";
    private static final String TAG_horario = "Horario";
    private static final String TAG_domingo = "D";
    private static final String TAG_lunes = "L";
    private static final String TAG_martes = "M";
    private static final String TAG_miercoles = "W";
    private static final String TAG_jueves = "J";
    private static final String TAG_viernes = "V";
    private static final String TAG_sabado = "S";

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

        //Se revisa si ya hay un horario establecido
        existListaD();
        existListaL();
        existListaM();
        existListaW();
        existListaJ();
        existListaV();
        existListaS();
        //actualizarConteo();
        diasS=getResources().getStringArray(R.array.diaSemana);
        //Acciones para los objetos de la interfaz
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogHorario();
            }
        });

        //Accion para el ListView sobre los elementos que se desean eliminar
        lstHorario.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(flagD){
                    showDialogDelete(lstD.get(position).getId(),TAG_domingo,position);
                }else if(flagL){
                    showDialogDelete(lstL.get(position).getId(),TAG_lunes,position);
                }else if(flagM){
                    showDialogDelete(lstM.get(position).getId(),TAG_martes,position);
                }else if(flagW){
                    showDialogDelete(lstW.get(position).getId(),TAG_miercoles,position);
                }else if(flagJ){
                    showDialogDelete(lstJ.get(position).getId(),TAG_jueves,position);
                }else if(flagV){
                    showDialogDelete(lstV.get(position).getId(),TAG_viernes,position);
                }else if(flagS){
                    showDialogDelete(lstS.get(position).getId(),TAG_sabado,position);
                }
                return false;
            }
        });
    }

    //Los siguientes métodos que permiten especificar el día de la semana en la que se definirá el horario
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
        if(!flagFAB){
            flagFAB=true;
            fab.setVisibility(View.VISIBLE);
        }
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
        if(!flagFAB){
            flagFAB=true;
            fab.setVisibility(View.VISIBLE);
        }
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
        if(!flagFAB){
            flagFAB=true;
            fab.setVisibility(View.VISIBLE);
        }
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
        if(!flagFAB){
            flagFAB=true;
            fab.setVisibility(View.VISIBLE);
        }
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
        if(!flagFAB){
            flagFAB=true;
            fab.setVisibility(View.VISIBLE);
        }
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
        if(!flagFAB){
            flagFAB=true;
            fab.setVisibility(View.VISIBLE);
        }
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
        if(!flagFAB){
            flagFAB=true;
            fab.setVisibility(View.VISIBLE);
        }
    }

    //Los siguientes métodos son para actualizar la lista de los horarios
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

    //Los siguientes métodos son para ver que horarios existen en la base de datos
    public void existListaD(){
        mDB= fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");
        mDB.child(TAG_medicos).child(usuarioId).child(TAG_horario).child(TAG_domingo).orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                lDom=0;
                Log.e("Count " ,""+snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    horarioDoctor post = postSnapshot.getValue(horarioDoctor.class);
                    lstD.add(post);
                    lDom++;
                    if(lDom==snapshot.getChildrenCount()){
                        lDom=post.getId()+1;
                    }
                }
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });

    }
    public void existListaL(){
        mDB=fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");
        mDB.child(TAG_medicos).child(usuarioId).child(TAG_horario).child(TAG_lunes).orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                lLun=0;
                Log.e("Count " ,""+snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    horarioDoctor post = postSnapshot.getValue(horarioDoctor.class);
                    lstL.add(post);
                    lLun++;
                    if(lLun==snapshot.getChildrenCount()){
                        lLun=post.getId()+1;
                    }
                }
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });

    }
    public void existListaM(){
        mDB= fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");
        mDB.child(TAG_medicos).child(usuarioId).child(TAG_horario).child(TAG_martes).orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                lMar=0;
                Log.e("Count " ,""+snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    horarioDoctor post = postSnapshot.getValue(horarioDoctor.class);
                    lstM.add(post);
                    lMar++;
                    if(lMar==snapshot.getChildrenCount()){
                        lMar=post.getId()+1;
                    }
                }
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }
    public void existListaW(){
        mDB=fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");
        mDB.child(TAG_medicos).child(usuarioId).child(TAG_horario).child(TAG_miercoles).orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                lMier=0;
                Log.e("Count " ,""+snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    horarioDoctor post = postSnapshot.getValue(horarioDoctor.class);
                    lstW.add(post);
                    lMier++;
                    if(lMier==snapshot.getChildrenCount()){
                        lMier=post.getId()+1;
                    }
                }
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }
    public void existListaJ(){
        mDB=fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");
        mDB.child(TAG_medicos).child(usuarioId).child(TAG_horario).child(TAG_jueves).orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                lJue=0;
                Log.e("Count " ,""+snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    horarioDoctor post = postSnapshot.getValue(horarioDoctor.class);
                    lstJ.add(post);
                    lJue++;
                    if(lJue==snapshot.getChildrenCount()){
                        lJue=post.getId()+1;
                    }
                }
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }
    public void existListaV(){
        mDB=fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");
        mDB.child(TAG_medicos).child(usuarioId).child(TAG_horario).child(TAG_viernes).orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                lVie=0;
                Log.e("Count " ,""+snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    horarioDoctor post = postSnapshot.getValue(horarioDoctor.class);
                    lstV.add(post);
                    lVie++;
                    if(lVie==snapshot.getChildrenCount()){
                        lVie=post.getId()+1;
                    }
                }
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }
    public void existListaS(){
        mDB=fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");
        mDB.child(TAG_medicos).child(usuarioId).child(TAG_horario).child(TAG_sabado).orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                lSab=0;
                Log.e("Count " ,""+snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    horarioDoctor post = postSnapshot.getValue(horarioDoctor.class);
                    lstS.add(post);
                    lSab++;
                    if(lSab==snapshot.getChildrenCount()){
                        lSab=post.getId()+1;
                    }
                }
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }

    //Los siguientes métodos se encargan de la ventana de dialogo que permite agregar un horario
    public void showDialogHorario() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogAddHorario();
        dialog.show(getSupportFragmentManager(), getResources().getString(R.string.app_agenda));
    }
    public void showDialogDelete(long id, String dia,int pos){
        DialogFragment dialog = new DialogDelete();
        Bundle bundle = new Bundle();
        bundle.putLong("ID",id);
        bundle.putString("DIA",dia);
        bundle.putInt("POS",pos);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), getResources().getString(R.string.app_agenda));
    }

    //Los siguientes métodos se encargan de la respuesta de las ventanas de dialogo
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
            mDB= fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/").child(TAG_medicos).child(usuarioId).child(TAG_horario).child(TAG_domingo);
            mDB.child(String.valueOf(lDom)).setValue(hD);
            lDom++;
        } else if (flagL) {
            hD.setDia(2);
            hD.setId(lLun);
            lstL.add(hD);
            actualizar_listaL();
            mDB= fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/").child(TAG_medicos).child(usuarioId).child(TAG_horario).child(TAG_lunes);
            mDB.child(String.valueOf(lLun)).setValue(hD);
            lLun++;
        } else if (flagM) {
            hD.setDia(3);
            hD.setId(lMar);
            lstM.add(hD);
            actualizar_listaM();
            mDB= fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/").child(TAG_medicos).child(usuarioId).child(TAG_horario).child(TAG_martes);
            mDB.child(String.valueOf(lMar)).setValue(hD);
            lMar++;
        } else if (flagW) {
            hD.setDia(4);
            hD.setId(lMier);
            lstW.add(hD);
            actualizar_listaW();
            mDB= fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/").child(TAG_medicos).child(usuarioId).child(TAG_horario).child(TAG_miercoles);
            mDB.child(String.valueOf(lMier)).setValue(hD);
            lMier++;
        } else if (flagJ) {
            hD.setDia(5);
            hD.setId(lJue);
            lstJ.add(hD);
            actualizar_listaJ();
            mDB= fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/").child(TAG_medicos).child(usuarioId).child(TAG_horario).child(TAG_jueves);
            mDB.child(String.valueOf(lJue)).setValue(hD);
            lJue++;
        } else if (flagV) {
            hD.setDia(6);
            hD.setId(lVie);
            lstV.add(hD);
            actualizar_listaV();
            mDB= fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/").child(TAG_medicos).child(usuarioId).child(TAG_horario).child(TAG_viernes);
            mDB.child(String.valueOf(lVie)).setValue(hD);
            lVie++;
        } else if (flagS) {
            hD.setDia(7);
            hD.setId(lSab);
            lstS.add(hD);
            actualizar_listaS();
            mDB= fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/").child(TAG_medicos).child(usuarioId).child(TAG_horario).child(TAG_sabado);
            mDB.child(String.valueOf(lSab)).setValue(hD);
            lSab++;
        }
        dialog.dismiss();
    }
    @Override
    public void onHorarioNegative(DialogFragment dialog) {
        dialog.dismiss();
    }
    @Override
    public void onDeletePositive(DialogFragment dialog, long id, String dia, int pos) {
        mDB= fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");
        mDB.child(TAG_medicos).child(usuarioId).child(TAG_horario).child(dia).child(String.valueOf(id)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                snapshot.getRef().removeValue();
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
        if(dia.compareTo("D")==0){
            lstD.remove(pos);
            actualizar_listaD();
        }else if(dia.compareTo("L")==0){
            lstL.remove(pos);
            actualizar_listaL();
        }else if(dia.compareTo("M")==0){
            lstM.remove(pos);
            actualizar_listaM();
        }else if(dia.compareTo("W")==0){
            lstW.remove(pos);
            actualizar_listaW();
        }else if(dia.compareTo("J")==0){
            lstJ.remove(pos);
            actualizar_listaJ();
        }else if(dia.compareTo("V")==0){
            lstV.remove(pos);
            actualizar_listaV();
        }else if(dia.compareTo("S")==0){
            lstS.remove(pos);
            actualizar_listaS();
        }
        dialog.dismiss();
    }
    @Override
    public void onDeleteNegative(DialogFragment dialog) {
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