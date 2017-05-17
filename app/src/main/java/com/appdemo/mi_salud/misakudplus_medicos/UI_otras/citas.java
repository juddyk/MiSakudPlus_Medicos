package com.appdemo.mi_salud.misakudplus_medicos.UI_otras;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.appdemo.mi_salud.misakudplus_medicos.Datos.citasAdapter;
import com.appdemo.mi_salud.misakudplus_medicos.Datos.datosCita;
import com.appdemo.mi_salud.misakudplus_medicos.R;
import com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos.DialogCitas;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class citas extends AppCompatActivity implements DialogCitas.CitasListener {

    ListView lstCitas;
    String usuarioId;
    List<datosCita> lstData;
    TextView mensaje;
    long lData = 0;
    private static final int FACTOR = 200;
    //FireBase
    private FirebaseDatabase fbDB;
    private DatabaseReference mDB;
    private static final String TAG = "citas";
    private static final String TAG_citas = "Citas";
    private static final String TAG_estado = "estado";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        usuarioId=getIntent().getStringExtra("cedula");

        //instancia de objetos de la interfaz
        lstCitas=(ListView) findViewById(R.id.listaCitas);
        mensaje=(TextView) findViewById(R.id.mensaje);
        //FIREBASE DATABASE
        fbDB= FirebaseDatabase.getInstance();

        //Se instancian las listas
        lstData = new ArrayList<>();

        //Extraer datos de Base de Datos
        existLista();
        actualizarConteo();

        //Acciones para el listview
        lstCitas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDialoginfo(position);
                return false;
            }
        });
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

    public void showDialoginfo(int pos) {
        String date=lstData.get(pos).getFechaDia()+"/"+lstData.get(pos).getFechaMes()+"/"+lstData.get(pos).getFechaAnio();
        String hour=citasAdapter.correcTime(lstData.get(pos).getHora())+":"+citasAdapter.correcTime(lstData.get(pos).getMinuto());

        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogCitas();
        Bundle bndl=new Bundle();
        bndl.putInt("POS",pos);
        bndl.putLong("ID",lstData.get(pos).getId());
        bndl.putString("nombre",lstData.get(pos).getNombrePaciente());
        bndl.putString("fecha",date);
        bndl.putString("hora",hour);
        bndl.putString("sede",lstData.get(pos).getSede());
        bndl.putString("tipo",lstData.get(pos).getTipoConsulta());
        bndl.putString("mod",lstData.get(pos).getModalidadAtencion());
        bndl.putString("time",lstData.get(pos).getTiempo());

        dialog.setArguments(bndl);
        dialog.show(getSupportFragmentManager(), getResources().getString(R.string.app_citas));
    }

    public void actualizar_lista(){
        citasAdapter adapter = new citasAdapter(lstData, this);
        ViewGroup.LayoutParams params = lstCitas.getLayoutParams();
        params.height = (lstCitas.getDividerHeight()+FACTOR)* lstData.size();
        lstCitas.setLayoutParams(params);
        lstCitas.setAdapter(adapter);
    }
    public void existLista(){
        mDB= fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");
        mDB.child(TAG_citas).child(usuarioId).orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.e("Count " ,""+snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    datosCita post = postSnapshot.getValue(datosCita.class);
                    lstData.add(post);
                }
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }
    public void actualizarConteo(){
        mDB=fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");

        mDB.child(TAG_citas).child(usuarioId).orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.e("Count " ,""+snapshot.getChildrenCount());
                lData=0;
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    datosCita post = postSnapshot.getValue(datosCita.class);
                    lData++;
                    if(lData==snapshot.getChildrenCount()){
                        lData=post.getId()+1;
                    }
                }
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });

    }


    @Override
    public void onCitasEliminar(DialogFragment dialog,int pos, long id) {
        String msg=getResources().getString(R.string.citas_notificar1)+" "+lstData.get(pos).getNombrePaciente()+" "+getResources().getString(R.string.citas_notificar2_cancel);
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
        //Eliminar en la base de datos
        deleteCita(id);
        //
        lstData.remove(pos);
        actualizar_lista();
        dialog.dismiss();
    }

    @Override
    public void onCitasCambiar(DialogFragment dialog,int pos, long id) {
        String msg=getResources().getString(R.string.citas_notificar1)+" "+lstData.get(pos).getNombrePaciente()+" "+getResources().getString(R.string.citas_notificar2_change);
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
        //Guardar en Base de Datos
        saveEstadoCita(id,getResources().getString(R.string.citas_estado_pos));
        //
        lstData.get(pos).setEstado(getResources().getString(R.string.citas_estado_pos));
        actualizar_lista();
        dialog.dismiss();
    }

    @Override
    public void onCitasCerrar(DialogFragment dialog) {
        dialog.dismiss();
    }

    public void deleteCita(long id){
        mDB= fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");
        mDB.child(TAG_citas).child(usuarioId).child(String.valueOf(id)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                snapshot.getRef().removeValue();
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }

    public void saveEstadoCita(long id, String estado){
        mDB= fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");
        Map<String, Object> updates = new HashMap<>();
        updates.put("/"+TAG_estado, estado);
        mDB.child(TAG_citas).child(usuarioId).child(String.valueOf(id)).updateChildren(updates);

    }

    public void temporal(View view){
        actualizar_lista();
        mensaje.setVisibility(View.VISIBLE);
        /*datosCita d1,d2,d3,d4,d5,d6;
        d1=new datosCita();
        d2=new datosCita();
        d3=new datosCita();
        d4=new datosCita();
        d5=new datosCita();
        d6=new datosCita();

        d1.setDocumMedico("0");
        d2.setDocumMedico("0");
        d3.setDocumMedico("0");
        d4.setDocumMedico("0");
        d5.setDocumMedico("0");
        d6.setDocumMedico("0");

        d1.setDocumPaciente("0");
        d2.setDocumPaciente("1");
        d3.setDocumPaciente("0");
        d4.setDocumPaciente("1");
        d5.setDocumPaciente("2");
        d6.setDocumPaciente("3");

        d1.setSede("demo");
        d2.setSede("demo");
        d3.setSede("demo");
        d4.setSede("demo");
        d5.setSede("demo");
        d6.setSede("demo");

        d1.setTipoConsulta("Teleconsulta");
        d2.setTipoConsulta("Telemedicina");
        d3.setTipoConsulta("Domicilio");
        d4.setTipoConsulta("Teleconsulta");
        d5.setTipoConsulta("Domicilio");
        d6.setTipoConsulta("Telemedicina");

        d1.setModalidadAtencion("Especializada de Control");
        d2.setModalidadAtencion("Especializada por Primera Vez");
        d3.setModalidadAtencion("Interconsulta Especilizada");
        d4.setModalidadAtencion("Especializada por Primera Vez");
        d5.setModalidadAtencion("Especializada de Control");
        d6.setModalidadAtencion("Interconsulta Especilizada");

        d1.setFechaAnio("2017");
        d2.setFechaAnio("2017");
        d3.setFechaAnio("2017");
        d4.setFechaAnio("2017");
        d5.setFechaAnio("2017");
        d6.setFechaAnio("2017");



        mDB=fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");
        mDB.child(TAG_citas).child(d1.getDocumMedico()).child("0").setValue(d1);
        mDB.child(TAG_citas).child(d2.getDocumMedico()).child("1").setValue(d2);
        mDB.child(TAG_citas).child(d3.getDocumMedico()).child("2").setValue(d3);
        mDB.child(TAG_citas).child(d4.getDocumMedico()).child("3").setValue(d4);
        mDB.child(TAG_citas).child(d5.getDocumMedico()).child("4").setValue(d5);
        mDB.child(TAG_citas).child(d6.getDocumMedico()).child("5").setValue(d6);*/

    }
}
