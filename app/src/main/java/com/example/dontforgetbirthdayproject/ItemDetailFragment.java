package com.example.dontforgetbirthdayproject;

import android.content.Context;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;


public class ItemDetailFragment extends Fragment {

    MainActivity mainActivity;
    private TextView itemDetailName,itemDetailSolar,itemDetailLunar,itemDetailGroup;
    private EditText itemDetailMemo;
    private ImageButton itemDetailCloseBtn;
    private LinearLayout itemDetailMemoLy;
    //onAttach 는 fragment가 activity에 올라온 순간
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }
    public ItemDetailFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private ImageView profile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_item_detail, container, false);
        profile = rootView.findViewById(R.id.item_detail_profile_iv);
        itemDetailName = rootView.findViewById(R.id.item_detail_name_tv);
        itemDetailSolar = rootView.findViewById(R.id.item_detail_solar_tv);
        itemDetailLunar = rootView.findViewById(R.id.item_detail_lunar_tv);
        itemDetailMemo = rootView.findViewById(R.id.item_detail_memo_et);
        itemDetailGroup = rootView.findViewById(R.id.item_detail_group_tv);
        itemDetailCloseBtn = rootView.findViewById(R.id.item_detail_close_btn);
        itemDetailMemoLy = rootView.findViewById(R.id.item_detail_memo_ly);

        itemDetailName.setText(mainActivity.itemName);
        itemDetailGroup.setText(mainActivity.itemGroup);
        itemDetailSolar.setText(mainActivity.itemSolarBirth);
        itemDetailLunar.setText(mainActivity.itemlunarBirth);
        itemDetailMemo = rootView.findViewById(R.id.item_detail_memo_et);

        itemDetailMemoLy.setBackgroundResource(R.drawable.memo_border);//배경색 설정
        itemDetailMemo.setEnabled(false);

        //닫기 버튼
        itemDetailCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onFragmentChange(0);
            }
        });

        //프로필 모서리 둥글게 하려면 setClipToOutline true로 둬야함
        profile.setClipToOutline(true);
        return rootView;
    }
    @Override
    public void onStart(){
        super.onStart();
        //popBackStack으로 B에서 이전 프래그먼트 A로 돌아오면서
        // 현재 UI Thread가 A로 바뀌지 않은 상태에서 setText를 해서 안먹음.
        //이전 프래그먼트로 복귀할 때는 onCreate부터 플로우를 타는 것이 아니기 때문에 해당 메소드를 오버라이드하여 setText()를 호출
        itemDetailMemo.setText(mainActivity.itemMemo);
    }
}