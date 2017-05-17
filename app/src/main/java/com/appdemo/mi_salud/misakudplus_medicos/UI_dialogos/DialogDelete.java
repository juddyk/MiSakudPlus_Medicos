package com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.appdemo.mi_salud.misakudplus_medicos.R;

public class DialogDelete extends DialogFragment {

    long idItem;
    String dia;
    int pos;

    public interface DeleteListener {
        void onDeletePositive(DialogFragment dialog, long id, String dia, int pos);
        void onDeleteNegative(DialogFragment dialog);
    }

    DeleteListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DeleteListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_delete, null);

        Bundle bndl= getArguments();
        idItem=bndl.getLong("ID");
        dia=bndl.getString("DIA");
        pos=bndl.getInt("POS");

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.opcion_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //ACCEPT
                        mListener.onDeletePositive(DialogDelete.this,idItem,dia,pos);

                    }
                })
                .setNegativeButton(R.string.opcion_nok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //CANCEL
                        mListener.onDeleteNegative(DialogDelete.this);
                    }
                });


        return builder.create();

    }



}
