package com.example.testtest;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends FirebaseRecyclerAdapter<User,CustomAdapter.myviewholder>
{
    public CustomAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull User model) {
        holder.id.setText(model.getId());
        holder.adress.setText(model.getStore());
        holder.floor.setText(model.getStname());
        holder.stname.setText(model.getAdress());
        holder.store.setText(model.getFloor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, myMovieDataList.getMovieName(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        //CircleImageView img;
        TextView id,adress,floor,stname,store;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            id=(TextView)itemView.findViewById(R.id.tv_id);
            adress=(TextView)itemView.findViewById(R.id.tv_adress);
            floor=(TextView)itemView.findViewById(R.id.tv_floor);
            stname=(TextView)itemView.findViewById(R.id.tv_stname);
            store=(TextView)itemView.findViewById(R.id.tv_store);
        }
    }
}
