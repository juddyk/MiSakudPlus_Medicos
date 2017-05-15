package com.appdemo.mi_salud.misakudplus_medicos.UI_otras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appdemo.mi_salud.misakudplus_medicos.R;

public class agenda extends AppCompatActivity {

    RelativeLayout rlD,rlL,rlM,rlW,rlJ,rlV,rlS;
    String[] dias;
    ViewGroup.LayoutParams paramsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        setTitle(getResources().getString(R.string.app_agenda));

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        rlD=(RelativeLayout) findViewById(R.id.vistaD);
        rlL=(RelativeLayout) findViewById(R.id.vistaL);
        rlM=(RelativeLayout) findViewById(R.id.vistaM);
        rlW=(RelativeLayout) findViewById(R.id.vistaW);
        rlJ=(RelativeLayout) findViewById(R.id.vistaJ);
        rlV=(RelativeLayout) findViewById(R.id.vistaV);
        rlS=(RelativeLayout) findViewById(R.id.vistaS);
        dias=getResources().getStringArray(R.array.diasSemana);
        paramsTV = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void onClicDomingo(View view){
        rlD.setVisibility(View.VISIBLE);
        rlL.setVisibility(View.GONE);
        rlM.setVisibility(View.GONE);
        rlW.setVisibility(View.GONE);
        rlJ.setVisibility(View.GONE);
        rlV.setVisibility(View.GONE);
        rlS.setVisibility(View.GONE);
/*

Button myButton = new Button(this);
                 myButton.setText("Add Me");

                 LinearLayout ll = (LinearLayout)findViewById(R.id.buttonlayout);
                 LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                 ll.addView(myButton, lp);

*/


    }
    public void onClicLunes(View view){
        rlL.setVisibility(View.VISIBLE);
        rlD.setVisibility(View.GONE);
        rlM.setVisibility(View.GONE);
        rlW.setVisibility(View.GONE);
        rlJ.setVisibility(View.GONE);
        rlV.setVisibility(View.GONE);
        rlS.setVisibility(View.GONE);

    }
    public void onClicMartes(View view){
        rlM.setVisibility(View.VISIBLE);
        rlL.setVisibility(View.GONE);
        rlD.setVisibility(View.GONE);
        rlW.setVisibility(View.GONE);
        rlJ.setVisibility(View.GONE);
        rlV.setVisibility(View.GONE);
        rlS.setVisibility(View.GONE);
    }
    public void onClicMiercoles(View view){
        rlW.setVisibility(View.VISIBLE);
        rlL.setVisibility(View.GONE);
        rlM.setVisibility(View.GONE);
        rlD.setVisibility(View.GONE);
        rlJ.setVisibility(View.GONE);
        rlV.setVisibility(View.GONE);
        rlS.setVisibility(View.GONE);

    }
    public void onClicJueves(View view){
        rlJ.setVisibility(View.VISIBLE);
        rlL.setVisibility(View.GONE);
        rlM.setVisibility(View.GONE);
        rlW.setVisibility(View.GONE);
        rlD.setVisibility(View.GONE);
        rlV.setVisibility(View.GONE);
        rlS.setVisibility(View.GONE);
    }
    public void onClicViernes(View view){
        rlV.setVisibility(View.VISIBLE);
        rlL.setVisibility(View.GONE);
        rlM.setVisibility(View.GONE);
        rlW.setVisibility(View.GONE);
        rlJ.setVisibility(View.GONE);
        rlD.setVisibility(View.GONE);
        rlS.setVisibility(View.GONE);


    }
    public void onClicSabado(View view){
        rlS.setVisibility(View.VISIBLE);
        rlL.setVisibility(View.GONE);
        rlM.setVisibility(View.GONE);
        rlW.setVisibility(View.GONE);
        rlJ.setVisibility(View.GONE);
        rlV.setVisibility(View.GONE);
        rlD.setVisibility(View.GONE);
    }


}
