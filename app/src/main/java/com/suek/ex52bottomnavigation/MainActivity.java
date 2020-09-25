package com.suek.ex52bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //1)
    BottomNavigationView bottomNavigationView;
    //3)
    FragmentManager fragmentManager;
    //4)
    Fragment[] fragments= new Fragment[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //3.1) 프레그먼트의 동적 추가/삭제/제거를 위해 관리자 객체 소환
        fragmentManager= getSupportFragmentManager();

        //4.1) 각 탭 화면 프래그먼트 객체 생성
        fragments[0]= new Tab1Fragment();
        fragments[1]= new Tab2Fragment();
        fragments[2]= new Tab3Fragment();
        fragments[3]= new Tab4Fragment();

        //6)
        FragmentTransaction tran= fragmentManager.beginTransaction();
        tran.add(R.id.container, fragments[0]);
        tran.commit();

        //2)
        bottomNavigationView= findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                //4.2) 프레그먼트 작업 트랜잭션 시작
                FragmentTransaction tran= fragmentManager.beginTransaction();
                Toast.makeText(MainActivity.this, "begin", Toast.LENGTH_SHORT).show();

                //2.1)
                switch (menuItem.getItemId()){
                    case R.id.menu_share:
                        tran.replace(R.id.container, fragments[0]);
                        break;

                    case R.id.menu_map:
                        tran.replace(R.id.container, fragments[1]);
                        break;

                    case R.id.menu_help:
                        tran.replace(R.id.container, fragments[2]);
                        break;

                    case R.id.menu_call:
                        tran.replace(R.id.container, fragments[3]);
                        break;
                }//switch

                //5) 트랜잭션 작업 완료 명령
                tran.commit();

                //return true 가 아니면 선택은 되지만 선택효과가 이동하지 않음
                //return true;

                return true;
            }
        });
    }
}
