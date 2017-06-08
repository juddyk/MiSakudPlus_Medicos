package com.appdemo.mi_salud.misakudplus_medicos.Datos;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.appdemo.mi_salud.misakudplus_medicos.R;

import java.util.ArrayList;
import java.util.List;

public class sedesAdapter extends BaseAdapter implements ListAdapter {
    private List<sedesDoctor> list = new ArrayList<>();
    private Context context;

    public sedesAdapter(List<sedesDoctor> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_sedes, null);
        }

        //Handle TextView and display string from your list
        TextView itemName = (TextView)view.findViewById(R.id.nomSede);
        TextView itemDir = (TextView)view.findViewById(R.id.dirSede);

        //listItemText.setText(list.get(position));
        itemName.setText(list.get(position).getNombre());
        itemDir.setText(list.get(position).getDireccion());

        return view;
    }

}
