package com.example.dontforgetbirthdayproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView gif_cake = (ImageView) findViewById(R.id.gift_gif_icon);
        Glide.with(this).load(R.drawable.gift_gif_icon).into(gif_cake); // gif
        Handler handler = new Handler();
        //자동로그인 구현을 위한 SharedPreferences. 앱 파일에 저장되는 데이터를 다룰 수 있는 인터페이스
        SharedPreferences auto = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
        String userID = auto.getString("userID", null);//
        String userPwd = auto.getString("userPwd", null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(userID!=null && userPwd!=null){
                    Toast.makeText(getApplicationContext(),"로그인 성공",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    intent.putExtra("userID",userID);
                    intent.putExtra("userPwd",userPwd);
                    startActivity(intent);
                    finish();
                } else{
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },3000);
    }
}