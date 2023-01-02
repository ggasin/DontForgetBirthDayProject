package com.example.dontforgetbirthdayproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MyPageFragement extends Fragment {
    MainActivity mainActivity;
    private TextView logoutTextBtn;
    private RadioGroup alarmRadioGroup;
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

    public MyPageFragement() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_my_page_fragement, container, false);
        logoutTextBtn = rootView.findViewById(R.id.mp_logout_text_btn);
        alarmRadioGroup = rootView.findViewById(R.id.mp_radio_group);

        alarmRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.mp_alarm_one_btn) {
                    mainActivity.isAlarmOne = true;
                }
                else if(checkedId == R.id.mp_alarm_three_btn) {
                    mainActivity.isAlarmThree = true;
                } else if(checkedId == R.id.mp_alarm_seven_btn) {
                    mainActivity.isAlarmSeven = true;
                }
            }
        });
        //로그아웃 버튼 이벤트
        logoutTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                SharedPreferences auto = mainActivity.getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = auto.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getActivity().getApplicationContext(), "로그아웃", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
        return rootView;
    }
}