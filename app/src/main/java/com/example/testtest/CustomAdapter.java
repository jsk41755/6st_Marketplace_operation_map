package com.example.testtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<User>arrayList;
    private Context context;


    public CustomAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tv_id.setText(arrayList.get(position).getId());
        holder.tv_stname.setText(arrayList.get(position).getStname());
        holder.tv_adress.setText(arrayList.get(position).getAdress());
        holder.tv_floor.setText(arrayList.get(position).getFloor());
        holder.tv_store.setText(arrayList.get(position).getStore());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id; // 건물 번호
        TextView tv_stname; //상호
        TextView tv_adress; //주소
        TextView tv_floor; //층수
        TextView tv_store; //업종


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_stname = itemView.findViewById(R.id.tv_stname);
            this.tv_adress= itemView.findViewById(R.id.tv_adress);
            this.tv_floor = itemView.findViewById(R.id.tv_floor);
            this.tv_store = itemView.findViewById(R.id.tv_store);
        }
    }
}
