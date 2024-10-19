package com.example.gptlogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class fineinquiry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fineinquiry);
         Button licenseTab = findViewById(R.id.tab_license);
        licenseTab.setOnClickListener(v -> {
            Log.d("FineInquiry", "License tab clicked");
            Intent intent = new Intent(fineinquiry.this, licences.class);
            startActivity(intent);
        });
    }
}