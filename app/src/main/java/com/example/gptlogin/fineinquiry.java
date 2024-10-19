package com.example.gptlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

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
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button sumit=findViewById(R.id.sub);
        sumit.setOnClickListener(view -> {
            Toast.makeText(this,"sumbimited sucseess fully",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this, service.class);
            startActivity(intent);
        });
    }
}