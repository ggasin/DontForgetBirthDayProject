package com.example.dontforgetbirthdayproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

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
        holder.iv_profile.setImageResource(arrayList.get(position).getIv_profile());
        holder.tv_group.setText(arrayList.get(position).getTv_item_group());
        holder.tv_name.setText(arrayList.get(position).getTv_item_name());
        holder.tv_so_birth.setText(arrayList.get(position).getTv_item_solar_birth());
        holder.tv_lu_birth.setText(arrayList.get(position).getTv_item_lunar_birth());
        holder.tv_memo.setText(arrayList.get(position).getTv_item_memo());
        holder.tv_so_dday.setText(arrayList.get(position).getTv_item_so_dday());
        holder.tv_lu_dday.setText(arrayList.get(position).getTv_item_lu_dday());

        holder.sw_alarm_on.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){

                }else{

                }
            }
        });

        if(arrayList.get(position).getItem_alarm_on()==0){
            holder.sw_alarm_on.setChecked(false);
        } else {
            holder.sw_alarm_on.setChecked(true);
        }
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
        protected ImageView iv_profile;
        protected TextView tv_group,tv_name,tv_memo,tv_so_birth,tv_lu_birth,tv_so_dday,tv_lu_dday;
        protected Switch sw_alarm_on;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = (ImageView) itemView.findViewById(R.id.recycler_item_profile_iv);
            this.tv_group = (TextView) itemView.findViewById(R.id.recycler_item_group_tv);
            this.tv_name = (TextView) itemView.findViewById(R.id.recycler_item_name_tv);
            this.tv_so_birth = (TextView) itemView.findViewById(R.id.recycler_item_so_birth_tv);
            this.tv_lu_birth = (TextView) itemView.findViewById(R.id.recycler_item_lu_birth_tv);
            this.tv_memo = (TextView) itemView.findViewById(R.id.recycler_item_memo_tv);
            this.sw_alarm_on = (Switch) itemView.findViewById(R.id.sw_alarm_btn);
            this.tv_so_dday = (TextView) itemView.findViewById(R.id.recycler_item_so_dday_tv);
            this.tv_lu_dday = (TextView) itemView.findViewById(R.id.recycler_item_lu_dday_tv);

            // ????????? ?????? ????????? ??????.
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
