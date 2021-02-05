package com.example.testtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> implements Filterable {

    private ArrayList<User>arrayList;
    private Context context;

    private ArrayList<User> ListFull;
    //private List<ExampleItem> exampleList; //필터
    private List<ExampleItem> exampleListFull; //필터

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

    //필터
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<User> filterList = new ArrayList<>();

            if(constraint == null || constraint.length()==0) {
                filterList.addAll(ListFull);
            }else{
                String filterpattern = constraint.toString().toLowerCase().trim();

                for (User item : ListFull){
                    if(item.getStname().toLowerCase().contains(filterpattern)){
                        filterList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayList.clear();
            arrayList.addAll((List) results.values);
            notifyDataSetChanged();
        }

    };
//필터 여기까지
}
