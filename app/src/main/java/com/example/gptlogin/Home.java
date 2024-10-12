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

import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity
{
    private Button searchFineBtn,submitReference;
    private EditText referenceNumber,locationInput;
 private GoogleMap mMap;
    int PERMISSION_ID=44;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        submitReference  = findViewById(R.id.searchParkingButton);
        searchFineBtn = findViewById(R.id.searchFineButton);
locationInput=findViewById(R.id.locationEditText);
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
                                }
                                else{
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
                }
            else {
                Toast.makeText(this, "Failed to inflate custom action bar", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "ActionBar is null", Toast.LENGTH_SHORT).show();
        }

        // Add click listener for the Search Fine button
        searchFineBtn.setOnClickListener(v -> {

        });
        submitReference.setOnClickListener(v -> {
            String location = locationInput.getText().toString();
            if (!location.isEmpty()) {
                // Logic to find nearest parking space using the entered location
                findNearestParking(location);
            } else {
                Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show();
            }//checkPermissionsAndFindParking();
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
    private void findNearestParking(String location) {
        // Assuming you have a method to get location coordinates from the location string
        Toast.makeText(this, "Could not find the location", Toast.LENGTH_SHORT).show();

    }

    }

//        fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if (location != null) {
//                    double latitude = location.getLatitude();
//                    double longitude = location.getLongitude();
//
//                    // Placeholder toast for now
//                    Toast.makeText(Home.this, "Latitude: " + latitude + ", Longitude: " + longitude, Toast.LENGTH_SHORT).show();
//
//                    // Here you can make a request to an API or use logic to find parking spaces nearby
//                    // For example, integrate Google Places API to get parking spots based on the location
//                } else {
//                    Toast.makeText(Home.this, "Unable to get location", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


