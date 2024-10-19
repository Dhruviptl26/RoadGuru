package com.example.gptlogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class licences extends AppCompatActivity {
    private EditText dubaiCodeEditText, licenseNumberEditText;
    private TextView enterLicenseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_licences);
        dubaiCodeEditText = findViewById(R.id.license_dubai_code);
        licenseNumberEditText = findViewById(R.id.license_number);
        enterLicenseText = findViewById(R.id.enter_license_text);
        Button licenseTab = findViewById(R.id.plate_tab);
        licenseTab.setOnClickListener(v -> {
            Log.d("FineInquiry", "License tab clicked");
            Intent intent = new Intent(licences.this, fineinquiry.class);
            startActivity(intent);
        });
        Button sumit=findViewById(R.id.submit_button);
        sumit.setOnClickListener(view -> {
            Toast.makeText(this,"sumbimited sucseess fully",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this, service.class);
            startActivity(intent);
        });
    }
}