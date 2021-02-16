package com.example.testtest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends FirebaseRecyclerAdapter<User, CustomAdapter.myviewholder>
{
    public CustomAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }


    public interface OnPersonItemClickListener {               //클릭 이벤트를 어댑터 내에서가 아닌 다른 액티비티에서 처리하고 싶어서 인터페이스 만듦
        public void onItemClick(View view, int position);
    }

    private OnPersonItemClickListener mlistener;

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull User model) {  //User 클래스 파일과 연결시켜주는 함수
        holder.id.setText(model.getId());
        holder.address.setText(model.getAddress());
        holder.floor.setText(model.getFloor());
        holder.st_name.setText(model.getSt_name());
        holder.st_type.setText(model.getSt_type());

       // holder.itemView.setOnClickListener(new View.OnClickListener() {  //카드뷰 눌렀을 때, 우선 로그캣으로 확인 가능.
      //      @Override
       //     public void onClick(View v) {
                //Toast.makeText(context, myMovieDataList.getSt_name(), Toast.LENGTH_SHORT).show();
     //       }
     //   });

    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new myviewholder(view);
    }

    
    public void setOnItemclicklistener(OnPersonItemClickListener listener) {
        this.mlistener = listener;
    }


    class myviewholder extends RecyclerView.ViewHolder    //list_item.xml과 연동시켜주는 함수
    {
        //CircleImageView img;
        TextView id, address, floor, st_name, st_type;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.tv_id);
            address = (TextView) itemView.findViewById(R.id.tv_address);
            floor = (TextView) itemView.findViewById(R.id.tv_floor);
            st_name = (TextView) itemView.findViewById(R.id.tv_st_name);
            st_type = (TextView) itemView.findViewById(R.id.tv_st_type);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {                    //main 액티비티에 클릭 리스너를 하기 위해 여기에 선언. 이유는 토스트는
                    int position = getAdapterPosition();            //context가 필요하기도 하고, 작업하기도 편해질 듯 해서
                    if (position != RecyclerView.NO_POSITION ) {
                        mlistener.onItemClick(view, position);

                    }


                }
            });


        }


    }


}



