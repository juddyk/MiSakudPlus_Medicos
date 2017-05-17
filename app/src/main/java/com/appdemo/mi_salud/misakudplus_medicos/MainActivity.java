package com.appdemo.mi_salud.misakudplus_medicos;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appdemo.mi_salud.misakudplus_medicos.UI_otras.*;
import com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos.dialogSlogan;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements dialogSlogan.sloganListener{

    //Objetos de la Interfaz
    Button btnAction_Citas;
    ImageView iv_foto;
    ImageView iv_puntaje;
    ImageButton ib_editarSlogan;
    TextView tv_Slogan;
    TextView tv_nombre;
    TextView tv_estado;
    TextView tv_puntaje;
    //FireBase
    private DatabaseReference mDB;
    private static final String TAG = "logueo";
    private static final String TAG_medicos = "Medicos";
    private static final String TAG_data = "Data";
    private static final String TAG_foto = "foto";
    private static final String TAG_slogan = "slogan";
    private static final String TAG_nombre1 = "nombre1";
    private static final String TAG_apellido1 = "apellido1";
    private static final String TAG_estado = "estado";
    private static final String TAG_puntaje = "puntaje";
    //FIREBASE STORAGE
    FirebaseStorage storage;
    private StorageReference storageRef;
    //
    String usuarioId,rutaFoto,nombre,apellido,eslogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se instancias objetos del a interfaz
        btnAction_Citas=(Button) findViewById(R.id.idBtnCitas);
        ib_editarSlogan=(ImageButton) findViewById(R.id.guardarSlogan);
        iv_foto=(ImageView) findViewById(R.id.fotoDoctor);
        iv_puntaje=(ImageView) findViewById(R.id.puntajeDoctor);
        tv_nombre=(TextView) findViewById(R.id.nombreDoctor);
        tv_estado=(TextView) findViewById(R.id.estadoDoctor);
        tv_puntaje=(TextView) findViewById(R.id.pointDoctor);
        tv_Slogan=(TextView) findViewById(R.id.sloganDoctor);

        //FIREBASE STORAGE
        storage=FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://mi-salud-5965a.appspot.com");

        usuarioId=getIntent().getStringExtra("usuario");
        mDB= FirebaseDatabase.getInstance().getReference().child(TAG_medicos).child(usuarioId);

        //Establecer la ruta(Firebase) de la foto
        mDB.child(TAG_data).child(TAG_foto).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                rutaFoto= (String) snapshot.getValue();  //prints "Do you have data? You'll love Firebase."
                if(rutaFoto!=null){
                    storageRef.child(rutaFoto).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Toast.makeText(getApplicationContext(),getResources().getString(R.string.load_foto),Toast.LENGTH_SHORT).show();
                            Glide.with(getApplicationContext())
                                    .load(uri)
                                    .into(iv_foto);


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getApplicationContext(),getResources().getString(R.string.load_no_foto),Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
                Toast.makeText(MainActivity.this, "Failed to load data.",Toast.LENGTH_SHORT).show();
            }
        });

        //Establecer el Nombre del Doctor
        mDB.child(TAG_data).child(TAG_nombre1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                nombre= (String) snapshot.getValue();
                mDB.child(TAG_data).child(TAG_apellido1).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        apellido= (String) snapshot.getValue();  //prints "Do you have data? You'll love Firebase."
                        tv_nombre.setText("Dr. "+nombre+" "+apellido);
                    }
                    @Override public void onCancelled(DatabaseError error) {
                        Log.w(TAG, "loadPost:onCancelled", error.toException());
                        Toast.makeText(MainActivity.this, "Failed to load data.",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
                Toast.makeText(MainActivity.this, "Failed to load data.",Toast.LENGTH_SHORT).show();
            }
        });

        //Establecer Slogan
        mDB.child(TAG_data).child(TAG_slogan).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                eslogan= (String) snapshot.getValue();  //prints "Do you have data? You'll love Firebase."
                tv_Slogan.setText(eslogan);
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
                Toast.makeText(MainActivity.this, "Failed to load data.",Toast.LENGTH_SHORT).show();
            }
        });

        //Establecer Estado
        mDB.child(TAG_data).child(TAG_estado).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    String estado= (String) snapshot.getValue();
                    setEstado(Integer.parseInt(estado));
                }
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
                Toast.makeText(MainActivity.this, "Failed to load data.",Toast.LENGTH_SHORT).show();
            }
        });

        //Establecer Puntaje
        mDB.child(TAG_data).child(TAG_puntaje).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    String puntaje=  (String) snapshot.getValue();
                    setImagePuntaje(Float.parseFloat(puntaje));
                    tv_puntaje.setText(puntaje);
                }
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
                Toast.makeText(MainActivity.this, "Failed to load data.",Toast.LENGTH_SHORT).show();
            }
        });

        btnAction_Citas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this, citas.class);
                i.putExtra("cedula", usuarioId);
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
            case R.id.actAgenda:
                Intent k=new Intent(MainActivity.this, agenda.class);
                k.putExtra("cedula", usuarioId);
                startActivity(k);
                return true;
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

    public void onClicSaveSlogan(View view){
        showDialogSlogan();
    }

    public void setImagePuntaje(float puntaje){
        if(puntaje>=0 && puntaje<2){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_puntaje.setImageDrawable(getDrawable(R.drawable.star_1));
            }
        }else if(puntaje>=2 && puntaje<3){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_puntaje.setImageDrawable(getDrawable(R.drawable.star_2));
            }
        }else if(puntaje>=3 && puntaje<4){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_puntaje.setImageDrawable(getDrawable(R.drawable.star_3));
            }
        }else if(puntaje>=4 && puntaje<4.5){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_puntaje.setImageDrawable(getDrawable(R.drawable.star_4));
            }
        }else if(puntaje>=4.5 && puntaje<=5){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv_puntaje.setImageDrawable(getDrawable(R.drawable.star_5));
            }
        }
    }

    public void setEstado(int estado){
        switch (estado){
            case -1:
                tv_estado.setText(getResources().getString(R.string.estado_off));
                break;
            case 0:
                tv_estado.setText(getResources().getString(R.string.estado_pend));
                break;
            case 1:
                tv_estado.setText(getResources().getString(R.string.estado_on));
                break;
            default:
                tv_estado.setText(getResources().getString(R.string.estado_sos));
                break;
        }
    }

    public void showDialogSlogan() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new dialogSlogan();
        Bundle bundle = new Bundle();
        bundle.putString("actual",tv_Slogan.getText().toString());
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), getResources().getString(R.string.reg_slogan));
    }

    @Override
    public void onSloganPositive(DialogFragment dialog, String slgn) {
        tv_Slogan.setText(slgn);
        Map<String, Object> updates = new HashMap<>();
        updates.put("/"+TAG_slogan, slgn);
        mDB.updateChildren(updates);
        dialog.dismiss();
    }

    @Override
    public void onSloganNegative(DialogFragment dialog) {
        dialog.dismiss();
    }
}
