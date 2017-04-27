package com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.appdemo.mi_salud.misakudplus_medicos.R;

public class DialogAvisoRegistro extends DialogFragment {

    public interface AvisoListener {
        void onClicAvisoPositive(DialogFragment dialog);
        void onClicAvisoNegative(DialogFragment dialog);
    }

    DialogAvisoRegistro.AvisoListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogAvisoRegistro.AvisoListener) activity;
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
        View view = inflater.inflate(R.layout.dialog_aviso_registro, null);

        ListView lst=(ListView) view.findViewById(R.id.listaDocumentos);

        ArrayAdapter<String> lstAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.documentos));

        lst.setAdapter(lstAdapter);


        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.opcion_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //ACCEPT
                            mListener.onClicAvisoPositive(DialogAvisoRegistro.this);
                    }
                })
                .setNegativeButton(R.string.opcion_nok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //CANCEL
                        mListener.onClicAvisoNegative(DialogAvisoRegistro.this);
                    }
                });


        return builder.create();

    }


}
