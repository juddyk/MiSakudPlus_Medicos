package com.appdemo.mi_salud.misakudplus_medicos.UI_otras;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.appdemo.mi_salud.misakudplus_medicos.R;


public class noticias extends Fragment {

    public noticias(){
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_noticias, container, false);

        ListView lst=(ListView) view.findViewById(R.id.listaNoticias);

        ArrayAdapter<String> lstAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.nwsLst));

        lst.setAdapter(lstAdapter);
        return view;
    }


}
