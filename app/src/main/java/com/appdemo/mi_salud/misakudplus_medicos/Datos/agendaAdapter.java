package com.appdemo.mi_salud.misakudplus_medicos.Datos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.appdemo.mi_salud.misakudplus_medicos.R;

import java.util.ArrayList;
import java.util.List;

public class agendaAdapter extends BaseAdapter implements ListAdapter {

    private List<horarioDoctor> list = new ArrayList<>();
    private Context context;
    private int pos;



    public agendaAdapter(List<horarioDoctor> list, Context context) {
        this.list = list;
        this.context = context;
        this.pos=0;
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
        pos=position;
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_agenda, null);
        }

        //Handle TextView and display string from your list
        TextView itemHourI = (TextView)view.findViewById(R.id.hourI);
        TextView itemHourF = (TextView)view.findViewById(R.id.hourF);
        TextView itemTime = (TextView)view.findViewById(R.id.tiempoC);

        //listItemText.setText(list.get(position));
        String HI=String.valueOf(list.get(position).getHoraI())+":"+String.valueOf(list.get(position).getMinutoI());
        String HF=String.valueOf(list.get(position).getHoraF())+":"+String.valueOf(list.get(position).getMinutoF());
        String TC=view.getResources().getString(R.string.tiempoConsulta)+"\t"+String.valueOf(list.get(position).getTiempoXcita())+"\t"+view.getResources().getString(R.string.min);
        itemHourI.setText(HI);
        itemHourF.setText(HF);
        itemTime.setText(TC);

        //Handle buttons and add onClickListeners
        ImageButton btn_DeleteHorario = (ImageButton)view.findViewById(R.id.cancel);

        btn_DeleteHorario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                list.remove(pos);
                Toast.makeText(context,"Elemento Borrado, por favor actualizar",Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }


}
