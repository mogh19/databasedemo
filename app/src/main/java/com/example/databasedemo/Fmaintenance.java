package com.example.databasedemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Fmaintenance extends Fragment {
    RecyclerView recyclerview;
    MaintenanceAdapter Adapter;
    Toolbar toolbar;
    RecyclerView.LayoutManager layoutManager;
    View listitems;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<MaintenanceCenters> maintenancecenters=new ArrayList<MaintenanceCenters>();
        //maintenancecenters.add(new MaintenanceCenters("",,"",));
        maintenancecenters.add(new MaintenanceCenters("4 cylinder", 45879,"Address: Qaryah 2/8 Abis - 10 minutes From carrefour - Alexandria (105.31 km)\n" +
               "Alexandria, Alexandria Governorate, Egypt",R.drawable.fourcylinderservice));
        maintenancecenters.add(new MaintenanceCenters("elsaffa service", 56491,"Address: Suez Bridge, 52 Mukhtar Abaza, Barakat Al-Nasr," +
                " Awal Al-Salam, Cairo Governorate 11511"
                ,R.drawable.elsaffaservice));
        maintenancecenters.add(new MaintenanceCenters("el saba",64429,"Address: Mahalla Al-Kobra Road - Mansoura, Meet Nabit, " +
                "Talkha, Dakahlia"
                ,R.drawable.elsabaservice));
        maintenancecenters.add(new MaintenanceCenters("elmohnds",011444
                ,"Address: Tanta, Gharbia Governorate, Egypt",R.drawable.elmohndsservice));
        maintenancecenters.add(new MaintenanceCenters("elmostakbl",011472,"Address: Eastern Tazmant Road in front of the end of the wall of the Mohi El-Din parking lot\n" +
                "Bani Sweif",R.drawable.elmostakblservice));
        maintenancecenters.add(new MaintenanceCenters("auto group",011021,"Address: Cat 9, S.2, Industrial Zone," +
                " First Road, Ain Sokhna Road, Cairo Governorate\n",R.drawable.autogruopservice));
        maintenancecenters.add(new MaintenanceCenters("diagno fast",012312 ,"Address: Mansoura Ring Road after El Sallab Academy in front of Caltex Gas Station" +
                ", Ring Road, Dakahlia\n",R.drawable.diagnofastservice));
        maintenancecenters.add(new MaintenanceCenters("akel center",010550,"Address: Tanta - Al Mahalla Al Kobra Road" +
                ", Saft Terab, Mahalla Al Kubra, Al Gharbia\n",R.drawable.akl));





        listitems= inflater.inflate(R.layout.fragment_fmaintenance, container, false);
        recyclerview=listitems.findViewById(R.id.maintenance_recyclerview);
        layoutManager=new GridLayoutManager(getContext(),1);
        recyclerview.setLayoutManager( layoutManager);
        Adapter =new MaintenanceAdapter(getContext(),maintenancecenters);






        recyclerview.setAdapter(Adapter);
        Adapter.notifyDataSetChanged();

        return listitems;
    }
}