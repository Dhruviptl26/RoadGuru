package com.example.gptlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class service extends AppCompatActivity {

    private GridLayout serviceGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        EdgeToEdge.enable(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();

             serviceGrid = findViewById(R.id.service_grid);
            //setupGridItems();

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setSelectedItemId(R.id.nav_services);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.getItemId() == R.id.nav_home) {
                        //Toast.makeText(call.this, "Home selected", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(service.this, Home.class);
                        startActivity(intent);
                        return true;
                    } else if (item.getItemId() == R.id.nav_services)
                    {
                        return true;
                    } else if (item.getItemId() == R.id.nav_901) {
                        Intent intent = new Intent(service.this, call.class);
                        startActivity(intent);
                        return true;
                    } else if (item.getItemId() == R.id.nav_more) {
                        Intent intent = new Intent(service.this, feedback.class);
                        startActivity(intent);
                        return true;
                    } else {
                        Toast.makeText(service.this, "Unknown item selected", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            });
        }
    }

    public GridLayout getServiceGrid() {
        return serviceGrid;
    }

    public void setServiceGrid(GridLayout serviceGrid) {
        this.serviceGrid = serviceGrid;
    }
}