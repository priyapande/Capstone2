package com.example.anjali.eco2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash_screen extends Activity {
    private static int SPLASH_TIME_OUT=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent=new Intent(splash_screen.this,MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

}
