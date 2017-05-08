package com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.appdemo.mi_salud.misakudplus_medicos.R;

public class DialogHorario extends DialogFragment {
    int codigo;
    String[] lstHoras, lstSemana;
    String[] d,l,m,w,j,v,s;
    CheckBox cbD,cbL,cbM,cbW,cbJ,cbV,cbS;
    LinearLayout llD,llL,llM,llW,llJ,llV,llS;
    Spinner spnDI,spnDF,spnLI,spnLF,spnMI,spnMF,spnWI,spnWF,spnJI,spnJF,spnVI,spnVF,spnSI,spnSF;

    public interface HourListener {
        void onHourPositive(DialogFragment dialog, int code, String[] d,String[] l,String[] m,String[] w,String[] j,String[] v,String[] s);
        void onHourNegative(DialogFragment dialog);
    }

    DialogHorario.HourListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (DialogHorario.HourListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ " must implement NoticeDialogListener");
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_horario, null);

        Bundle bndle = getArguments();
        codigo=bndle.getInt("CODE",-1);
        lstHoras=getResources().getStringArray(R.array.horas);
        lstSemana=getResources().getStringArray(R.array.diasSemana);

        cbD=(CheckBox) view.findViewById(R.id.domingo);
        cbL=(CheckBox) view.findViewById(R.id.lunes);
        cbM=(CheckBox) view.findViewById(R.id.martes);
        cbW=(CheckBox) view.findViewById(R.id.miercoles);
        cbJ=(CheckBox) view.findViewById(R.id.jueves);
        cbV=(CheckBox) view.findViewById(R.id.viernes);
        cbS=(CheckBox) view.findViewById(R.id.sabado);
        cbD.setText(lstSemana[1]);
        cbL.setText(lstSemana[2]);
        cbM.setText(lstSemana[3]);
        cbW.setText(lstSemana[4]);
        cbJ.setText(lstSemana[5]);
        cbV.setText(lstSemana[6]);
        cbS.setText(lstSemana[7]);
        llD=(LinearLayout) view.findViewById(R.id.boxDomingo);
        llL=(LinearLayout) view.findViewById(R.id.boxLunes);
        llM=(LinearLayout) view.findViewById(R.id.boxMartes);
        llW=(LinearLayout) view.findViewById(R.id.boxMiercoles);
        llJ=(LinearLayout) view.findViewById(R.id.boxJueves);
        llV=(LinearLayout) view.findViewById(R.id.boxViernes);
        llS=(LinearLayout) view.findViewById(R.id.boxSabado);
        spnDI=(Spinner) view.findViewById(R.id.hourIDomingo);
        spnLI=(Spinner) view.findViewById(R.id.hourILunes);
        spnMI=(Spinner) view.findViewById(R.id.hourIMartes);
        spnWI=(Spinner) view.findViewById(R.id.hourIMiercoles);
        spnJI=(Spinner) view.findViewById(R.id.hourIJueves);
        spnVI=(Spinner) view.findViewById(R.id.hourIViernes);
        spnSI=(Spinner) view.findViewById(R.id.hourISabado);
        spnDF=(Spinner) view.findViewById(R.id.hourFDomingo);
        spnLF=(Spinner) view.findViewById(R.id.hourFLunes);
        spnMF=(Spinner) view.findViewById(R.id.hourFMartes);
        spnWF=(Spinner) view.findViewById(R.id.hourFMiercoles);
        spnJF=(Spinner) view.findViewById(R.id.hourFJueves);
        spnVF=(Spinner) view.findViewById(R.id.hourFViernes);
        spnSF=(Spinner) view.findViewById(R.id.hourFSabado);

        cbD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDomingo();
            }
        });
        spnDI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    d[0]=lstHoras[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spnDF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    d[1]=lstHoras[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        cbL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLunes();
            }
        });
        spnLI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    l[0]=lstHoras[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spnLF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    l[1]=lstHoras[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        cbM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMartes();
            }
        });
        spnMI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    m[0]=lstHoras[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spnMF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    m[1]=lstHoras[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        cbW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMiercoles();
            }
        });
        spnWI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    w[0]=lstHoras[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spnWF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    w[1]=lstHoras[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        cbJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkJueves();
            }
        });
        spnJI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    j[0]=lstHoras[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spnJF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    j[1]=lstHoras[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        cbV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkViernes();
            }
        });
        spnVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    v[0]=lstHoras[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spnVF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    v[1]=lstHoras[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        cbS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSabado();
            }
        });
        spnSI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    s[0]=lstHoras[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spnSF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    s[1]=lstHoras[position];
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
                        if(validarHorario()){
                            mListener.onHourPositive(DialogHorario.this,codigo,d,l,m,w,j,v,s);
                        }else{
                            Toast.makeText(getContext(),getResources().getString(R.string.horario_nok), Toast.LENGTH_SHORT).show();
                            mListener.onHourNegative(DialogHorario.this);
                        }

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

    public void checkDomingo(){
        d = new String[2];
        setCBD(cbD.isChecked());
        setCBL(false);
        setCBM(false);
        setCBW(false);
        setCBJ(false);
        setCBV(false);
        setCBS(false);
    }

    public void checkLunes(){
        l = new String[2];
        setCBL(cbL.isChecked());
        setCBD(false);
        setCBM(false);
        setCBW(false);
        setCBJ(false);
        setCBV(false);
        setCBS(false);
    }
    public void checkMartes(){
        m = new String[2];
        setCBM(cbM.isChecked());
        setCBD(false);
        setCBL(false);
        setCBW(false);
        setCBJ(false);
        setCBV(false);
        setCBS(false);
    }
    public void checkMiercoles(){
        w = new String[2];
        setCBW(cbW.isChecked());
        setCBD(false);
        setCBM(false);
        setCBL(false);
        setCBJ(false);
        setCBV(false);
        setCBS(false);
    }
    public void checkJueves(){
        j = new String[2];
        setCBJ(cbJ.isChecked());
        setCBD(false);
        setCBM(false);
        setCBW(false);
        setCBL(false);
        setCBV(false);
        setCBS(false);
    }
    public void checkViernes(){
        v = new String[2];
        setCBV(cbV.isChecked());
        setCBD(false);
        setCBM(false);
        setCBW(false);
        setCBJ(false);
        setCBL(false);
        setCBS(false);
    }
    public void checkSabado(){
        s = new String[2];
        setCBS(cbS.isChecked());
        setCBD(false);
        setCBM(false);
        setCBW(false);
        setCBJ(false);
        setCBV(false);
        setCBL(false);
    }

    public void setCBD(boolean estado){
        if(!estado){
            llD.setVisibility(View.GONE);
        }else{
            llD.setVisibility(View.VISIBLE);
        }
        cbD.setChecked(estado);
    }
    public void setCBL(boolean estado){
        if(!estado){
            llL.setVisibility(View.GONE);
        }else{
            llL.setVisibility(View.VISIBLE);
        }
        cbL.setChecked(estado);
    }
    public void setCBM(boolean estado){
        if(!estado){
            llM.setVisibility(View.GONE);
        }else{
            llM.setVisibility(View.VISIBLE);
        }
        cbM.setChecked(estado);
    }
    public void setCBW(boolean estado){
        if(!estado){
            llW.setVisibility(View.GONE);
        }else{
            llW.setVisibility(View.VISIBLE);
        }
        cbW.setChecked(estado);
    }
    public void setCBJ(boolean estado){
        if(!estado){
            llJ.setVisibility(View.GONE);
        }else{
            llJ.setVisibility(View.VISIBLE);
        }
        cbJ.setChecked(estado);
    }
    public void setCBV(boolean estado){
        if(!estado){
            llV.setVisibility(View.GONE);
        }else{
            llV.setVisibility(View.VISIBLE);
        }
        cbV.setChecked(estado);
    }
    public void setCBS(boolean estado){
        if(!estado){
            llS.setVisibility(View.GONE);
        }else{
            llS.setVisibility(View.VISIBLE);
        }
        cbS.setChecked(estado);
    }

    public boolean validarHorario(){
        return ((!d[0].isEmpty() && !d[1].isEmpty())|| (!l[0].isEmpty() && !l[1].isEmpty())|| (!m[0].isEmpty() && !m[1].isEmpty())|| (!w[0].isEmpty() && !w[1].isEmpty())|| (!j[0].isEmpty() && !j[1].isEmpty())|| (!v[0].isEmpty() && !v[1].isEmpty())|| (!s[0].isEmpty() && !s[1].isEmpty()));

    }

}
