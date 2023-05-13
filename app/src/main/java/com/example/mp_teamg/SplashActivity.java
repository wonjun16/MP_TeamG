package com.example.mp_teamg;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        // 스플래시 화면이 보이는 동안의 작업을 수행

        // 스플래시 화면이 끝나면 다음 액티비티로 이동. -> 나중에 로그인 액티비티로 이동하도록 할 것
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
