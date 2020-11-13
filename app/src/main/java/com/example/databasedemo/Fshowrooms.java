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


public class Fshowrooms extends Fragment {
    RecyclerView recyclerview;
    ShowroomsAdapter showroomsAdapter;
    Toolbar toolbar;
    RecyclerView.LayoutManager layoutManager;
    View listitems;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ArrayList<Showrooms> showrooms=new ArrayList<Showrooms>();
        showrooms.add(new Showrooms("essam hosny",R.drawable.essamhosny));
        showrooms.add(new Showrooms("majestic",R.drawable.majestic));
        showrooms.add(new Showrooms("prime automotive",R.drawable.images));
        showrooms.add(new Showrooms("motor one",R.drawable.motorone));
        showrooms.add(new Showrooms("U2auto",R.drawable.u2auto));
        showrooms.add(new Showrooms("auto for car trading",R.drawable.autoforcartrading));
        showrooms.add(new Showrooms("alqersh cars",R.drawable.alqershcar));
        showrooms.add(new Showrooms("alqds motors",R.drawable.alqdsmotor));
        showrooms.add(new Showrooms("kassar motors",R.drawable.kassarmotors));
        showrooms.add(new Showrooms("auto samir mahran",R.drawable.autosamirmahran));
        showrooms.add(new Showrooms("alfatima",R.drawable.alfatima));
        showrooms.add(new Showrooms("alfa motors",R.drawable.alfamotor));
        showrooms.add(new Showrooms("original motors",R.drawable.originalmotors));
        showrooms.add(new Showrooms("ELfalla7",R.drawable.elfalla7));






        listitems= inflater.inflate(R.layout.fragment_showrooms, container, false);
        recyclerview=listitems.findViewById(R.id.showrooms_recyclerview);
        layoutManager=new GridLayoutManager(getContext(),1);
        recyclerview.setLayoutManager( layoutManager);
        showroomsAdapter =new ShowroomsAdapter(getContext(),showrooms, new onRvItemSelected() {
            @Override
            public void onItemClicked(int carId) {
                Intent intent=new Intent(getContext(),MainActivity3.class);
                startActivity(intent);



            }
        });
        recyclerview.setAdapter(showroomsAdapter);
        showroomsAdapter.notifyDataSetChanged();

        return listitems;
    }
}