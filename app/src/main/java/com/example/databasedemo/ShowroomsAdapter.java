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


public class ShowroomsAdapter extends RecyclerView.Adapter<ShowroomsAdapter.newrecycler1>  {
    private Context context;
    private ArrayList<Showrooms> showrooms;
    private  onRvItemSelected listener1;
    public ArrayList<Showrooms> getShowrooms() {
        return showrooms;
    }



    public ShowroomsAdapter(@NonNull Context context, ArrayList<Showrooms> showrooms, onRvItemSelected listener1) {
        this.context=context;
        this.showrooms=showrooms;
        this.listener1=listener1;

    }


    @NonNull
    @Override
    public ShowroomsAdapter.newrecycler1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.showroom_layout,parent,false);
        newrecycler1 newrecycler=new newrecycler1(view);
        return newrecycler;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowroomsAdapter.newrecycler1 holder, int position) {
        Showrooms showrooms1=showrooms.get(position);
        holder.textView5.setText(showrooms1.getShowroom());
        holder.imageView5.setImageResource(showrooms1.getImage());



    }

    @Override
    public int getItemCount() {
        return showrooms.size();
    }
    public class newrecycler1 extends RecyclerView.ViewHolder {
        TextView textView5;
        ImageView imageView5;

        int id;


        public newrecycler1(@NonNull View itemView) {
            super(itemView);
            textView5=itemView.findViewById(R.id.showroom_text);
            imageView5=itemView.findViewById(R.id.car_image_view);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener1.onItemClicked(id);

                }
            });

        }
    }
}
