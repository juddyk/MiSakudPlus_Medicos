package com.appdemo.mi_salud.misakudplus_medicos.UI_otras;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appdemo.mi_salud.misakudplus_medicos.Datos.datosMedico;
import com.appdemo.mi_salud.misakudplus_medicos.R;
import com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos.DialogCalendar;
import com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos.DialogDireccion;
import com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos.DialogHorario;
import com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos.DialogName;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.Random;

/*
* CODIGO PARA LAS VENTANAS DE DIALOGOS
* (Para diferenciar la información a guardar)
*
* DialogName:
*   Nombre: 10
* DialogHorario:
*   Horario PAC: 90
* DialogDireccion:
*   Dirección:  10
*   Dirección PAC: 90
* DialogCalendar:
*   Fecha Nacimiento: 10
*   Fecha Expedicion: 11
*   Experiencia ->Fecha Inicio:   70
*   Experiencia ->Fecha Final:    71
*   Curso ->Fecha Inicio:   80
*   Curso ->Fecha Final:    81
*
* */

public class registro extends AppCompatActivity implements DialogName.NameListener,DialogHorario.HourListener,DialogDireccion.DirListener,DialogCalendar.DialogListener{
    //Etiquetas
    private static final int CODE_FOTO=301;
    private static final int CODE_TARJETA_PROF=401;
    private static final int CODE_DIPLOMA_PRE=501;
    private static final int CODE_ACTA_PRE=502;
    private static final int CODE_RESOLUCION=503;
    private static final int CODE_DIPLOMA_POS=601;
    private static final int CODE_ACTA_POS=602;
    private static final int CODE_CERTIFICADO_EXP=701;


    //Objetos para el control de la visualización
    TextView tv_i,tv_ii,tv_iii,tv_iv,tv_v,tv_vi,tv_vii,tv_viii,tv_ix,tv_x;
    ImageView iv_i,iv_ii,iv_iii,iv_iv,iv_v,iv_vi,iv_vii,iv_viii,iv_ix,iv_x;
    LinearLayout ll_i,ll_ii,ll_iii,ll_iv,ll_v,ll_vi,ll_vii,ll_viii,ll_ix,ll_x;
    ProgressBar barraAvance=null;
    InputMethodManager imm;

    //Objetos para el control de los ingresos de los datos
    TextView  tv1_editnombre,tv1_editFechaNacim,tv1_editFechaExp,tv2_editDirecc,tv4_loadTarjProf,tv5_loadDiploma,tv5_loadActa,tv5_loadResolucion,tv6_loadDiploma,tv6_loadActa,tv7_loadCertificado,tv7_editFechaI,tv7_editFechaF,tv8_editFechaI,tv8_editFechaF,tv9_editDirecc,tv9_editHorario;
    EditText et1_documento,et2_celular,et2_fijo1,et2_fijo2,et2_correo1,et2_correo2,et3_slogan,et4_registroMed,et5_titulo,et6_titulo,et7_institucion,et7_cargo,et8_curso,et8_institucion;
    Spinner spn1_genero, spn1_tipoDoc,spn2_departamento,spn2_municipio;
    ImageButton ib1_editName,ib1_editFechaNacim,ib1_editFechaExp,ib2_editDirecc,ib7_editFechaI,ib7_editFechaF,ib8_editFechaI,ib8_editFechaF,ib9_editDirecc,ib9_editHorario;
    ImageButton ib3_loadFoto,ib4_loadTarjProf,ib5_loadDiploma,ib5_loadActa,ib5_loadResolucion,ib6_loadDiploma,ib6_loadActa,ib7_loadCertificado;
    Button btn_registrar;
    //Variables Auxiliares
    private String[] lstMunc;
    int anio;
    public int pogreso=0;
    boolean ok1=false,ok2=false,ok3=false,ok4=false,ok5=false,ok6=false,ok7=false,ok8=false,ok9=false,ok10=false;

    //FIREBASE STORAGE
    private StorageReference storageRef;
    public UploadTask uploadTask;
    private datosMedico dM;
    //FIREBASE DATABASE
    private DatabaseReference mDB;
    private FirebaseDatabase fbDB;
    private static final String TAG_medicos = "Medicos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setTitle(getResources().getString(R.string.app_registro));

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //FIREBASE DATABASE
        fbDB=FirebaseDatabase.getInstance();
        //FIREBASE STORAGE
        FirebaseStorage storage=FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://mi-salud-5965a.appspot.com");
        dM=new datosMedico();
        Calendar calendar = Calendar.getInstance();
        anio = calendar.get(Calendar.YEAR);
        //Se inicializa Paciente
        dM.setFnAnio(anio + 1);

        //Se instancian objetos de Visualizacion
        instanciasObjetosVisualizacion();
        //Se instancian objetos para el registro
        instanciasObjetosRegistro();

