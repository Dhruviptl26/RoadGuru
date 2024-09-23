package com.example.gptlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout; // Use the correct GridLayout import
import androidx.gridlayout.widget.GridLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class service extends AppCompatActivity {

   // private GridLayout serviceGrid;  // This should now refer to androidx.gridlayout.widget.GridLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view
        setContentView(R.layout.activity_service);

        // Hide the ActionBar if it exists
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize the service grid layout
        //serviceGrid = findViewById(R.id.service_grid);


        // Bottom Navigation setup
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_services);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    Intent intent = new Intent(service.this, Home.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.nav_services) {
                    Toast.makeText(service.this, "Services selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.nav_901) {
                    Intent intent = new Intent(service.this, call.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.nav_more) {
                    return true;
                } else {
                    Toast.makeText(service.this, "Unknown item selected", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        });
    }
}
