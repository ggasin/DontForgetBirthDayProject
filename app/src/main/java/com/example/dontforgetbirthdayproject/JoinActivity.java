package com.example.dontforgetbirthdayproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {

    private Button joinCompleteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        joinCompleteBtn = findViewById(R.id.join_join_btn);
        joinCompleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"가입 완료",Toast.LENGTH_SHORT);
                Intent intent = new Intent(JoinActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
