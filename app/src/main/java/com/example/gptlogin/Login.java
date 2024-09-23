package com.example.gptlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {

    private EditText emiratesIdInput;
    private Button verifyButton, signInWithUAEPASSButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        // Initialize views
        emiratesIdInput = findViewById(R.id.emiratesIdInput);
        verifyButton = findViewById(R.id.verifyButton);
        signInWithUAEPASSButton = findViewById(R.id.signInWithUAEPASSButton);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(getApplicationContext());

        // Load saved Emirates ID if it exists
        //loadEmiratesId();

        // Verify button click listener
        verifyButton.setOnClickListener(v -> {
            String emiratesId = emiratesIdInput.getText().toString();
            if (emiratesId.isEmpty()) {
                Toast.makeText(Login.this, "Please enter phone no", Toast.LENGTH_SHORT).show();
            } else {
                // Save Emirates ID using DatabaseHelper
                databaseHelper.addLoginInfo(emiratesId);
                Toast.makeText(Login.this, "phone no  saved: " + emiratesId, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
            }
        });

        // Sign in with UAE PASS button click listener
        signInWithUAEPASSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this, gmail.class);
                startActivity(intent);
            }
        });

    }

    // Method to load Emirates ID from the database

}
}