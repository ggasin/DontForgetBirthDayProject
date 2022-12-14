package com.example.dontforgetbirthdayproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //프래그먼트 Transaction 획득
        //프래그먼트를 올리거나 교체하는 작업을 Transaction이라고 합니다.
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new ItemSelectedListener());
        bottomNavigationView.setSelectedItemId(R.id.home_menu);


    }
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.setting_menu:
                    Toast.makeText(getApplicationContext(),"미구현",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.home_menu:
                    transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.mypage_menu:
                    transaction.replace(R.id.frameLayout, fragmentMyPage).commitAllowingStateLoss();
                    break;

            }

            return true;
        }
    }

    public void onFragmentChange(int index){
        if(index==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentHome).commit();
        }
        else if(index==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentAddItem).commit();
        }
    }
}