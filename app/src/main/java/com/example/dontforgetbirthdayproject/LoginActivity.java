package com.example.dontforgetbirthdayproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private TextView goJoinBtn;
    private EditText et_id,et_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.login_btn);
        goJoinBtn = findViewById(R.id.go_join_text_btn);
        et_id = findViewById(R.id.login_edit_id);
        et_pwd = findViewById(R.id.login_edit_pwd);
        //로그인 버튼 이벤트
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_id.getText().toString();
                String userPwd = et_pwd.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(!et_id.getText().toString().equals("") && !et_pwd.getText().toString().equals("")){ //공백이 아니면
                                if(success){
                                    String UserID = jsonObject.getString("userID");
                                    String UserPwd = jsonObject.getString("userPassword");
                                    Toast.makeText(getApplicationContext(),"로그인 성공",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    intent.putExtra("userID",userID);
                                    intent.putExtra("userPwd",userPwd);
                                    startActivity(intent);

                                } else{
                                    Toast.makeText(getApplicationContext(),"로그인 실패",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } else{
                                Toast.makeText(getApplicationContext(),"로그인 실패",Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID,userPwd,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);


            }
        });

        //회원가입 버튼
        goJoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
