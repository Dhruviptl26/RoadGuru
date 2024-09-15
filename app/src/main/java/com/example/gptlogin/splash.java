package com.example.gptlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {
    private static final int SPLASH_SCREEN_DURATION = 2000;
    ImageView img;
    @SuppressLint({"ResourceType", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        img = (ImageView)findViewById(R.id.imageView);
        Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadin);
        img.startAnimation(animZoomIn);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(splash.this, Home.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN_DURATION);
    }
}