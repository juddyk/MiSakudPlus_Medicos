package com.appdemo.mi_salud.misakudplus_medicos.UI_otras;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appdemo.mi_salud.misakudplus_medicos.MainActivity;
import com.appdemo.mi_salud.misakudplus_medicos.R;
import com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos.DialogAvisoRegistro;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class logueo extends AppCompatActivity implements DialogAvisoRegistro.AvisoListener {

    //Objetos de la interfaz
    Button ActionBtn_logueo;//Se crea boton de logueo
    Button ActionBtn_registro;//Se crea boton de registro
    EditText et_Usuario;//Se crea objeto para leer el numero de documento
    EditText et_Contrasena;//Se crea objeto para leer la contraseña

    //Variable de SESION
    private SharedPreferences.Editor preferenceEditor;
    private static final int PREFERENCE_MODE_PRIVATE=0;
    boolean flag=false;
    //FireBase
    private DatabaseReference mDB;
    private static final String TAG = "logueo";
    private static final String TAG_medicos = "Medicos";
    private static final String TAG_data = "Data";
    private static final String TAG_contrasena = "psswrd";
    //Variables Axuliares
    public String uname,upass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//Se esconde el Titulo de la app
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//Pantalla Completa
        setContentView(R.layout.activity_logueo);

        //Preferencias (Variable de Sesion)
        SharedPreferences preferenceSettings;
        preferenceSettings = getPreferences(PREFERENCE_MODE_PRIVATE);
        preferenceEditor=preferenceSettings.edit();

        if(getIntent().getBooleanExtra("close",false)){
            preferenceEditor.putBoolean("flag",false);
            preferenceEditor.apply();
        }
        if(preferenceSettings.getBoolean("flag",flag)){
            Intent i=new Intent(logueo.this,MainActivity.class);
            i.putExtra("usuario",preferenceSettings.getString("user",""));
            startActivity(i);
            finish();
        }

        //Instancias de la interfaz
        ActionBtn_logueo = (Button) findViewById(R.id.idBtnLogueo);//Se asocia con el boton de la interfaz
        ActionBtn_registro = (Button) findViewById(R.id.idBtnRegister);//Se asocia con el boton de la interfaz
        et_Usuario = (EditText) findViewById(R.id.idUSER);//Se asocia con el editText de la interfaz
        et_Contrasena = (EditText) findViewById(R.id.idPASSWORD);//Se asocia con el editText de la interfaz

        //Se asocia la acción de abrir la vista principal cuando se da CLICK en el boton de Iniciar Sesión
        ActionBtn_logueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_Usuario.getText().toString().isEmpty() || et_Contrasena.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.log_wrong),Toast.LENGTH_SHORT).show();
                }else{
                    uname=et_Usuario.getText().toString();
                    upass=et_Contrasena.getText().toString();
                    mDB= FirebaseDatabase.getInstance().getReference().child(TAG_medicos).child(uname);
                    mDB.child(TAG_data).child(TAG_contrasena).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            String psw= (String) snapshot.getValue();  //prints "Do you have data? You'll love Firebase."
                            if(psw==null){//Si no se encontre una contraseña asociada al documetno ingresado
                                Toast.makeText(logueo.this,getResources().getString(R.string.log_uwrong),Toast.LENGTH_SHORT).show();
                            }else{
                                if(psw.equals(upass)){//Si las contraseñas coinciden
                                    preferenceEditor.putBoolean("flag",true);
                                    preferenceEditor.commit();
                                    preferenceEditor.putString("user",uname);
                                    preferenceEditor.commit();
                                    et_Usuario.setText("");
                                    et_Contrasena.setText("");
                                    Intent i =new Intent(logueo.this, MainActivity.class);
                                    i.putExtra("usuario",uname);
                                    startActivity(i);
                                    finish();
                                }else{
                                    Toast.makeText(logueo.this,getResources().getString(R.string.log_pwrong),Toast.LENGTH_SHORT).show();
                                    et_Contrasena.setText("");
                                }
                            }
                        }
                        @Override public void onCancelled(DatabaseError error) {
                            Log.w(TAG, "loadPost:onCancelled", error.toException());
                            Toast.makeText(logueo.this, "Failed to load data.",Toast.LENGTH_SHORT).show();
                        }
                    });

                }


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

    //Los siguientes métodos se encargan de la respuesta ante la ventana de dialogo
    @Override
    public void onClicAvisoPositive(DialogFragment dialog) {
        //Se crea la actividad para el REGISTROS
        Intent i =new Intent(logueo.this, registro.class);
        startActivity(i);
    }
    @Override
    public void onClicAvisoNegative(DialogFragment dialog) {

    }

    //Muestra la ventana de dialogo de Aviso antes del registro
    public void showAvisoDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogAvisoRegistro();
        Bundle bundle = new Bundle();
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "ADVERTENCIA");
    }
}
