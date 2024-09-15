package com.example.gptlogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class gmail extends AppCompatActivity {

    private EditText emiratesIdInput;
    private CheckBox rememberMeCheckbox;
    private Button loginButton;
    private TextView createAccountLink, recoverAccountLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail);

        // Initialize views
        emiratesIdInput = findViewById(R.id.emiratesIdInput);
        rememberMeCheckbox = findViewById(R.id.rememberMeCheckbox);
        loginButton = findViewById(R.id.loginButton);
        createAccountLink = findViewById(R.id.createAccountLink);
        recoverAccountLink = findViewById(R.id.recoverAccountLink);

        // Handle Login button click
        loginButton.setOnClickListener(v -> {
            String emiratesId = emiratesIdInput.getText().toString();
            if (emiratesId.isEmpty()) {
                Toast.makeText(gmail.this, "Please enter Emirates ID", Toast.LENGTH_SHORT).show();
            } else {
                // Here you can add login logic (e.g., save Emirates ID to SharedPreferences)
                Toast.makeText(gmail.this, "Logged in with Emirates ID: " + emiratesId, Toast.LENGTH_SHORT).show();
            }
            Intent intent=new Intent(gmail.this, MainActivity.class);
            startActivity(intent);
        });

        // Create new account link
        createAccountLink.setOnClickListener(v -> {
            // Handle creating a new account logic
            Toast.makeText(gmail.this, "Redirecting to create account page...", Toast.LENGTH_SHORT).show();
        });

        // Recover account link
        recoverAccountLink.setOnClickListener(v -> {
            // Handle account recovery logic
            Toast.makeText(gmail.this, "Redirecting to account recovery page...", Toast.LENGTH_SHORT).show();
        });

    }
}
