package com.example.gptlogin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class call extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

            Button callEmergencyButton = findViewById(R.id.call_emergency);
            Button callCenterButton = findViewById(R.id.call_center);

            callEmergencyButton.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:100"));
                startActivity(intent);
            });

            callCenterButton.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:108"));
                startActivity(intent);
            });
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setSelectedItemId(R.id.nav_901);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.getItemId() == R.id.nav_home) {
                        //Toast.makeText(call.this, "Home selected", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(call.this, Home.class);
                        startActivity(intent);
                        return true;
                    } else if (item.getItemId() == R.id.nav_services) {
                        Intent intent=new Intent(call.this, service.class);
                        startActivity(intent);
                        return true;
                    } else if (item.getItemId() == R.id.nav_901) {
                        return true;
                    } else if (item.getItemId() == R.id.nav_more) {
                        Intent intent = new Intent(call.this, feedback.class);
                        startActivity(intent);
                        return true;
                    } else {
                        Toast.makeText(call.this, "Unknown item selected", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }

            });

    }
}

