package com.example.gptlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {
    private static final int SPLASH_SCREEN_DURATION = 2000;
    ImageView im;
    @SuppressLint({"ResourceType", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_splash);
//            VideoView videoView=(VideoView)findViewById(R.id.vv);
//            MediaController mediaController=new MediaController(this);
//            mediaController.setAnchorView(videoView);
//            Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video);
//            videoView.setMediaController(mediaController);
//            videoView.setVideoURI(uri);
//            videoView.requestFocus();
//            videoView.start();
            im=(ImageView)findViewById(R.id.img);
            Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadin);
            im.startAnimation(animZoomIn);
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(splash.this, Home.class);
                startActivity(intent);
                finish();
            }, SPLASH_SCREEN_DURATION);
        }
    }
}