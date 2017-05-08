package com.appdemo.mi_salud.misakudplus_medicos.Datos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import com.appdemo.mi_salud.misakudplus_medicos.R;


import java.util.ArrayList;
import java.util.List;

public class checkboxAdapter extends ArrayAdapter<checkboxDinamico>{

    private ArrayList<checkboxDinamico> cbList;
    private LayoutInflater layoutInflater;

    public checkboxAdapter(Context context, List<checkboxDinamico> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        this.cbList = new ArrayList<>();
        this.cbList.addAll(objects);
    }

    private class ViewHolder {
        CheckBox cbItem;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // holder pattern
        ViewHolder holder;
        Log.v("ConvertView", String.valueOf(position));
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = layoutInflater.inflate(R.layout.item_checkbox, null);
            holder.cbItem=(CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);

            holder.cbItem.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    int position = (Integer) cb.getTag();
                    getItem(position).setChecked(cb.isChecked());
                }
            });

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        checkboxDinamico row = getItem(position);
        assert row != null;
        //holder.nameItem.setText(row.getTexto());
        holder.cbItem.setTag(position);
        holder.cbItem.setText(row.getTexto());
        holder.cbItem.setChecked(row.isChecked());
        //holder.getCheckBox().setOnClickListener(this);
        return convertView;
    }

    public ArrayList<checkboxDinamico> getCbList() {
        return cbList;
    }

    /*public void setCbList(ArrayList<checkboxDinamico> cbList) {
        this.cbList = cbList;
    }*/
}
