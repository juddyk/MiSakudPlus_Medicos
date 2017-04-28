package com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos;

import android.support.annotation.NonNull;
import android.widget.Toast;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.appdemo.mi_salud.misakudplus_medicos.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DialogCalendar extends DialogFragment {

    Spinner spnDay;
    Spinner spnMonth;
    Spinner spnYear;
    public static int MAX_AGE=120;
    int dia=0, mes=0, anio=0,y;
    int codigo=-1;
    public interface DialogListener {
        void onCalendarPositiveClick(DialogFragment dialog,int code, int d, int m, int y);
        void onCalendarNegativeClick(DialogFragment dialog);
    }

    DialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_calendar, null);

        spnDay=(Spinner) view.findViewById(R.id.calDay);//Se asocia el Spinner de los Días del Dialog
        spnMonth=(Spinner) view.findViewById(R.id.calMonth);//Se asocia el Spinner de los Meses del Dialog
        spnYear=(Spinner) view.findViewById(R.id.calYear);//Se asocia el Spinner de los Años del Dialog

        Calendar calendar = Calendar.getInstance();
        y= calendar.get(Calendar.YEAR);

        List<String> yearList = new ArrayList<>();
        int i=0;
        yearList.add("--");
        while(i<MAX_AGE){//Se considera Personas con un maximo de Edad de 120 años
            yearList.add(String.valueOf(y-i));
            i++;
        }
        //Create a ArrayAdapter using arraylist and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,yearList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnYear.setAdapter(adapter);

        //Si el usuario ya ha escogido alguna fecha en este punto carga la opción por defecto
        Bundle bndle = getArguments();
        codigo=bndle.getInt("CODE",-1);
        spnDay.setSelection(bndle.getInt("day"));
        spnMonth.setSelection(bndle.getInt("month"));
        spnYear.setSelection(y-bndle.getInt("year")+1);

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.opcion_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //ACCEPT
                        if(anio==0 || dia==0 || mes==0){
                            Toast toast = Toast.makeText(getContext(), "Datos invalidos", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        anio=anio-1;
                        mListener.onCalendarPositiveClick(DialogCalendar.this, codigo,dia, mes, y-anio);
                    }
                })
                .setNegativeButton(R.string.opcion_nok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //CANCEL
                        mListener.onCalendarNegativeClick(DialogCalendar.this);

                    }
                });


        spnDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dia=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast toast = Toast.makeText(getContext(), "Seleccionar Día", Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mes=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast toast = Toast.makeText(getContext(), "Seleccionar Mes", Toast.LENGTH_SHORT);
                toast.show();

            }
        });


        spnYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                anio=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast toast = Toast.makeText(getContext(), "Seleccionar Año", Toast.LENGTH_SHORT);
                toast.show();

            }
        });


        return builder.create();

    }



}