package com.appdemo.mi_salud.misakudplus_medicos.UI_registro;

import android.os.Bundle;
//import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appdemo.mi_salud.misakudplus_medicos.R;

public class tabsRegistro extends Fragment {

    //public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 10 ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.tab_registro,null);

        //tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

/*
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
*/

        return view;

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new registroI();
                case 1 : return new registroII();
                case 2 : return new registroIII();
                case 3 : return new registroIV();
                case 4 : return new registroV();
                case 5 : return new registroVI();
                case 6 : return new registroVII();
                case 7 : return new registroVIII();
                case 8 : return new registroIX();
                case 9 : return new registroX();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;
        }

/*
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return getResources().getString(R.string.reg_tab1);
                case 1 :
                    return getResources().getString(R.string.reg_tab2);
                case 2 :
                    return getResources().getString(R.string.reg_tab3);

            }
            return null;
        }*/

    }





}
