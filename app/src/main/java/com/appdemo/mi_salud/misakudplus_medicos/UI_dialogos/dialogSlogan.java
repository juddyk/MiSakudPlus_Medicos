package com.appdemo.mi_salud.misakudplus_medicos.UI_dialogos;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.appdemo.mi_salud.misakudplus_medicos.R;

public class dialogSlogan extends DialogFragment {

    EditText etA1;

    public interface sloganListener {
        void onSloganPositive(DialogFragment dialog,String slgn);
        void onSloganNegative(DialogFragment dialog);
    }

    dialogSlogan.sloganListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (dialogSlogan.sloganListener) activity;
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
        View view = inflater.inflate(R.layout.dialog_slogan, null);

        etA1=(EditText) view.findViewById(R.id.idSlogan);

        Bundle bndle = getArguments();

        etA1.setText(bndle.getString("actual"));

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.opcion_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //ACCEPT

                            mListener.onSloganPositive(dialogSlogan.this,String.valueOf(etA1.getText()));

                    }
                })
                .setNegativeButton(R.string.opcion_nok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //CANCEL
                        mListener.onSloganNegative(dialogSlogan.this);
                    }
                });


        return builder.create();

    }
}