        //Acciones objetos Registro I
        spn1_genero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    String[] lista=getResources().getStringArray(R.array.genero);
                    dM.setGenero(lista[position]);
                    setPogreso(2);
                    barraAvance.setProgress(getPogreso());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spn1_tipoDoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    String[] lista=getResources().getStringArray(R.array.tipdoc);
                    dM.setTpDoc(lista[position]);
                    setPogreso(2);
                    barraAvance.setProgress(getPogreso());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        et1_documento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                dM.setNumDoc(et1_documento.getText().toString());
            }
        });

        //Acciones objetos Registro II

        //Establecer la lista de los municipios de acuerdo al departamento
        spn2_departamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<CharSequence> adapter;
                if(position!=0){
                    String[] lista=getResources().getStringArray(R.array.departamentos);
                    dM.setDepartamento(lista[position]);
                    setPogreso(2);
                    barraAvance.setProgress(getPogreso());
                }
                switch (position) {
                    case 1:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun1, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun1);
                        break;
                    case 2:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun2, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun2);
                        break;
                    case 3:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun3, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun3);
                        break;
                    case 4:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun4, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun4);
                        break;
                    case 5:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun5, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun5);
                        break;
                    case 6:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun6, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun6);
                        break;
                    case 7:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun7, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun7);
                        break;
                    case 8:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun8, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun8);
                        break;
                    case 9:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun9, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun9);
                        break;
                    case 10:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun10, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun10);
                        break;
                    case 11:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun11, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun11);
                        break;
                    case 12:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun12, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun12);
                        break;
                    case 13:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun13, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun13);
                        break;
                    case 14:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun14, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun14);
                        break;
                    case 15:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun15, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun15);
                        break;
                    case 16:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun16, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun16);
                        break;
                    case 17:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun17, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun17);
                        break;
                    case 18:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun18, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun18);
                        break;
                    case 19:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun19, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun19);
                        break;
                    case 20:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun20, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun20);
                        break;
                    case 21:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun21, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun21);
                        break;
                    case 22:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun22, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun22);
                        break;
                    case 23:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun23, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun23);
                        break;
                    case 24:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun24, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun24);
                        break;
                    case 25:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun25, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun25);
                        break;
                    case 26:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun26, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun26);
                        break;
                    case 27:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun27, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun27);
                        break;
                    case 28:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun28, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun28);
                        break;
                    case 29:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun29, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun29);
                        break;
                    case 30:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun30, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun30);
                        break;
                    case 31:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun31, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun31);
                        break;
                    case 32:
                        //Se crea un ArrayAdapter usando el array Departamentos y el spinner por defecto
                        adapter = ArrayAdapter.createFromResource(registro.this, R.array.mun32, android.R.layout.simple_spinner_item);
                        //Se especifica el diseño a utilizar en los items del Spinner
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Aplica el adaptador al spinner
                        spn2_municipio.setAdapter(adapter);
                        lstMunc=getResources().getStringArray(R.array.mun32);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn2_municipio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    setPogreso(2);
                    barraAvance.setProgress(getPogreso());
                    dM.setMunicipio(lstMunc[position]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        et2_celular.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                dM.setCelular(et2_celular.getText().toString());
            }
        });
        et2_fijo1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                dM.setFijo1(et2_fijo1.getText().toString());
            }
        });
        et2_fijo2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                dM.setFijo2(et2_fijo2.getText().toString());
            }
        });
        et2_correo1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                dM.setCorreo1(et2_correo1.getText().toString());
            }
        });
        et2_correo2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                dM.setCorreo2(et2_correo2.getText().toString());
            }
        });

        //Acciones objetos Registro III
        ib3_loadFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.reg_select_photo)),CODE_FOTO);
            }
        });
        et3_slogan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                dM.setSlogan(et3_slogan.getText().toString());
            }
        });

        //Acciones objetos Registro IV
        et4_registroMed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                dM.setRegistro_medico(et4_registroMed.getText().toString());
            }
        });
        ib4_loadTarjProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setType("application/pdf").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.reg_select_file)),CODE_TARJETA_PROF);
            }
        });
        tv4_loadTarjProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setType("application/pdf").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.reg_select_file)),CODE_TARJETA_PROF);
            }
        });

        //Acciones objetos Registro V
        et5_titulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                dM.setPregrado(et5_titulo.getText().toString());
            }
        });
        ib5_loadDiploma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setType("application/pdf").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.reg_select_file)),CODE_DIPLOMA_PRE);
            }
        });
        tv5_loadDiploma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setType("application/pdf").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.reg_select_file)),CODE_DIPLOMA_PRE);
            }
        });
        ib5_loadActa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setType("application/pdf").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.reg_select_file)),CODE_ACTA_PRE);
            }
        });
        tv5_loadActa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setType("application/pdf").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.reg_select_file)),CODE_ACTA_PRE);
            }
        });
        ib5_loadResolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setType("application/pdf").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.reg_select_file)),CODE_RESOLUCION);
            }
        });
        tv5_loadResolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setType("application/pdf").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.reg_select_file)),CODE_RESOLUCION);
            }
        });

        //Acciones objetos Registro VI
        et6_titulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                dM.setTtl_posgrado(et6_titulo.getText().toString());
            }
        });
        ib6_loadDiploma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setType("application/pdf").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.reg_select_file)),CODE_DIPLOMA_POS);
            }
        });
        tv6_loadDiploma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setType("application/pdf").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.reg_select_file)),CODE_DIPLOMA_POS);
            }
        });
        ib6_loadActa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setType("application/pdf").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.reg_select_file)),CODE_ACTA_POS);
            }
        });
        tv6_loadActa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setType("application/pdf").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.reg_select_file)),CODE_ACTA_POS);
            }
        });
        //Acciones objetos Registro VII
        ib7_loadCertificado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setType("application/pdf").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.reg_select_file)),CODE_CERTIFICADO_EXP);
            }
        });
        tv7_loadCertificado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setType("application/pdf").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.reg_select_file)),CODE_CERTIFICADO_EXP);
            }
        });
        et7_institucion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                dM.setInstitucion_exp(et7_institucion.getText().toString());
            }
        });
        et7_cargo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                dM.setCargo_exp(et7_cargo.getText().toString());
            }
        });

        //Acciones objetos Registro VIII
        et8_curso.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                dM.setCurso(et8_curso.getText().toString());
            }
        });
        et8_institucion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                dM.setInstitucion_curso(et8_institucion.getText().toString());
            }
        });
        //Acciones objetos Registro IX

        //Acciones objetos Registro X


        //Boton de REGISTRAR
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ok1 && ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8 && ok9 && ok10){
                    Random rnd= new Random();
                    int psw=(int)(rnd.nextDouble()*10000);
                    while (psw<1000){//Garantiza que la contraseña generada sea de 4 digitos
                        psw=(int)(rnd.nextDouble()*10000);
                    }
                    dM.setPsswrd(String.valueOf(psw));
                    writeNewUser();
                    Intent i = new Intent(registro.this, logueo.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),"Su contraseña es: "+String.valueOf(dM.getPsswrd()),Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"¡Faltan datos!",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(!dM.getTpDoc().isEmpty() && !dM.getNumDoc().isEmpty()){
            if(resultCode==RESULT_OK){
                Uri uriPath = data.getData();
                StorageReference riversRef;
                String ruta;
                switch (requestCode){
                    case CODE_FOTO:
                        ruta="fotos/"+dM.getTpDoc()+dM.getNumDoc();
                        riversRef = storageRef.child(ruta);
                        uploadTask = riversRef.putFile(uriPath);
                        dM.setFoto(ruta);
                        //Establecer FOTO
                        ib3_loadFoto.setImageURI(uriPath);
                        setPogreso(5);
                        barraAvance.setProgress(getPogreso());
                        break;
                    case CODE_TARJETA_PROF:
                        ruta="documentos/tarjetas_profesionales/"+dM.getTpDoc()+dM.getNumDoc();
                        dM.setTarjeta_prof(ruta);
                        riversRef = storageRef.child(ruta);
                        uploadTask = riversRef.putFile(uriPath);
                        tv4_loadTarjProf.setText(getResources().getString(R.string.reg_select_ok));
                        setPogreso(5);
                        barraAvance.setProgress(getPogreso());
                        break;
                    case CODE_DIPLOMA_PRE:
                        ruta="documentos/pre_diplomas/"+dM.getTpDoc()+dM.getNumDoc();
                        dM.setPre_diploma(ruta);
                        riversRef = storageRef.child(ruta);
                        uploadTask = riversRef.putFile(uriPath);
                        tv5_loadDiploma.setText(getResources().getString(R.string.reg_select_ok));
                        setPogreso(2);
                        barraAvance.setProgress(getPogreso());
                        break;
                    case CODE_ACTA_PRE:
                        ruta="documentos/pre_actas/"+dM.getTpDoc()+dM.getNumDoc();
                        dM.setPre_acta(ruta);
                        riversRef = storageRef.child(ruta);
                        uploadTask = riversRef.putFile(uriPath);
                        tv5_loadActa.setText(getResources().getString(R.string.reg_select_ok));
                        setPogreso(2);
                        barraAvance.setProgress(getPogreso());
                        break;
                    case CODE_RESOLUCION:
                        ruta="documentos/resoluciones/"+dM.getTpDoc()+dM.getNumDoc();
                        dM.setResolucion(ruta);
                        riversRef = storageRef.child(ruta);
                        uploadTask = riversRef.putFile(uriPath);
                        tv5_loadResolucion.setText(getResources().getString(R.string.reg_select_ok));
                        setPogreso(2);
                        barraAvance.setProgress(getPogreso());
                        break;
                    case CODE_DIPLOMA_POS:
                        ruta="documentos/pos_diplomas/"+dM.getTpDoc()+dM.getNumDoc();
                        dM.setPos_diploma(ruta);
                        riversRef = storageRef.child(ruta);
                        uploadTask = riversRef.putFile(uriPath);
                        tv6_loadDiploma.setText(getResources().getString(R.string.reg_select_ok));
                        setPogreso(3);
                        barraAvance.setProgress(getPogreso());
                        break;
                    case CODE_ACTA_POS:
                        ruta="documentos/pos_actas/"+dM.getTpDoc()+dM.getNumDoc();
                        dM.setPos_acta(ruta);
                        riversRef = storageRef.child(ruta);
                        uploadTask = riversRef.putFile(uriPath);
                        tv6_loadActa.setText(getResources().getString(R.string.reg_select_ok));
                        setPogreso(3);
                        barraAvance.setProgress(getPogreso());
                        break;
                    case CODE_CERTIFICADO_EXP:
                        ruta="documentos/certificados_exp/"+dM.getTpDoc()+dM.getNumDoc();
                        dM.setCertificado_exp(ruta);
                        riversRef = storageRef.child(ruta);
                        uploadTask = riversRef.putFile(uriPath);
                        tv7_loadCertificado.setText(getResources().getString(R.string.reg_select_ok));
                        setPogreso(3);
                        barraAvance.setProgress(getPogreso());
                        break;
                    default:
                        break;
                }
            }else{
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.reg_select_no),Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.reg_error),Toast.LENGTH_SHORT).show();
        }

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

    public void instanciasObjetosVisualizacion(){
        tv_i=(TextView) findViewById(R.id.tvReg1);
        iv_i=(ImageView) findViewById(R.id.ivReg1);
        ll_i=(LinearLayout) findViewById(R.id.llReg1);
        tv_ii=(TextView) findViewById(R.id.tvReg2);
        iv_ii=(ImageView) findViewById(R.id.ivReg2);
        ll_ii=(LinearLayout) findViewById(R.id.llReg2);
        tv_iii=(TextView) findViewById(R.id.tvReg3);
        iv_iii=(ImageView) findViewById(R.id.ivReg3);
        ll_iii=(LinearLayout) findViewById(R.id.llReg3);
        tv_iv=(TextView) findViewById(R.id.tvReg4);
        iv_iv=(ImageView) findViewById(R.id.ivReg4);
        ll_iv=(LinearLayout) findViewById(R.id.llReg4);
        tv_v=(TextView) findViewById(R.id.tvReg5);
        iv_v=(ImageView) findViewById(R.id.ivReg5);
        ll_v=(LinearLayout) findViewById(R.id.llReg5);
        tv_vi=(TextView) findViewById(R.id.tvReg6);
        iv_vi=(ImageView) findViewById(R.id.ivReg6);
        ll_vi=(LinearLayout) findViewById(R.id.llReg6);
        tv_vii=(TextView) findViewById(R.id.tvReg7);
        iv_vii=(ImageView) findViewById(R.id.ivReg7);
        ll_vii=(LinearLayout) findViewById(R.id.llReg7);
        tv_viii=(TextView) findViewById(R.id.tvReg8);
        iv_viii=(ImageView) findViewById(R.id.ivReg8);
        ll_viii=(LinearLayout) findViewById(R.id.llReg8);
        tv_ix=(TextView) findViewById(R.id.tvReg9);
        iv_ix=(ImageView) findViewById(R.id.ivReg9);
        ll_ix=(LinearLayout) findViewById(R.id.llReg9);
        tv_x=(TextView) findViewById(R.id.tvReg10);
        iv_x=(ImageView) findViewById(R.id.ivReg10);
        ll_x=(LinearLayout) findViewById(R.id.llReg10);

        barraAvance=(ProgressBar) findViewById(R.id.progressRegistro);
        btn_registrar=(Button) findViewById(R.id.reg_btnRegistrar);
    }

    public void instanciasObjetosRegistro(){
        //Objetos registro 1
        tv1_editnombre=(TextView) findViewById(R.id.reg1_tvName);
        tv1_editFechaNacim=(TextView) findViewById(R.id.reg1_tvFecha);
        tv1_editFechaExp=(TextView) findViewById(R.id.reg1_tvFechExp);
        et1_documento=(EditText) findViewById(R.id.reg1_etDocumento);
        spn1_tipoDoc=(Spinner) findViewById(R.id.reg1_spnTipoDoc);
        spn1_genero=(Spinner) findViewById(R.id.reg1_spnGenero);
        ib1_editName=(ImageButton) findViewById(R.id.reg1_btnName);
        ib1_editFechaNacim=(ImageButton) findViewById(R.id.reg1_btnFecha);
        ib1_editFechaExp=(ImageButton) findViewById(R.id.reg1_btnFechExpe);
        //Objetos registro 2
        tv2_editDirecc=(TextView) findViewById(R.id.reg2_tvDireccion);
        et2_celular=(EditText) findViewById(R.id.reg2_etCelular);
        et2_fijo1=(EditText) findViewById(R.id.reg2_etFijo1);
        et2_fijo2=(EditText) findViewById(R.id.reg2_etFijo2);
        et2_correo1=(EditText) findViewById(R.id.reg2_etEmail1);
        et2_correo2=(EditText) findViewById(R.id.reg2_etEmail2);
        spn2_departamento=(Spinner) findViewById(R.id.reg2_spnDepartamento);
        spn2_municipio=(Spinner) findViewById(R.id.reg2_spnMunicipio);
        ib2_editDirecc=(ImageButton) findViewById(R.id.reg2_btnDireccion);
        //Objetos registro 3
        et3_slogan=(EditText) findViewById(R.id.reg3_etSlogan);
        ib3_loadFoto=(ImageButton) findViewById(R.id.reg3_ibFoto);
        //Objetos registro 4
        et4_registroMed=(EditText) findViewById(R.id.reg4_etRegistroMedico);
        tv4_loadTarjProf=(TextView) findViewById(R.id.reg4_tvTarjetaProf);
        ib4_loadTarjProf=(ImageButton) findViewById(R.id.reg4_ibTarjetaProf);
        //Objetos registro 5
        et5_titulo=(EditText) findViewById(R.id.reg5_etTituloPregrado);
        tv5_loadDiploma=(TextView) findViewById(R.id.reg5_tvDiplomaPregrado);
        tv5_loadActa=(TextView) findViewById(R.id.reg5_tvActaPregrado);
        tv5_loadResolucion=(TextView) findViewById(R.id.reg5_tvResolucion);
        ib5_loadDiploma=(ImageButton) findViewById(R.id.reg5_ibDiplomaPregrado);
        ib5_loadActa=(ImageButton) findViewById(R.id.reg5_ibActaPregrado);
        ib5_loadResolucion=(ImageButton) findViewById(R.id.reg5_ibResolucion);
        //Objetos registro 6
        et6_titulo=(EditText) findViewById(R.id.reg6_etTituloPosgrado);
        tv6_loadDiploma=(TextView) findViewById(R.id.reg6_tvDiplomaPosgrado);
        tv6_loadActa=(TextView) findViewById(R.id.reg6_tvActaPosgrado);
        ib6_loadDiploma=(ImageButton) findViewById(R.id.reg6_ibDiplomaPosgrado);
        ib6_loadActa=(ImageButton) findViewById(R.id.reg6_ibActaPosgrado);
        //Objetos registro 7
        et7_institucion=(EditText) findViewById(R.id.reg7_etInstitucion);
        et7_cargo=(EditText) findViewById(R.id.reg7_etCargo);
        tv7_editFechaI=(TextView) findViewById(R.id.reg7_tvFechaInicio);
        tv7_editFechaF=(TextView) findViewById(R.id.reg7_tvFechaFinal);
        tv7_loadCertificado=(TextView) findViewById(R.id.reg7_tvCertificado);
        ib7_loadCertificado=(ImageButton) findViewById(R.id.reg7_ibCertificado);
        ib7_editFechaI=(ImageButton) findViewById(R.id.reg7_ibFechaInicio);
        ib7_editFechaF=(ImageButton) findViewById(R.id.reg7_ibFechaFinal);
        //Objetos registro 8
        et8_curso=(EditText) findViewById(R.id.reg8_etPrograma);
        et8_institucion=(EditText) findViewById(R.id.reg8_etInstitucion);
        tv8_editFechaI=(TextView) findViewById(R.id.reg8_tvFechaInicio);
        tv8_editFechaF=(TextView) findViewById(R.id.reg8_tvFechaFinal);
        ib8_editFechaI=(ImageButton) findViewById(R.id.reg8_ibFechaInicio);
        ib8_editFechaF=(ImageButton) findViewById(R.id.reg8_ibFechaFinal);
        //Objetos registro 9
        tv9_editDirecc=(TextView) findViewById(R.id.reg9_tvDireccionPAC);
        tv9_editHorario=(TextView) findViewById(R.id.reg9_tvHourPAC);
        ib9_editDirecc=(ImageButton) findViewById(R.id.reg9_ibDireccionPAC);
        ib9_editHorario=(ImageButton) findViewById(R.id.reg9_ibHourPAC);
        //Objetos registro 10

    }

    public void desplegarReg1(View view){
        estadoReg1(false);
        estadoReg2(true);
        estadoReg3(true);
        estadoReg4(true);
        estadoReg5(true);
        estadoReg6(true);
        estadoReg7(true);
        estadoReg8(true);
        estadoReg9(true);
        estadoReg10(true);
        checkReg2();
        checkReg3();
        checkReg4();
        checkReg5();
        checkReg6();
        checkReg7();
        checkReg8();
        checkReg9();
        checkReg10();
    }

    public void estadoReg1(boolean in){
        if(in){//Oculta la vista
            ll_i.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(ok1){
                    iv_i.setImageDrawable(getDrawable(R.drawable.check));
                }else{
                    iv_i.setImageDrawable(getDrawable(R.drawable.hide));
                }
            }
        }else{//Muestra la vista
            ll_i.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_i.setImageDrawable(getDrawable(R.drawable.show));
            }
        }
    }

    public void desplegarReg2(View view){
        estadoReg2(false);
        estadoReg1(true);
        estadoReg3(true);
        estadoReg4(true);
        estadoReg5(true);
        estadoReg6(true);
        estadoReg7(true);
        estadoReg8(true);
        estadoReg9(true);
        estadoReg10(true);
        checkReg1();
        checkReg3();
        checkReg4();
        checkReg5();
        checkReg6();
        checkReg7();
        checkReg8();
        checkReg9();
        checkReg10();
    }

    public void estadoReg2(boolean in){
        if(in){//Oculta la vista
            ll_ii.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(ok2){
                    iv_ii.setImageDrawable(getDrawable(R.drawable.check));
                }else{
                    iv_ii.setImageDrawable(getDrawable(R.drawable.hide));
                }
            }
        }else{//Muestra la vista
            ll_ii.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_ii.setImageDrawable(getDrawable(R.drawable.show));
            }
        }
    }

    public void desplegarReg3(View view){
        estadoReg3(false);
        estadoReg1(true);
        estadoReg2(true);
        estadoReg4(true);
        estadoReg5(true);
        estadoReg6(true);
        estadoReg7(true);
        estadoReg8(true);
        estadoReg9(true);
        estadoReg10(true);
        checkReg2();
        checkReg1();
        checkReg4();
        checkReg5();
        checkReg6();
        checkReg7();
        checkReg8();
        checkReg9();
        checkReg10();
    }

    public void estadoReg3(boolean in){
        if(in){//Oculta la vista
            ll_iii.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(ok3){
                    iv_iii.setImageDrawable(getDrawable(R.drawable.check));
                }else{
                    iv_iii.setImageDrawable(getDrawable(R.drawable.hide));
                }
            }
        }else{//Muestra la vista
            ll_iii.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_iii.setImageDrawable(getDrawable(R.drawable.show));
            }
        }
    }

    public void desplegarReg4(View view){
        estadoReg4(false);
        estadoReg1(true);
        estadoReg2(true);
        estadoReg3(true);
        estadoReg5(true);
        estadoReg6(true);
        estadoReg7(true);
        estadoReg8(true);
        estadoReg9(true);
        estadoReg10(true);
        checkReg2();
        checkReg3();
        checkReg1();
        checkReg5();
        checkReg6();
        checkReg7();
        checkReg8();
        checkReg9();
        checkReg10();
    }

    public void estadoReg4(boolean in){
        if(in){//Oculta la vista
            ll_iv.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(ok4){
                    iv_iv.setImageDrawable(getDrawable(R.drawable.check));
                }else{
                    iv_iv.setImageDrawable(getDrawable(R.drawable.hide));
                }
            }
        }else{//Muestra la vista
            ll_iv.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_iv.setImageDrawable(getDrawable(R.drawable.show));
            }
        }
    }

    public void desplegarReg5(View view){
        estadoReg5(false);
        estadoReg1(true);
        estadoReg2(true);
        estadoReg3(true);
        estadoReg4(true);
        estadoReg6(true);
        estadoReg7(true);
        estadoReg8(true);
        estadoReg9(true);
        estadoReg10(true);
        checkReg2();
        checkReg3();
        checkReg4();
        checkReg1();
        checkReg6();
        checkReg7();
        checkReg8();
        checkReg9();
        checkReg10();
    }

    public void estadoReg5(boolean in){
        if(in){//Oculta la vista
            ll_v.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(ok5){
                    iv_v.setImageDrawable(getDrawable(R.drawable.check));
                }else{
                    iv_v.setImageDrawable(getDrawable(R.drawable.hide));
                }
            }
        }else{//Muestra la vista
            ll_v.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_v.setImageDrawable(getDrawable(R.drawable.show));
            }
        }
    }

    public void desplegarReg6(View view){
        estadoReg6(false);
        estadoReg1(true);
        estadoReg2(true);
        estadoReg3(true);
        estadoReg4(true);
        estadoReg5(true);
        estadoReg7(true);
        estadoReg8(true);
        estadoReg9(true);
        estadoReg10(true);
        checkReg2();
        checkReg3();
        checkReg4();
        checkReg5();
        checkReg1();
        checkReg7();
        checkReg8();
        checkReg9();
        checkReg10();
    }

    public void estadoReg6(boolean in){
        if(in){//Oculta la vista
            ll_vi.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(ok6){
                    iv_vi.setImageDrawable(getDrawable(R.drawable.check));
                }else{
                    iv_vi.setImageDrawable(getDrawable(R.drawable.hide));
                }
            }
        }else{//Muestra la vista
            ll_vi.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_vi.setImageDrawable(getDrawable(R.drawable.show));
            }
        }
    }

    public void desplegarReg7(View view){
        estadoReg7(false);
        estadoReg1(true);
        estadoReg2(true);
        estadoReg3(true);
        estadoReg4(true);
        estadoReg5(true);
        estadoReg6(true);
        estadoReg8(true);
        estadoReg9(true);
        estadoReg10(true);
        checkReg2();
        checkReg3();
        checkReg4();
        checkReg5();
        checkReg6();
        checkReg1();
        checkReg8();
        checkReg9();
        checkReg10();
    }

    public void estadoReg7(boolean in){
        if(in){//Oculta la vista
            ll_vii.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(ok7){
                    iv_vii.setImageDrawable(getDrawable(R.drawable.check));
                }else{
                    iv_vii.setImageDrawable(getDrawable(R.drawable.hide));
                }
            }
        }else{//Muestra la vista
            ll_vii.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_vii.setImageDrawable(getDrawable(R.drawable.show));
            }
        }
    }

    public void desplegarReg8(View view){
        estadoReg8(false);
        estadoReg1(true);
        estadoReg2(true);
        estadoReg3(true);
        estadoReg4(true);
        estadoReg5(true);
        estadoReg6(true);
        estadoReg7(true);
        estadoReg9(true);
        estadoReg10(true);
        checkReg2();
        checkReg3();
        checkReg4();
        checkReg5();
        checkReg6();
        checkReg7();
        checkReg1();
        checkReg9();
        checkReg10();
    }

    public void estadoReg8(boolean in){
        if(in){//Oculta la vista
            ll_viii.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(ok8){
                    iv_viii.setImageDrawable(getDrawable(R.drawable.check));
                }else{
                    iv_viii.setImageDrawable(getDrawable(R.drawable.hide));
                }
            }
        }else{//Muestra la vista
            ll_viii.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_viii.setImageDrawable(getDrawable(R.drawable.show));
            }
        }
    }

    public void desplegarReg9(View view){
        estadoReg9(false);
        estadoReg1(true);
        estadoReg2(true);
        estadoReg3(true);
        estadoReg4(true);
        estadoReg5(true);
        estadoReg6(true);
        estadoReg7(true);
        estadoReg8(true);
        estadoReg10(true);
        checkReg2();
        checkReg3();
        checkReg4();
        checkReg5();
        checkReg6();
        checkReg7();
        checkReg8();
        checkReg1();
        checkReg10();
    }

    public void estadoReg9(boolean in){
        if(in){//Oculta la vista
            ll_ix.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(ok9){
                    iv_ix.setImageDrawable(getDrawable(R.drawable.check));
                }else{
                    iv_ix.setImageDrawable(getDrawable(R.drawable.hide));
                }
            }
        }else{//Muestra la vista
            ll_ix.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_ix.setImageDrawable(getDrawable(R.drawable.show));
            }
        }
    }

    public void desplegarReg10(View view){
        estadoReg10(false);
        estadoReg1(true);
        estadoReg2(true);
        estadoReg3(true);
        estadoReg4(true);
        estadoReg5(true);
        estadoReg6(true);
        estadoReg7(true);
        estadoReg8(true);
        estadoReg9(true);
        //borrar luego
        checkReg10();

        checkReg2();
        checkReg3();
        checkReg4();
        checkReg5();
        checkReg6();
        checkReg7();
        checkReg8();
        checkReg9();
        checkReg1();
    }

    public void estadoReg10(boolean in){
        if(in){//Oculta la vista
            ll_x.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(ok10){
                    iv_x.setImageDrawable(getDrawable(R.drawable.check));
                }else{
                    iv_x.setImageDrawable(getDrawable(R.drawable.hide));
                }
            }
        }else{//Muestra la vista
            ll_x.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_x.setImageDrawable(getDrawable(R.drawable.show));
            }
        }
    }

    public void checkReg1(){
        if(!dM.getNombre1().isEmpty() && !dM.getApellido1().isEmpty() && !dM.getTpDoc().isEmpty() && !dM.getNumDoc().isEmpty() && !dM.getGenero().isEmpty()){
            if(dM.getFnAnio()!=0 && dM.getFeAnio()!=0 && dM.getFnMes()!=0 && dM.getFeMes()!=0 && dM.getFnDia()!=0 && dM.getFeDia()!=0){
                ok1=true;
                tv_i.setText(getResources().getString(R.string.reg_1_ok));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    iv_i.setImageDrawable(getDrawable(R.drawable.check));
                }
            }
        }
    }

    public void checkReg2(){
        if(!dM.getDireccion().isEmpty() && !dM.getDepartamento().isEmpty() && !dM.getMunicipio().isEmpty() && !dM.getCelular().isEmpty() && !dM.getFijo1().isEmpty() && !dM.getCorreo1().isEmpty()){
            ok2=true;
            tv_ii.setText(getResources().getString(R.string.reg_2_ok));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_ii.setImageDrawable(getDrawable(R.drawable.check));
            }
        }
    }

    public void checkReg3(){
        if(!dM.getSlogan().isEmpty() && !dM.getFoto().isEmpty()){
            ok3=true;
            tv_iii.setText(getResources().getString(R.string.reg_3_ok));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_iii.setImageDrawable(getDrawable(R.drawable.check));
            }
        }
    }

    public void checkReg4(){
        if(!dM.getRegistro_medico().isEmpty() && !dM.getTarjeta_prof().isEmpty()){
            ok4=true;
            tv_iv.setText(getResources().getString(R.string.reg_4_ok));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_iv.setImageDrawable(getDrawable(R.drawable.check));
            }
        }
    }

    public void checkReg5(){
        if(!dM.getPregrado().isEmpty() && !dM.getPre_diploma().isEmpty() && !dM.getPre_acta().isEmpty() && !dM.getResolucion().isEmpty()){
            ok5=true;
            tv_v.setText(getResources().getString(R.string.reg_5_ok));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_v.setImageDrawable(getDrawable(R.drawable.check));
            }
        }
    }

    public void checkReg6(){
        if(!dM.getTtl_posgrado().isEmpty() && !dM.getPos_diploma().isEmpty() && !dM.getPos_acta().isEmpty()){
            ok6=true;
            tv_vi.setText(getResources().getString(R.string.reg_6_ok));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_vi.setImageDrawable(getDrawable(R.drawable.check));
            }
        }
    }

    public void checkReg7(){
        if(!dM.getCertificado_exp().isEmpty() && !dM.getInstitucion_exp().isEmpty() && !dM.getCargo_exp().isEmpty()){
            if(dM.getFiAnio_exp()!=0 && dM.getFfAnio_exp()!=0 && dM.getFiMes_exp()!=0 && dM.getFfMes_exp()!=0 && dM.getFiDia_exp()!=0 && dM.getFfDia_exp()!=0) {
                ok7 = true;
                tv_vii.setText(getResources().getString(R.string.reg_7_ok));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    iv_vii.setImageDrawable(getDrawable(R.drawable.check));
                }
            }
        }
    }

    public void checkReg8(){
        if(!dM.getCurso().isEmpty() && !dM.getInstitucion_curso().isEmpty()){
            if(dM.getFiAnio_curso()!=0 && dM.getFfAnio_curso()!=0 && dM.getFiMes_curso()!=0 && dM.getFfMes_curso()!=0 && dM.getFiDia_curso()!=0 && dM.getFfDia_curso()!=0) {
                ok8 = true;
                tv_viii.setText(getResources().getString(R.string.reg_8_ok));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    iv_viii.setImageDrawable(getDrawable(R.drawable.check));
                }
            }
        }
    }

    public void checkReg9(){
        if(!dM.getDireccion_sede().isEmpty() && !dM.getHorai().isEmpty() && !dM.getHoraf().isEmpty()){
            ok9=true;
            tv_ix.setText(getResources().getString(R.string.reg_9_ok));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_ix.setImageDrawable(getDrawable(R.drawable.check));
            }
        }
    }

    public void checkReg10(){
        if(ok1 && ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8 && ok9){
            ok10=true;
            btn_registrar.setVisibility(View.VISIBLE);
        }else{
            btn_registrar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNamePositive(DialogFragment dialog, int code, String a1, String a2, String n1, String n2) {
        if(!n1.isEmpty() && !a1.isEmpty()){
            setPogreso(2);
            barraAvance.setProgress(getPogreso());
            dM.setNombre1(n1);
            dM.setNombre2(n2);
            dM.setApellido1(a1);
            dM.setApellido2(a2);
            tv1_editnombre.setText( n1 + " " + n2 + " " + a1 + " " + a2 + " ");
        }

        dialog.dismiss();
    }

    @Override
    public void onNameNegative(DialogFragment dialog) {
        dialog.dismiss();
    }

    @Override
    public void onHourPositive(DialogFragment dialog, int code, String hi, String hf) {
        if(!hi.isEmpty() && !hf.isEmpty()){
            if(code==90){
                setPogreso(2);
                barraAvance.setProgress(getPogreso());
                tv9_editHorario.setText(hi+" - "+hf);
                dM.setHorai(hi);
                dM.setHoraf(hf);
            }
        }


        dialog.dismiss();
    }

    @Override
    public void onHourNegative(DialogFragment dialog) {
        dialog.dismiss();
    }

    @Override
    public void onCalendarPositiveClick(DialogFragment dialog, int code, int d, int m, int y) {
        String data;
        if (d == 0) {
            data = "-- / ";
        } else {
            data = String.valueOf(d) + " / ";
        }
        if (m == 0) {
            data = data + "-- / ";
        } else {
            data = data + String.valueOf(m) + " / ";
        }
        if (anio - y == -1) {
            data = data + "-- ";
        } else {
            data = data + String.valueOf(y) + " ";
        }
        if(d!=0 && m!=0 && y!=0){
            if(code==10){//Fecha de Nacimiento
                dM.setFnDia(d);
                dM.setFnMes(m);
                dM.setFnAnio(y);
                setPogreso(2);
                barraAvance.setProgress(getPogreso());
                tv1_editFechaNacim.setText(data);
            }else if(code==11){//Fecha de Expedicion
                dM.setFeDia(d);
                dM.setFeMes(m);
                dM.setFeAnio(y);
                setPogreso(2);
                barraAvance.setProgress(getPogreso());
                tv1_editFechaExp.setText(data);
            }else if(code==70){//Fecha de Inicio
                dM.setFiDia_exp(d);
                dM.setFiMes_exp(m);
                dM.setFiAnio_exp(y);
                setPogreso(2);
                barraAvance.setProgress(getPogreso());
                tv7_editFechaI.setText(data);
            }else if(code==71){//Fecha de Finalizacion
                dM.setFfDia_exp(d);
                dM.setFfMes_exp(m);
                dM.setFfAnio_exp(y);
                setPogreso(1);
                barraAvance.setProgress(getPogreso());
                tv7_editFechaF.setText(data);
            }else if(code==80){//Fecha de Inicio
                dM.setFiDia_curso(d);
                dM.setFiMes_curso(m);
                dM.setFiAnio_curso(y);
                setPogreso(2);
                barraAvance.setProgress(getPogreso());
                tv8_editFechaI.setText(data);
            }else if(code==81){//Fecha de Finalizacion
                dM.setFfDia_curso(d);
                dM.setFfMes_curso(m);
                dM.setFfAnio_curso(y);
                setPogreso(2);
                barraAvance.setProgress(getPogreso());
                tv8_editFechaF.setText(data);
            }



        }
        dialog.dismiss();
    }

    @Override
    public void onCalendarNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
    }

    @Override
    public void onDirPositive(DialogFragment dialog, int code, String dir) {
        if(!dir.isEmpty()){
            if(code==10){//Direccion
                dM.setDireccion(dir);
                tv2_editDirecc.setText(dir);
                setPogreso(3);
                barraAvance.setProgress(getPogreso());
            }else if(code==90){//Direccion Sede
                dM.setDireccion_sede(dir);
                tv9_editDirecc.setText(dir);
                setPogreso(2);
                barraAvance.setProgress(getPogreso());
            }
        }
        dialog.dismiss();
    }

    @Override
    public void onDirNegative(DialogFragment dialog) {
        dialog.dismiss();
    }

    public void onClicDialogName(View view){
        showDialogName();
    }
    public void onClicDialogHorario(View view){
        showDialogHorario();
    }
    public void onClicDialogDireccion(View view){
        showDialogDireccion();
    }
    public void onClicDialogDireccionPAC(View view){
        showDialogDireccionPAC();
    }
    public void onClicDialogFechaNac(View view){
        showDialogCalendar_FechaNac();
    }
    public void onClicDialogFechaExp(View view){
        showDialogCalendar_FechaExp();
    }
    public void onClicDialogExpFI(View view){
        showDialogCalendar_ExpFI();
    }
    public void onClicDialogExpFF(View view){
        showDialogCalendar_ExpFF();
    }
    public void onClicDialogCursoFI(View view){
        showDialogCalendar_CursoFI();
    }
    public void onClicDialogCursoFF(View view){
        showDialogCalendar_CursoFF();
    }

    public void showDialogName() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogName();
        Bundle bundle = new Bundle();
        bundle.putInt("CODE",10);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), getResources().getString(R.string.reg_nombre));
    }
    public void showDialogHorario() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogHorario();
        Bundle bundle = new Bundle();
        bundle.putInt("CODE",90);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), getResources().getString(R.string.reg9_tittleDialog));
    }
    public void showDialogDireccion() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogDireccion();
        Bundle bundle = new Bundle();
        bundle.putInt("CODE",10);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(),getResources().getString(R.string.reg1_DialogDireccion));
    }
    public void showDialogDireccionPAC() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogDireccion();
        Bundle bundle = new Bundle();
        bundle.putInt("CODE",90);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(),getResources().getString(R.string.reg9_DialogDireccion));
    }
    public void showDialogCalendar_FechaNac() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogCalendar();
        Bundle bundle = new Bundle();
        bundle.putInt("CODE",10);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), getResources().getString(R.string.reg_fechanacimiento));
    }
    public void showDialogCalendar_FechaExp() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogCalendar();
        Bundle bundle = new Bundle();
        bundle.putInt("CODE",11);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), getResources().getString(R.string.reg_expdocumento));
    }
    public void showDialogCalendar_ExpFI() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogCalendar();
        Bundle bundle = new Bundle();
        bundle.putInt("CODE",70);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), getResources().getString(R.string.reg_fechaInicial));
    }
    public void showDialogCalendar_ExpFF() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogCalendar();
        Bundle bundle = new Bundle();
        bundle.putInt("CODE",71);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), getResources().getString(R.string.reg_fechaFinal));
    }
    public void showDialogCalendar_CursoFI() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogCalendar();
        Bundle bundle = new Bundle();
        bundle.putInt("CODE",80);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), getResources().getString(R.string.reg_fechaInicial));
    }
    public void showDialogCalendar_CursoFF() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogCalendar();
        Bundle bundle = new Bundle();
        bundle.putInt("CODE",81);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), getResources().getString(R.string.reg_fechaFinal));
    }

    public int getPogreso() {
        return pogreso;
    }

    public void setPogreso(int pogreso) {
        this.pogreso += pogreso;
    }

    private void writeNewUser() {
        mDB=fbDB.getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");
        mDB.child(TAG_medicos).child(String.valueOf(dM.getNumDoc())).setValue(dM);
    }

}
