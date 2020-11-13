package com.example.databasedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MaintenanceAdapter extends RecyclerView.Adapter<MaintenanceAdapter.newrecycler2> {
    private Context context;
    private ArrayList<MaintenanceCenters> maintenanceCenters;
    public ArrayList<MaintenanceCenters> getMaintenanceCenters() {
        return maintenanceCenters;
    }

    public MaintenanceAdapter(Context context, ArrayList<MaintenanceCenters> maintenanceCenters) {
        this.context = context;
        this.maintenanceCenters = maintenanceCenters;
    }



    @NonNull
    @Override
    public MaintenanceAdapter.newrecycler2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.maintenance_layout,parent,false);
        newrecycler2 newrecycler=new newrecycler2(view);
        return newrecycler;

    }

    @Override
    public void onBindViewHolder(@NonNull MaintenanceAdapter.newrecycler2 holder, int position) {
        MaintenanceCenters maintenanceCenters1=maintenanceCenters.get(position);
        holder.textview_name.setText(maintenanceCenters1.getMaintenance_center());
        holder.textview_address.setText(maintenanceCenters1.getMaintenance_center_address());
        holder.textview_phone.setText(maintenanceCenters1.getMaintenance_center_phone()+"");
        holder.imageview_maintenance.setImageResource(maintenanceCenters1.getMaintenance_center_image());

    }

    @Override
    public int getItemCount() {
        return maintenanceCenters.size();
    }
    public class newrecycler2 extends RecyclerView.ViewHolder {
        TextView textview_name,textview_phone,textview_address;

        ImageView imageview_maintenance;

        int id;


        public newrecycler2(@NonNull View itemView) {
            super(itemView);
            textview_name=itemView.findViewById(R.id.maintenance_name_text);
            textview_address=itemView.findViewById(R.id.maintenance_address);
            textview_phone=itemView.findViewById(R.id.maintenance_phone);
            imageview_maintenance=itemView.findViewById(R.id.car_image_view);


        }
    }


}
