package com.example.gptlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class women extends AppCompatActivity {

    private Button btnCall999, btnCall901, btnOpenAlAmeen;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women);

        btnCall999 = findViewById(R.id.btn999);
        btnCall901 = findViewById(R.id.btnCall901);

        // Set the onClickListeners for the buttons
        btnCall999.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall("181");
            }
        });

        btnCall901.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall("1098");
            }
        });
    }

    // Method to initiate a phone call
    private void makePhoneCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }
    // Method to open the Al Ameen service
    }

