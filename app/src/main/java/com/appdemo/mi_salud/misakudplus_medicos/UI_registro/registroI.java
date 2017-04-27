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


public class registroI extends Fragment {

    public registroI() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_registro_i, container, false);


        return view;
    }

}
