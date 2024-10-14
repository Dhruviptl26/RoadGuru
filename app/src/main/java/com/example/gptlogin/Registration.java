package com.example.gptlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {
EditText name,email,dl,add;
Button btn;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        SharedPreferences sharedPreferences = getSharedPreferences("userDetails", MODE_PRIVATE);
        boolean isRegistered = sharedPreferences.getBoolean("isRegistered", false);

        if (isRegistered) {
            // If user is already registered, go to the next screen
            Intent intent = new Intent(Registration.this, UserDetails.class); // or any other activity
            startActivity(intent);
            finish(); // Close the registration activity
        }
        setContentView(R.layout.activity_registration);
        name=findViewById(R.id.etxxt);
        email=findViewById(R.id.editTextTextEmailAddress);
        dl=findViewById(R.id.editTextText5);
        add=findViewById(R.id.editTextTextPostalAddress);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("userDetails", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("isRegistered", true);
                editor.putString("name", name.getText().toString());
                editor.putString("email", email.getText().toString());
                editor.putString("dl", dl.getText().toString());
                editor.putString("address", add.getText().toString());
                editor.apply();
                Intent intent=new Intent(Registration.this, UserDetails.class);
                startActivity(intent);

            }
        });
    }
}