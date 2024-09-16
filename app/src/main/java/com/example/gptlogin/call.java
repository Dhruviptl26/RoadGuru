package com.example.gptlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class call extends AppCompatActivity {
    Button callEmergencyButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callEmergencyButton   =(Button)findViewById(R.id.call_emergency);
        Button callCenterButton = findViewById(R.id.call_center);
        Button alAmeenButton = findViewById(R.id.al_ameen);

        callEmergencyButton.setOnClickListener(v ->
        {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:999"));
            startActivity(intent);
        });

        callCenterButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:901"));
            startActivity(intent);
        });

        alAmeenButton.setOnClickListener(v -> {
            // Assuming this will open a web link or another activity
            // Modify as per your requirement
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.alameen.com"));
            startActivity(intent);
        });
    }
}
