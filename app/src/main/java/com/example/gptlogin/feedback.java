package com.example.gptlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class feedback extends AppCompatActivity {
    private EditText nameEditText, mobileEditText, emailEditText, subjectEditText, descriptionEditText;
    private Button submitButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();}

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        emailEditText = findViewById(R.id.emailEditText);
        subjectEditText = findViewById(R.id.subjectEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        submitButton = findViewById(R.id.submitButton);

        // Set onClickListener for submit button
        submitButton.setOnClickListener(view -> submitFeedback());
    }

    private void submitFeedback() {
        // Get values from the input fields
        String name = nameEditText.getText().toString();
        String mobile = mobileEditText.getText().toString();
        String email = emailEditText.getText().toString();
        // String feedbackType = feedbackTypeSpinner.getSelectedItem().toString();
        String subject = subjectEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        // Simple validation
        if (name.isEmpty() || mobile.isEmpty() || email.isEmpty() || subject.isEmpty() || description.isEmpty()) {
            Toast.makeText(feedback.this, "All fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Process the feedback (e.g., send to server or save locally)
        // For now, we'll just show a Toast message
        Toast.makeText(feedback.this, "Feedback submitted successfully!", Toast.LENGTH_SHORT).show();

        // Clear the input fields after submission
        nameEditText.setText(name);
        mobileEditText.setText(mobile);
        emailEditText.setText(email);
        subjectEditText.setText(subject);
        descriptionEditText.setText(description);
        //  feedbackTypeSpinner.setSelection(0);
        Intent intent=new Intent(this, Home.class);
        startActivity(intent);
    }
}
