package com.example.gptlogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {

    private EditText phoneNo;
    private Button verifyButton, signInWithEmail;
    private DatabaseHelper databaseHelper;
    String emiratesId;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("userDetails", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // User is already logged in, redirect to UserDetails activity
            Intent intent = new Intent(Login.this, UserDetails.class);
            startActivity(intent);
            finish();  // Prevent going back to the login screen
            return;  // Stop further execution of onCreate
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
            // Initialize views
            phoneNo = findViewById(R.id.emiratesIdInput);
            verifyButton = findViewById(R.id.verifyButton);
            signInWithEmail = findViewById(R.id.signInWithUAEPASSButton);

            // Initialize DatabaseHelper
            databaseHelper = new DatabaseHelper(getApplicationContext());

            // Load saved Emirates ID if it exists
            //loadEmiratesId();

            // Verify button click listener
            sp=getSharedPreferences("user_details",MODE_PRIVATE);
            verifyButton.setOnClickListener(v -> {
                emiratesId = phoneNo.getText().toString();
                if (emiratesId.isEmpty()) {

                    Toast.makeText(Login.this, "Please enter phone no", Toast.LENGTH_SHORT).show();
                } else {
                    // Save Emirates ID using DatabaseHelper
                    databaseHelper.addLoginInfo(emiratesId);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("phoneNumber",emiratesId);
                    editor.putBoolean("isLoggedIn",true);
                    editor.apply();
                    Intent intent = new Intent(Login.this,Home.class);
                    startActivity(intent);
                    finish();
                }
            });

            // Sign in with UAE PASS button click listener
            signInWithEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Login.this, gmail.class);
                    startActivity(intent);
                }
            });

        }

    }
}