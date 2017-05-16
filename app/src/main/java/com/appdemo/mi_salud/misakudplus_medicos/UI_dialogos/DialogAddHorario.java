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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.appdemo.mi_salud.misakudplus_medicos.R;

public class DialogAddHorario extends DialogFragment{

    Spinner spn_hhI,spn_mmF,spn_hhF,spn_mmI;
    Spinner spn_tiempo_consulta;
    LinearLayout ll;
    int time=0,hi=0,mi=0,hf=0,mf=0;

    public interface HourListener {
        void onHorarioPositive(DialogFragment dialog, int tiempo, int hi, int mi, int hf, int mf);
        void onHorarioNegative(DialogFragment dialog);
    }

    DialogAddHorario.HourListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (DialogAddHorario.HourListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ " must implement NoticeDialogListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_horario, null);

        spn_tiempo_consulta=(Spinner) view.findViewById(R.id.tiempoConsulta);//Se asocia el Spinner del tiempo por consulta;
        spn_hhI=(Spinner) view.findViewById(R.id.hhI);//Se asocia el Spinner de la hora inicial
        spn_mmI=(Spinner) view.findViewById(R.id.mmI);//Se asocia el Spinner del minuto inicial
        spn_hhF=(Spinner) view.findViewById(R.id.hhF);//Se asocia el Spinner de la hora final
        spn_mmF=(Spinner) view.findViewById(R.id.mmF);//Se asocia el Spinner del minuto final
        ll=(LinearLayout) view.findViewById(R.id.vista);

        spn_tiempo_consulta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    ll.setVisibility(View.VISIBLE);
                    switch(position){
                        case 1:
                            time=20;
                            break;
                        case 2:
                            time=30;
                            break;
                        case 3:
                            time=40;
                            break;
                        case 4:
                            time=45;
                            break;
                        case 5:
                            time=60;
                            break;
                        default:
                            break;
                    }
                }else{
                    ll.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spn_hhI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] horas=getResources().getStringArray(R.array.horas);
                if(position!=0){
                    hi=Integer.parseInt(horas[position]);
                }else{
                    hi=-1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spn_mmI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] horas=getResources().getStringArray(R.array.minutos);
                if(position!=0){
                    mi=Integer.parseInt(horas[position]);
                }else{
                    mi=-1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spn_hhF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] horas=getResources().getStringArray(R.array.horas);
                if(position!=0){
                    hf=Integer.parseInt(horas[position]);
                }else{
                    hf=-1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spn_mmF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] horas=getResources().getStringArray(R.array.minutos);
                if(position!=0){
                    mf=Integer.parseInt(horas[position]);
                }else{
                    mf=-1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.opcion_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //ACCEPT
                        if(hi!=-1 && mi!=-1 && hf!=-1 && mf!=-1){
                            if((hi==hf && mf-mi>20) || (hf>hi) ){
                                mListener.onHorarioPositive(DialogAddHorario.this,time,hi,mi,hf,mf);
                            }else{
                                Toast.makeText(getContext(),getResources().getString(R.string.wrongHorario),Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getContext(),getResources().getString(R.string.data_empty),Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.opcion_nok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //CANCEL
                        mListener.onHorarioNegative(DialogAddHorario.this);
                    }
                });


        return builder.create();

    }



}
