package com.example.gptlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class UserDetails extends AppCompatActivity {
    Button Logout;
    TextView tv;
    ImageView img;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_details);
        SharedPreferences sp = getSharedPreferences("userDetails", MODE_PRIVATE);
        img=(ImageView)findViewById(R.id.img);
        tv = (TextView) findViewById(R.id.textView3);
        String num = sp.getString("phoneNumber", "num");
        tv.setText(num);
       Logout=(Button)findViewById(R.id.button2);
       Logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(UserDetails.this, "logged out", Toast.LENGTH_SHORT).show();
               SharedPreferences.Editor editor = sp.edit();
               editor.putBoolean("isLoggedIn", false);
               editor.apply();

               // Redirect to Login activity
               Intent intent = new Intent(UserDetails.this, Login.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent);
               finish();
           }
       });


    }
}
