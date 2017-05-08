package com.appdemo.mi_salud.misakudplus_medicos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appdemo.mi_salud.misakudplus_medicos.UI_otras.*;
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
import com.google.firebase.storage.StreamDownloadTask;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    //Objetos de la Interfaz
    Button btnAction_Agenda;
    EditText et_Slogan;
    ImageView iv_foto;
    TextView tv_nombre;
    //FireBase
    private DatabaseReference mDB;
    private static final String TAG = "logueo";
    private static final String TAG_medicos = "Medicos";
    private static final String TAG_foto = "foto";
    private static final String TAG_slogan = "slogan";
    private static final String TAG_nombre1 = "nombre1";
    private static final String TAG_apellido1 = "apellido1";
    //FIREBASE STORAGE
    FirebaseStorage storage;
    private StorageReference storageRef;
    //
    String usuarioId,rutaFoto,nombre,apellido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se instancias objetos del a interfaz
        btnAction_Agenda=(Button) findViewById(R.id.idBtnAgenda);
        et_Slogan=(EditText) findViewById(R.id.sloganDoctor);
        iv_foto=(ImageView) findViewById(R.id.fotoDoctor);
        tv_nombre=(TextView) findViewById(R.id.nombreDoctor);

        //FIREBASE STORAGE
        storage=FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://mi-salud-5965a.appspot.com");

        //Establecer el Eslogan
        usuarioId=getIntent().getStringExtra("usuario");
        mDB= FirebaseDatabase.getInstance().getReference().child(TAG_medicos).child(usuarioId);
        mDB.child(TAG_slogan).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String slgn= (String) snapshot.getValue();  //prints "Do you have data? You'll love Firebase."
                et_Slogan.setText(slgn);
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
                Toast.makeText(MainActivity.this, "Failed to load data.",Toast.LENGTH_SHORT).show();
            }
        });
        mDB.child(TAG_nombre1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                nombre= (String) snapshot.getValue();  //prints "Do you have data? You'll love Firebase."
            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
                Toast.makeText(MainActivity.this, "Failed to load data.",Toast.LENGTH_SHORT).show();
            }
        });
        mDB.child(TAG_apellido1).addValueEventListener(new ValueEventListener() {
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


        //Establecer la ruta(Firebase) de la foto
        mDB.child(TAG_foto).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                rutaFoto= (String) snapshot.getValue();  //prints "Do you have data? You'll love Firebase."
                if(rutaFoto!=null){
                    final long ONE_MEGABYTE = 1024 * 1024;
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
                            // Handle any errors
                        }
                    });

                }


            }
            @Override public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
                Toast.makeText(MainActivity.this, "Failed to load data.",Toast.LENGTH_SHORT).show();
            }
        });

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
