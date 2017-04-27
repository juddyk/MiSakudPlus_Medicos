package com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appdemo.mi_salud.misakudplus_medicos.R;

public class DialogDireccion extends DialogFragment{

    TextView dir;
    MultiAutoCompleteTextView complemento;
    Spinner lst,ltrs1,ltrs2,card1,card2;
    EditText nums1,nums2,nums3;
    String d1,d2,d3,d4,d5,d6,d7,d8,d9;
    int pos;

    public interface DirListener {
        void onDirPositive(DialogFragment dialog, String dir);
        void onDirNegative(DialogFragment dialog);
    }

    DirListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DirListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_direccion, null);
        d1="";d2="";d3="";d4="";d5="";d6="";d7="";d8="";d9="";
        dir=(TextView) view.findViewById(R.id.idDirF);
        lst=(Spinner) view.findViewById(R.id.dir1);//
        nums1=(EditText) view.findViewById(R.id.dir2);
        ltrs1=(Spinner) view.findViewById(R.id.dir3);//
        card1=(Spinner) view.findViewById(R.id.dir4);//
        nums2=(EditText) view.findViewById(R.id.dir5);
        ltrs2=(Spinner) view.findViewById(R.id.dir6);//
        card2=(Spinner) view.findViewById(R.id.dir7);//
        nums3=(EditText) view.findViewById(R.id.dir8);
        complemento=(MultiAutoCompleteTextView) view.findViewById(R.id.dir9);//

        String[] opciones = getResources().getStringArray(R.array.nomenclatura);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,opciones);

        complemento.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());//Vuelve a sugerir al separar por comas
        complemento.setThreshold(2);//Sugiere a partir de 1 caracter
        complemento.setAdapter(adapter);


        lst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    String[] sts = getResources().getStringArray(R.array.standar);
                    d1 = sts[position];
                    dir.setText(d1 + " " + d2 + " " + d3 + " " + d4 + " " + d5 + " " + d6 + " " + d7 + " " + d8 + " " + d9);
                }else{
                    d1="";
                    dir.setText(d2 + " " + d3+ " "+ d4 + " "+ d5 + " " + d6 + " " + d7 + " " + d8 + " " + d9);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ltrs1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    String[] sts = getResources().getStringArray(R.array.letras);
                    d3 = sts[position];
                    dir.setText(d1 + " " + d2 + " " + d3 + " " + d4 + " " + d5 + " " + d6 + " " + d7 + " " + d8 + " " + d9);
                }else{
                    d3="";
                    dir.setText(d1 + " " + d2 +" "+ d4 + " "+ d5 + " " + d6 + " " + d7 + " " + d8 + " " + d9);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ltrs2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    String[] sts = getResources().getStringArray(R.array.letras);
                    d6 = sts[position];
                    dir.setText(d1 + " " + d2 + " " + d3 + " " + d4 + " " + d5 + " " + d6 + " " + d7 + " " + d8 + " " + d9);
                }else{
                    d6="";
                    dir.setText(d1 + " " + d2 + " " + d3+ " "+ d4 + " "+ d5 +" " + d7 + " " + d8 + " " + d9);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        card1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    String[] sts = getResources().getStringArray(R.array.cardinales);
                    d4 = sts[position];
                    dir.setText(d1 + " " + d2 + " " + d3 + " " + d4 + " " + d5 + " " + d6 + " " + d7 + " " + d8 + " " + d9);
                }else{
                    d4="";
                    dir.setText(d1 + " " + d2 + " " + d3 + " "+ d5 + " " + d6 + " " + d7 + " " + d8 + " " + d9);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        card2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    String[] sts=getResources().getStringArray(R.array.cardinales);
                    d7=sts[position];
                    dir.setText(d1+" "+d2+" "+d3+" "+d4+" "+d5+" "+d6+" "+d7+" "+d8+" "+d9);
                }else{
                    d7="";
                    dir.setText(d1 + " " + d2 + " " + d3+ " "+ d4 + " "+ d5 + " " + d6 + " "+ d8 + " " + d9);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        complemento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                d9=complemento.getText().toString();
                dir.setText(d1 + " " + d2 + " " + d3+ " "+ d4 + " "+ d5 + " " + d6 + " "+ d8 + " " + d9);


            }
        });

        nums1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                d2=nums1.getText().toString();
                dir.setText(d1+" "+d2+" "+d3+" "+d4+" "+d5+" "+d6+" "+d7+" "+d8+" "+d9);

            }
        });

        nums2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                d5="#"+nums2.getText().toString();
                dir.setText(d1+" "+d2+" "+d3+" "+d4+" "+d5+" "+d6+" "+d7+" "+d8+" "+d9);

            }
        });

        nums3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                d8=nums3.getText().toString();
                dir.setText(d1+" "+d2+" "+d3+" "+d4+" "+d5+" "+d6+" "+d7+" "+d8+" "+d9);

            }
        });

        Bundle bundle = new Bundle();
        dir.setText(bundle.getString("dir"));

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.opcion_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //ACCEPT
                        if(dir.getText().toString().isEmpty()){
                            Toast.makeText(getContext(), getResources().getString(R.string.data_empty), Toast.LENGTH_SHORT).show();
                        }else{
                            mListener.onDirPositive(DialogDireccion.this, String.valueOf(dir.getText()));
                        }
                    }
                })
                .setNegativeButton(R.string.opcion_nok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //CANCEL
                        mListener.onDirNegative(DialogDireccion.this);
                    }
                });


        return builder.create();

    }

}
