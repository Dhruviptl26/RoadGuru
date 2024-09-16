package com.example.gptlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
                    userIcon.setOnClickListener(v -> {
                        Intent intent = new Intent(Home.this, Login.class);
                        startActivity(intent);
                    });

                    // Handle search icon click
                    searchIcon.setOnClickListener(v -> {
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

    }
}
