package com.example.dontforgetbirthdayproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.CustomViewHolder> implements OnItemClickListener {


    private Context context;
    private ArrayList<ItemData> arrayList;
    OnItemClickListener listener;
    public HomeAdapter(Context context,ArrayList<ItemData> arrayList) {
        this.context = context;
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
        holder.tv_group.setText(arrayList.get(position).getTv_item_group());
        holder.tv_name.setText(arrayList.get(position).getTv_item_name());
        holder.tv_so_birth.setText(arrayList.get(position).getTv_item_solar_birth());
        holder.tv_lu_birth.setText(arrayList.get(position).getTv_item_lunar_birth());
        holder.tv_memo.setText(arrayList.get(position).getTv_item_memo());
        if(arrayList.get(position).getItem_alram_on()==0){
            holder.sw_alram_on.setChecked(false);
        } else {
            holder.sw_alram_on.setChecked(true);
        }
        holder.itemView.setTag(position);

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

    public void setOnItemClicklistener(OnItemClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onItemClick(CustomViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }
    public ItemData getItem(int position){
        return arrayList.get(position);
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_group;
        protected TextView tv_name;
        protected TextView tv_memo;
        protected TextView tv_so_birth;
        protected TextView tv_lu_birth;
        protected Switch sw_alram_on;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_group = (TextView) itemView.findViewById(R.id.tv_group);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_so_birth = (TextView) itemView.findViewById(R.id.tv_so_birth);
            this.tv_lu_birth = (TextView) itemView.findViewById(R.id.tv_lu_birth);
            this.tv_memo = (TextView) itemView.findViewById(R.id.tv_memo);
            this.sw_alram_on = (Switch) itemView.findViewById(R.id.sw_alram_btn);

            // 아이템 클릭 이벤트 처리.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition() ;
                    if(listener != null){
                        listener.onItemClick(CustomViewHolder.this, v, position);
                    }
                }
            });

        }
    }
}
