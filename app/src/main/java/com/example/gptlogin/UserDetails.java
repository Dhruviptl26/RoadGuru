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
    TextView name1,e,phone,ad,dl1;
    ImageView img;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_details);
        SharedPreferences sp = getSharedPreferences("userDetails", MODE_PRIVATE);
       name1=findViewById(R.id.textViewName);
       ad=findViewById(R.id.textViewAddress);
       dl1=findViewById(R.id.textViewDL);
       phone=findViewById(R.id.textViewPhone);
       e=findViewById(R.id.textViewEmail);


        String name = sp.getString("name", "No Name");
        String email = sp.getString("email", "No Email");
        String dl = sp.getString("dl", "No DL");
        String address = sp.getString("address", "No Address");
        String num = sp.getString("phoneNumber", "num");
name1.setText(name);
ad.setText(address);
e.setText(email);
dl1.setText(dl);
phone.setText(num);

       Logout=(Button)findViewById(R.id.buttonLogout);
       Logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(UserDetails.this, "logged out", Toast.LENGTH_SHORT).show();
               SharedPreferences.Editor editor = sp.edit();
               editor.putBoolean("isLoggedIn", false);
               editor.apply();
               editor.putBoolean("isRegistered", false); // Reset the registration status
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
