package com.example.gptlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gptlogin.R;

public class login1 extends AppCompatActivity {
    SharedPreferences prf;
    Intent intent;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login1);
        //TextView result = (TextView)findViewById(R.id.resultView);
        Button LogOut = (Button)findViewById(R.id.btnLogOut);
        prf = getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(this, Login.class);

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.apply();
                Toast.makeText(login1.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();

            }
        });


    }
}