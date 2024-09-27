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
   private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getSharedPreferences("userDetails", MODE_PRIVATE);
        boolean isLoggedIn = sp.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // User is already logged in, redirect to UserDetails activity
            Intent intent = new Intent(Login.this, UserDetails.class);
            startActivity(intent);
            finish();  // Prevent going back to the login screen
            return;  // Stop further execution of onCreate
        }

            // Initialize views
            phoneNo = findViewById(R.id.emiratesIdInput);
            verifyButton = findViewById(R.id.verifyButton);
            signInWithEmail = findViewById(R.id.signInWithUAEPASSButton);

            // Initialize DatabaseHelper
            databaseHelper = new DatabaseHelper(getApplicationContext());

            // Load saved Emirates ID if it exists
        loadPhoneNumber();

            // Verify button click listener
            sp=getSharedPreferences("userDetails",MODE_PRIVATE);
        SharedPreferences finalSp = sp;
        SharedPreferences finalSp1 = sp;
        verifyButton.setOnClickListener(v -> {
                emiratesId = phoneNo.getText().toString();
                if (emiratesId.isEmpty()) {

                    Toast.makeText(Login.this, "Please enter phone no", Toast.LENGTH_SHORT).show();
                } else {
                    // Save Emirates ID using DatabaseHelper
                    databaseHelper.addLoginInfo(emiratesId);
                    SharedPreferences.Editor editor= finalSp1.edit();
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

    private void loadPhoneNumber() {
        String savedPhoneNumber = databaseHelper.getPhoneNumber();
        if (savedPhoneNumber != null && !savedPhoneNumber.isEmpty()) {
            phoneNo.setText(savedPhoneNumber);
        }
    }
}