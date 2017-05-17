package com.appdemo.mi_salud.misakudplus_medicos.Datos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.appdemo.mi_salud.misakudplus_medicos.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class agendaAdapter extends BaseAdapter implements ListAdapter {

    private static final String TAG = "agenda";
    private static final String TAG_medicos = "Medicos";
    private static final String TAG_horario = "Horario";


    private List<horarioDoctor> list = new ArrayList<>();
    private Context context;
    private int pos;
    private String user;



    public agendaAdapter(List<horarioDoctor> list, Context context, String user) {
        this.list = list;
        this.context = context;
        this.pos=0;
        this.user=user;
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
/*
        //Handle buttons and add onClickListeners
        ImageButton btn_DeleteHorario = (ImageButton)view.findViewById(R.id.cancel);

        btn_DeleteHorario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Elemento Borrado, por favor actualizar",Toast.LENGTH_SHORT).show();
                DatabaseReference mDB= FirebaseDatabase.getInstance().getReferenceFromUrl("https://mi-salud-5965a.firebaseio.com/");
                mDB.child(TAG_medicos).child(user).child(TAG_horario).child(getDiaString(list.get(pos).getDia())).child(String.valueOf(pos)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        snapshot.getRef().removeValue();
                    }
                    @Override public void onCancelled(DatabaseError error) {
                        Log.w(TAG, "loadPost:onCancelled", error.toException());
                    }
                });
                list.remove(pos);
            }
        });

*/

        return view;
    }

    private String getDiaString(int dia){
        String out="";
        switch (dia){
            case 1:
                out="D";
                break;
            case 2:
                out="L";
                break;
            case 3:
                out="M";
                break;
            case 4:
                out="W";
                break;
            case 5:
                out="J";
                break;
            case 6:
                out="V";
                break;
            case 7:
                out="S";
                break;
        }
        return out;
    }

}
