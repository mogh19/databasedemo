package com.example.databasedemo;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.newrecycler> {
    private Context context;
    private ArrayList<Car> cars;
    private  onRvItemSelected listener;

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public RecyclerViewAdapter(@NonNull Context context, ArrayList<Car> cars, onRvItemSelected listener) {
        this.context=context;
        this.cars=cars;
        this.listener=listener;

    }

    @NonNull
    @Override
    public RecyclerViewAdapter.newrecycler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.car_layout_view,parent,false);
        newrecycler newrecycler=new newrecycler(view);
        return newrecycler;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.newrecycler holder, int position) {
        Car car=cars.get(position);
        if(car.getImage()!=null &&car.getImage().isEmpty()){
            holder.imageView.setImageURI(Uri.parse(car.getImage()));
        }
        holder.textView.setText(car.getModel());
        holder.textView1.setText(car.getColor());
        holder.textView2.setText(car.getDpl()+"");

        holder.id=car.getId();

    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class newrecycler extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView1;
        TextView textView2;
        ImageView imageView;
        int id;


        public newrecycler(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.car_text_modle);
            textView1=itemView.findViewById(R.id.car_text_color);
            textView2=itemView.findViewById(R.id.showroom_text);
            imageView=itemView.findViewById(R.id.car_image_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(id);

                }
            });

        }
    }
}