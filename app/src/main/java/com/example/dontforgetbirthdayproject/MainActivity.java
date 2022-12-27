package com.example.dontforgetbirthdayproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {
    // 뒤로가기 이벤트 핸들러 변수
    private final BackKeyHandler backKeyHandler = new BackKeyHandler(this);
    //뒤로가기 두번 누르면 종료
    @Override
    public void onBackPressed() {
        backKeyHandler.onBackPressed("'뒤로' 버튼을 두 번 누르면 종료됩니다.");
    }
    //프래그먼트 매니저 획득
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private HomeFragment fragmentHome = new HomeFragment();
    private MyPageFragement fragmentMyPage = new MyPageFragement();
    private AddItemFragment fragmentAddItem = new AddItemFragment();
    private ItemDetailFragment fragmentItemDetail = new ItemDetailFragment();
    private RadioButton mpAlramOne,mpAlramThree,mpAlramSeven;
    String userId,selectedGroup,itemName,itemSolarBirth,itemlunarBirth,itemMemo,itemGroup;
    boolean isAlramOne,isAlramThree,isAlramSeven;
    //현재 시간,분 변수선언
    int currHour, currMinute;
    //시스템에서 알람 서비스를 제공하도록 도와주는 클래스
    //특정 시점에 알람이 울리도록 도와준다
    private AlarmManager alarmManager;
    @RequiresApi(api = Build.VERSION_CODES.O) //이 코드는 애너테이션으로 아래 함수를 사용할 때 필요한 최소 API를 명시된 API 레벨보다 낮으면 오류가 발생하는 역할을 한다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        userId=intent.getStringExtra("userId");
        //프래그먼트 Transaction 획득
        //프래그먼트를 올리거나 교체하는 작업을 Transaction이라고 합니다.
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        transaction.replace(R.id.frameLayout, fragmentHome).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new ItemSelectedListener());
        bottomNavigationView.setSelectedItemId(R.id.home_menu);

        mpAlramOne = findViewById(R.id.mp_alram_one_btn);
        mpAlramThree = findViewById(R.id.mp_alram_three_btn);
        mpAlramSeven = findViewById(R.id.mp_alram_seven_btn);



        //현재 시간기준으로 몇시 몇분인지 구하기
        LocalTime now = LocalTime.now();
        currHour = now.getHour();
        currMinute = now.getMinute();
        //푸시알림을 보내기 위해, 시스템에서 알림 서비스를 생성해주는 코드
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);



    }
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.home_menu:
                    transaction.replace(R.id.frameLayout, fragmentHome).commit();
                    break;
                case R.id.mypage_menu:
                    transaction.replace(R.id.frameLayout, fragmentMyPage).commitAllowingStateLoss();
                    break;

            }

            return true;
        }
    }

    public void onFragmentChange(int index){
        if(index==0){ //홈프레그먼트로 이동
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentHome).commit();
        }
        else if(index==1){ //아이템 추가 프레그먼트로 이동
            Log.d("selectedGroup on Main",selectedGroup);
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentAddItem).commit();
        } else if(index==2){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentItemDetail).commit();
        }
    }
}