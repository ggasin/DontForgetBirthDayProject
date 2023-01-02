package com.example.dontforgetbirthdayproject;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


public class ItemDetailFragment extends Fragment {

    MainActivity mainActivity;
    private TextView itemDetailName,itemDetailSolar,itemDetailLunar,itemDetailGroup;
    private EditText itemDetailMemo,itemDetailEditSolar,itemDetailEditLunar,itemDetailEditName;
    private ImageButton itemDetailCloseBtn,itemDetailDeleteBtn,itemDetailAlterBtn;
    private LinearLayout itemDetailMemoLy;
    private Button itemDetailCompleteBtn,itemDetailCalcelBtn;
    private Spinner itemDetailGroupSpinner;
    private LinearLayout itemDetailBtnLy;
    private ImageView itemDetailProfile;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_item_detail, container, false);
        itemDetailProfile = rootView.findViewById(R.id.item_detail_profile_iv);
        itemDetailName = rootView.findViewById(R.id.item_detail_name_tv);
        itemDetailSolar = rootView.findViewById(R.id.item_detail_solar_tv);
        itemDetailLunar = rootView.findViewById(R.id.item_detail_lunar_tv);
        itemDetailMemo = rootView.findViewById(R.id.item_detail_memo_et);
        itemDetailGroup = rootView.findViewById(R.id.item_detail_group_tv);
        itemDetailCloseBtn = rootView.findViewById(R.id.item_detail_close_btn);
        itemDetailMemoLy = rootView.findViewById(R.id.item_detail_memo_ly);
        itemDetailAlterBtn = rootView.findViewById(R.id.item_detail_alter_btn);
        itemDetailDeleteBtn = rootView.findViewById(R.id.item_detail_delete_btn);
        itemDetailCompleteBtn = rootView.findViewById(R.id.item_detail_complete_btn);
        itemDetailCalcelBtn = rootView.findViewById(R.id.item_detail_cancel_btn);
        itemDetailEditName = rootView.findViewById(R.id.item_detail_edit_name);
        itemDetailEditSolar = rootView.findViewById(R.id.item_detail_edit_solar);
        itemDetailEditLunar = rootView.findViewById(R.id.item_detail_edit_lunar);
        itemDetailGroupSpinner = rootView.findViewById(R.id.item_detail_group_spinner);
        itemDetailBtnLy = rootView.findViewById(R.id.item_detail_btn_ly);
        itemDetailMemo = rootView.findViewById(R.id.item_detail_memo_et);

        //사용자 정보로 세팅
        itemDetailProfile.setImageResource(mainActivity.profile_id);
        itemDetailName.setText(mainActivity.itemName);
        itemDetailGroup.setText(mainActivity.itemGroup);
        itemDetailSolar.setText(mainActivity.itemSolarBirth);
        itemDetailLunar.setText(mainActivity.itemlunarBirth);


        itemDetailMemoLy.setBackgroundResource(R.drawable.memo_cant_edit_border);//배경색 설정
        itemDetailMemo.setEnabled(false);

        //삭제 버튼
        itemDetailDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //수정 버튼
        itemDetailAlterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemDetailName.setVisibility(View.GONE);
                itemDetailGroup.setVisibility(View.GONE);
                itemDetailSolar.setVisibility(View.GONE);
                itemDetailLunar.setVisibility(View.GONE);
                itemDetailEditName.setVisibility(View.VISIBLE);
                itemDetailEditSolar.setVisibility(View.VISIBLE);
                itemDetailEditLunar.setVisibility(View.VISIBLE);
                itemDetailGroupSpinner.setVisibility(View.VISIBLE);
                itemDetailBtnLy.setVisibility(View.VISIBLE);
                itemDetailMemoLy.setBackgroundResource(R.drawable.memo_can_edit_border);
                itemDetailMemo.setEnabled(true);

            }
        });
        //완료 버튼
        itemDetailCompleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemDetailName.setVisibility(View.GONE);
                itemDetailGroup.setVisibility(View.GONE);
                itemDetailSolar.setVisibility(View.GONE);
                itemDetailLunar.setVisibility(View.GONE);
                itemDetailEditName.setVisibility(View.VISIBLE);
                itemDetailEditSolar.setVisibility(View.VISIBLE);
                itemDetailEditLunar.setVisibility(View.VISIBLE);
                itemDetailGroupSpinner.setVisibility(View.VISIBLE);
                itemDetailBtnLy.setVisibility(View.VISIBLE);
                itemDetailMemoLy.setBackgroundResource(R.drawable.memo_cant_edit_border);//배경색 설정
                itemDetailMemo.setEnabled(false);
            }
        });
        //취소 버튼
        itemDetailCalcelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemDetailName.setVisibility(View.VISIBLE);
                itemDetailGroup.setVisibility(View.VISIBLE);
                itemDetailSolar.setVisibility(View.VISIBLE);
                itemDetailLunar.setVisibility(View.VISIBLE);
                itemDetailEditName.setVisibility(View.GONE);
                itemDetailEditSolar.setVisibility(View.GONE);
                itemDetailEditLunar.setVisibility(View.GONE);
                itemDetailGroupSpinner.setVisibility(View.GONE);
                itemDetailBtnLy.setVisibility(View.GONE);
                itemDetailMemoLy.setBackgroundResource(R.drawable.memo_cant_edit_border);
                itemDetailMemo.setText(mainActivity.itemMemo);
                itemDetailMemo.setEnabled(false);
            }
        });
        //닫기 버튼
        itemDetailCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onFragmentChange(0);
            }
        });


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