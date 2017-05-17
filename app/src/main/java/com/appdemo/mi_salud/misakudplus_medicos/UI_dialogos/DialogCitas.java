package com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.appdemo.mi_salud.misakudplus_medicos.R;

public class DialogCitas extends DialogFragment {

    long idItem;
    int pos;
    public interface CitasListener {
        void onCitasEliminar(DialogFragment dialog,int pos, long id);
        void onCitasCambiar(DialogFragment dialog,int pos, long id);
        void onCitasCerrar(DialogFragment dialog);
    }

    CitasListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (CitasListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ " must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_cita, null);

        Bundle bndl= getArguments();
        idItem=bndl.getLong("ID");
        pos=bndl.getInt("POS");

        TextView tv_nombre=(TextView) view.findViewById(R.id.nombre);
        TextView tv_fecha=(TextView) view.findViewById(R.id.fecha);
        TextView tv_hora=(TextView) view.findViewById(R.id.hour);
        TextView tv_sede=(TextView) view.findViewById(R.id.sede);
        TextView tv_tipo_consulta=(TextView) view.findViewById(R.id.tipoConsul);
        TextView tv_modalidad_atencion=(TextView) view.findViewById(R.id.modAtencion);
        TextView tv_tiempo_consulta=(TextView) view.findViewById(R.id.tiempoConsul);

        tv_nombre.setText(bndl.getString("nombre"));
        tv_fecha.setText(bndl.getString("fecha"));
        tv_hora.setText(bndl.getString("hora"));
        tv_sede.setText(bndl.getString("sede"));
        tv_tipo_consulta.setText(bndl.getString("tipo"));
        tv_modalidad_atencion.setText(bndl.getString("mod"));
        tv_tiempo_consulta.setText(bndl.getString("time"));

        builder.setView(view).setNeutralButton(R.string.opcion_delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //ELIMINAR
                mListener.onCitasEliminar(DialogCitas.this,pos,idItem);
            }
        }).setNegativeButton(R.string.opcion_change, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //CAMBIAR
                mListener.onCitasCambiar(DialogCitas.this,pos,idItem);
            }
        }).setPositiveButton(R.string.opcion_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //CERRAR
                mListener.onCitasCerrar(DialogCitas.this);
            }
        });
        return builder.create();

    }

}
