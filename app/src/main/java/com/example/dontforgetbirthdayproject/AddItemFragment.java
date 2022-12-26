package com.example.dontforgetbirthdayproject;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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
import java.time.LocalDate;

//아이템 추가 화면 fragment
public class AddItemFragment extends Fragment {
    MainActivity mainActivity;
    private Button add_complete_btn,add_close_btn;
    private EditText add_name_et,add_solar_birth_et,add_memo_et;
    private TextView add_group_t;
    private CheckBox add_lunar_chk;
    String selectedGroup;
    boolean isValidBirth = false; //유효한 생년월일인지 파악
    //onAttach 는 fragment가 activity에 올라온 순간
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
        selectedGroup = mainActivity.selectedGroup;
        Log.d("selectedGroup on Add",selectedGroup);
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
        add_group_t = rootView.findViewById(R.id.add_group_t);
        add_solar_birth_et = rootView.findViewById(R.id.add_solar_birth_et);
        add_memo_et = rootView.findViewById(R.id.add_memo_et);
        add_lunar_chk = rootView.findViewById(R.id.add_lunar_check_box);

        //선택된 그룹 이름으로 group text 초기화
        add_group_t.setText(selectedGroup);
        Log.d("selectedGroup on Add1",selectedGroup);


        //완료 버튼 이벤트
        add_complete_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                LocalDate now = LocalDate.now(); //현재 날짜 가져오기
                String id = mainActivity.userId;
                String name = add_name_et.getText().toString();
                String group = add_group_t.getText().toString();
                String solarBirth = add_solar_birth_et.getText().toString();
                String memo = add_memo_et.getText().toString() ;
                String lunarBirth;

                boolean isLunarChecked = add_lunar_chk.isChecked(); //음력 체크 유무

                if(isLunarChecked){
                    lunarBirth = "19981208"; //음력 미구현
                } else {
                    lunarBirth = "--";
                }
                if(solarBirth.length()==8){
                    int solarBirthYear = Integer.parseInt(solarBirth.substring(0,4)); //생년 인트형 변환
                    //유효한 생년월일인지 체크
                    if(solarBirthYear>1900 && solarBirthYear<=now.getYear()){
                        isValidBirth = true;
                    }
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(!name.equals("") &&!group.equals("") && !solarBirth.equals("") && isValidBirth){ //공백이 없고 생년월일이 유효하면
                                if(success){
                                    Toast.makeText(getContext(),"추가 완료",Toast.LENGTH_SHORT).show();
                                    mainActivity.onFragmentChange(0);

                                } else{
                                    Toast.makeText(getContext(),"이름이 중복되지 않도록 기입해주세요.",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } else {
                                if(!isValidBirth){
                                    Toast.makeText(getContext(),"유효한 생년월일인지 확인해주세요.",Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(),"모든 항목에 입력값을 넣었는지 확인해주세요.",Toast.LENGTH_SHORT).show();
                                }
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
                ItemAddRequest itemAddRequest = new ItemAddRequest(id,group,name,solarBirth,lunarBirth,memo,1,responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                queue.add(itemAddRequest);

            }
        });
        //닫기 버튼 이벤트
        add_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_name_et.setText("");
                add_solar_birth_et.setText("");
                add_memo_et.setText("");
                mainActivity.onFragmentChange(0);
            }
        });
        return rootView;
    }
}