package com.example.gptlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    private Button searchFineBtn, submitReference;
    private EditText referenceNumber, locationInput;
    private GoogleMap mMap;
    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        submitReference = findViewById(R.id.searchParkingButton);
        searchFineBtn = findViewById(R.id.searchFineButton);

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
                    userIcon.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SharedPreferences sharedPreferences = getSharedPreferences("userDetails", MODE_PRIVATE);
                            boolean isLoogedIn = sharedPreferences.getBoolean("isLoggedIn", false);

                            if (isLoogedIn) {
                                Intent intent = new Intent(Home.this, Registration.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(Home.this, Login.class);
                                startActivity(intent);
                            }
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
            Intent intent=new Intent(this, fineinquiry.class);
            startActivity(intent);
        });
        submitReference.setOnClickListener(v -> {
            Intent intent=new Intent(this, GoogleMapsActivity.class);
            startActivity(intent);
        });
        // Set up BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    return true;
                } else if (item.getItemId() == R.id.nav_services) {//Toast.makeText(Home.this, "Services selected", Toast.LENGTH_SHORT).show();
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