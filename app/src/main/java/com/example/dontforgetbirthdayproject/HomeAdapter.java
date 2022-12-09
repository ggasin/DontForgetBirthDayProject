package com.example.dontforgetbirthdayproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.CustomViewHolder> {

    private ArrayList<MainData> arrayList;

    public HomeAdapter(ArrayList<MainData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.birth_recycler_item,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tv_group.setText(arrayList.get(position).getTv_group());
        holder.tv_name.setText(arrayList.get(position).getTv_name());
        holder.tv_so_birth.setText(arrayList.get(position).getTv_so_birth());
        holder.tv_lu_birth.setText(arrayList.get(position).getTv_lu_birth());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"sadasd",Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                remove(holder.getBindingAdapterPosition());
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position){
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_group;
        protected TextView tv_name;
        protected TextView tv_memo;
        protected TextView tv_so_birth;
        protected TextView tv_lu_birth;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_group = (TextView) itemView.findViewById(R.id.tv_group);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_so_birth = (TextView) itemView.findViewById(R.id.tv_so_birth);
            this.tv_lu_birth = (TextView) itemView.findViewById(R.id.tv_lu_birth);
            this.tv_memo = (TextView) itemView.findViewById(R.id.tv_memo);
        }
    }
}
