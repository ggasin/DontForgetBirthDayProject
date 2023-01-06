package com.example.dontforgetbirthdayproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<GroupData> arrayList;

    public GroupAdapter(Context context,ArrayList<GroupData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_recycler_item,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tv_group.setText(arrayList.get(position).getGroup());
        holder.itemView.setTag(position);
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
    public GroupData getItem(int position){
        return arrayList.get(position);
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_group;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_group = (TextView)itemView.findViewById(R.id.group_recycler_group);
        }
    }
}

