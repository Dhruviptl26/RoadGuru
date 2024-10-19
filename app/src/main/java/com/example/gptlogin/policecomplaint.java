package com.example.gptlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class policecomplaint extends AppCompatActivity {
    private EditText etName, etEmail, etMobileIntl, etMobile, etComplaintDetails;
    private Spinner spNationality, spGender;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_policecomplaint);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etMobileIntl = findViewById(R.id.etMobileIntl);
        etMobile = findViewById(R.id.etMobile);
        etComplaintDetails = findViewById(R.id.etComplaintDetails);

        // Initialize Spinners
        spNationality = findViewById(R.id.spNationality);
        spGender = findViewById(R.id.spGender);

        // Set up Spinner data
        setupSpinners();
        Button sub=findViewById(R.id.btn2);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(policecomplaint.this,"Submited",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(policecomplaint.this, service.class);
                startActivity(intent);
            }
        });
    }
    private void setupSpinners() {
        // Sample data for nationality and gender
        String[] nationalities = {"Select Nationality", "American", "British", "Canadian", "Indian"};
        String[] genders = {"Select Gender", "Male", "Female", "Other"};

        // Adapter for nationality spinner
        ArrayAdapter<String> nationalityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, nationalities);
        nationalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNationality.setAdapter(nationalityAdapter);

        // Adapter for gender spinner
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, genders);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(genderAdapter);
    }

    private void submitForm() {
        // Validate form fields
        if (etName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (spNationality.getSelectedItem().toString().equals("Select Nationality")) {
            Toast.makeText(this, "Please select a nationality", Toast.LENGTH_SHORT).show();
            return;
        }

        if (spGender.getSelectedItem().toString().equals("Select Gender")) {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return;
        }

        // Example: Retrieving user input
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String mobileIntl = etMobileIntl.getText().toString();
        String complaintDetails = etComplaintDetails.getText().toString();

        // Display or send data to server
        Toast.makeText(this, "Form Submitted", Toast.LENGTH_SHORT).show();
    }


}