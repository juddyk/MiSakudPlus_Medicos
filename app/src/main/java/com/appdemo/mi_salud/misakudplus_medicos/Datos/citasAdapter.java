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

public class citasAdapter extends BaseAdapter implements ListAdapter {

    private List<datosCita> list = new ArrayList<>();
    private Context context;
    int pos;

    private static final String TAG = "logueo";
    private static final String TAG_pacientes = "Pacientes";
    private static final String TAG_nombre1 = "nombre1";
    private static final String TAG_apellido1 = "apellido1";

    public citasAdapter(List<datosCita> list, Context context) {
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

    public View getView(final int position, final View convertView, ViewGroup parent) {
        pos=position;
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_citas, null);
        }

        TextView tv_nombre=(TextView) view.findViewById(R.id.nombre);
        TextView tv_fecha=(TextView) view.findViewById(R.id.fecha);
        TextView tv_hora=(TextView) view.findViewById(R.id.hour);
        TextView tv_estado=(TextView) view.findViewById(R.id.estadoCita);

        String date=list.get(position).getFechaDia()+"/"+list.get(pos).getFechaMes()+"/"+list.get(pos).getFechaAnio();
        String hour=correcTime(list.get(pos).getHora())+":"+correcTime(list.get(pos).getMinuto());
        tv_fecha.setText(date);
        tv_hora.setText(hour);
        tv_nombre.setText(list.get(pos).getNombrePaciente());
        tv_estado.setText(list.get(pos).getEstado());
        return view;
    }

    public static String correcTime(String t){
        if(Integer.parseInt(t)<10){
            return "0"+t;
        }else{
            return t;
        }
    }


}
