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
import android.widget.Toast;

import com.appdemo.mi_salud.misakudplus_medicos.R;


public class DialogName extends DialogFragment {

    EditText etA1;
    EditText etA2;
    EditText etN1;
    EditText etN2;


    public interface NameListener {
        void onNamePositive(DialogFragment dialog, String a1, String a2, String n1, String n2);
        void onNameNegative(DialogFragment dialog);
    }

    NameListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NameListener) activity;
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
        View view = inflater.inflate(R.layout.dialog_name, null);

        etA1=(EditText) view.findViewById(R.id.idA1);
        etA2=(EditText) view.findViewById(R.id.idA2);
        etN1=(EditText) view.findViewById(R.id.idN1);
        etN2=(EditText) view.findViewById(R.id.idN2);

        Bundle bndle = getArguments();

        etA1.setText(bndle.getString("a1"));
        etA2.setText(bndle.getString("a2"));
        etN1.setText(bndle.getString("n1"));
        etN2.setText(bndle.getString("n2"));


        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.opcion_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //ACCEPT
                        if (etA1.getText().toString().isEmpty() || etN1.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), getResources().getString(R.string.data_empty), Toast.LENGTH_SHORT).show();
                        }else{
                            mListener.onNamePositive(DialogName.this, String.valueOf(etA1.getText()), String.valueOf(etA2.getText()), String.valueOf(etN1.getText()), String.valueOf(etN2.getText()));
                        }
                    }
                })
                .setNegativeButton(R.string.opcion_nok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //CANCEL
                        mListener.onNameNegative(DialogName.this);
                    }
                });


        return builder.create();

    }



}
