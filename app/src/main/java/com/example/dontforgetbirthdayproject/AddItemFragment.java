package com.example.dontforgetbirthdayproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;


public class AddItemFragment extends Fragment {
    MainActivity mainActivity;
    private Button add_complete_btn,add_close_btn;
    private EditText add_name_et,add_group_et,add_solar_birth_et,add_memo_et;
    private TextView textView;
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_add_item, container, false);
        add_complete_btn = rootView.findViewById(R.id.add_complete_btn);
        add_close_btn = rootView.findViewById(R.id.add_close_btn);
        add_name_et = rootView.findViewById(R.id.add_name_et);
        add_group_et = rootView.findViewById(R.id.add_group_et);
        add_solar_birth_et = rootView.findViewById(R.id.add_solar_birth_et);
        add_memo_et = rootView.findViewById(R.id.add_memo_et);
        //완료 버튼 이벤트
        add_complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mainActivity.userId.toString();
                String name = add_name_et.getText().toString();
                String group = add_group_et.getText().toString();
                String solarBirth = add_solar_birth_et.getText().toString();
                String memo = add_memo_et.getText().toString() ;
                String lunarBirth = "198893";

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(!name.equals("") &&!group.equals("") && !solarBirth.equals("") && !memo.equals("")){
                                if(success){
                                    Toast.makeText(getContext(),"추가 완료",Toast.LENGTH_SHORT).show();
                                    mainActivity.onFragmentChange(0);

                                } else{
                                    Toast.makeText(getContext(),"추가를 실패했습니다.",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } else {
                                Toast.makeText(getContext(),"모든 항목에 입력값을 넣었는지 확인해주세요.",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(getContext(),"오류",Toast.LENGTH_SHORT).show();
                            StringWriter sw = new StringWriter();

                            e.printStackTrace(new PrintWriter(sw));

                            String exceptionAsStrting = sw.toString();

                            Log.e("오류", exceptionAsStrting);
                            e.printStackTrace();
                        }
                    }
                };
                ItemAddRequest itemAddRequest = new ItemAddRequest(id,name,group,solarBirth,lunarBirth,memo,1,responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                queue.add(itemAddRequest);

            }
        });
        //닫기 버튼 이벤트
        add_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onFragmentChange(0);
            }
        });
        return rootView;
    }
}