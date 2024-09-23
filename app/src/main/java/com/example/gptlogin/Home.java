package com.example.gptlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity
{
    private Button searchFineBtn;
    private EditText referenceNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        searchFineBtn = findViewById(R.id.searchFineButton);
        referenceNumber = findViewById(R.id.referenceNumber);

        // Set up ActionBar with custom layout
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            LayoutInflater inflater = LayoutInflater.from(this);
            View customActionBarView = inflater.inflate(R.layout.custom_action_bar, null);

            if (customActionBarView != null) {
                actionBar.setCustomView(customActionBarView);

                // Set up click listeners for the icons
                ImageView userIcon = customActionBarView.findViewById(R.id.lastUserIcon);
                ImageView searchIcon = customActionBarView.findViewById(R.id.searchIcon);

                if (userIcon != null && searchIcon != null) {
                    // Handle user icon click
                    userIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Home.this, Login.class);
                            startActivity(intent);
                        }
                    });

                    searchIcon.setOnClickListener(v ->
                    {
                        Toast.makeText(Home.this, "Search icon clicked", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    Toast.makeText(this, "Icons not found", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Failed to inflate custom action bar", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "ActionBar is null", Toast.LENGTH_SHORT).show();
        }

        // Add click listener for the Search Fine button
        searchFineBtn.setOnClickListener(v -> {
            String refNum = referenceNumber.getText().toString();
            if (refNum.isEmpty()) {
                Toast.makeText(Home.this, "Please insert a reference number", Toast.LENGTH_SHORT).show();
            } else {
                // Logic for searching fines with the reference number
                Toast.makeText(Home.this, "Searching fines for reference: " + refNum, Toast.LENGTH_SHORT).show();
            }
        });

        // Set up BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    return true;
                } else if (item.getItemId() == R.id.nav_services) {Toast.makeText(Home.this, "Services selected", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Home.this, service.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.nav_901) {
                    Intent intent = new Intent(Home.this, call.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.nav_more) {
                    Intent intent = new Intent(Home.this, feedback.class);
                    startActivity(intent);
                    return true;
                } else {
                    Toast.makeText(Home.this, "Unknown item selected", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        });
    }
}
