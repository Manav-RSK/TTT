package com.example.tictactoe_minimax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(). postDelayed(() -> {
            Intent iHome = new Intent( SplashActivity.this, MainActivity.class);
            startActivity (iHome);
            finish();
        }, 2000);
    }
}