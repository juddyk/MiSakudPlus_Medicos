package com.appdemo.mi_salud.misakudplus_medicos.UI_registro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appdemo.mi_salud.misakudplus_medicos.MainActivity;
import com.appdemo.mi_salud.misakudplus_medicos.R;
import com.appdemo.mi_salud.misakudplus_medicos.UI_otras.logueo;

public class registroX extends Fragment {

    Button btnAction_registrar;

    public registroX() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_registro_x, container, false);

        btnAction_registrar=(Button) view.findViewById(R.id.idBtnRegister);

        btnAction_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver(v);
            }
        });
        return view;
    }

    public void volver(View v) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}
