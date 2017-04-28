package com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;


import com.appdemo.mi_salud.misakudplus_medicos.R;

public class DialogHorario extends DialogFragment {

    Spinner spn_hora_i;
    Spinner spn_hora_f;
    int codigo;
    String hora_i,hora_f;
    String[] lista;

    public interface HourListener {
        void onHourPositive(DialogFragment dialog, int code, String hi, String hf);
        void onHourNegative(DialogFragment dialog);
    }

    DialogHorario.HourListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogHorario.HourListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_horario, null);

        Bundle bndle = getArguments();
        codigo=bndle.getInt("CODE",-1);
        lista=getResources().getStringArray(R.array.horas);

        spn_hora_i=(Spinner) view.findViewById(R.id.hourInicial);//Se asocia el Spinner de la hora inicial
        spn_hora_f=(Spinner) view.findViewById(R.id.hourFinal);//Se asocia el Spinner de la hora final

        spn_hora_i.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hora_i=lista[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spn_hora_f.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hora_f=lista[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.opcion_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //ACCEPT
                        if(hora_i.isEmpty() || hora_f.isEmpty()){
                            Toast.makeText(getContext(),getResources().getString(R.string.reg_data_nok), Toast.LENGTH_SHORT).show();
                        }
                        mListener.onHourPositive(DialogHorario.this,codigo,hora_i,hora_f);

                    }
                })
                .setNegativeButton(R.string.opcion_nok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //CANCEL
                        mListener.onHourNegative(DialogHorario.this);
                    }
                });


        return builder.create();

    }




}
