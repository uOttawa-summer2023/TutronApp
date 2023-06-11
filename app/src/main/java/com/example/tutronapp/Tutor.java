package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Tutor extends AppCompatActivity {

    private EditText etFirstName, etLastName, etEducationLevel, etEmail, etPassword, etNativeLanguage, etDescription;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEducationLevel = findViewById(R.id.etEducationLevel);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etNativeLanguage = findViewById(R.id.etNativeLanguage);
        etDescription = findViewById(R.id.etDescription);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String educationLevel = etEducationLevel.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String nativeLanguage = etNativeLanguage.getText().toString().trim();
                String description = etDescription.getText().toString().trim();

                if (firstName.isEmpty() || lastName.isEmpty() || educationLevel.isEmpty() ||
                        email.isEmpty() || password.isEmpty() || nativeLanguage.isEmpty() ||
                        description.isEmpty()) {
                    Toast.makeText(Tutor.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                } else if (description.length() >= 600) {
                    Toast.makeText(Tutor.this, "Description should not exceed 600 characters", Toast.LENGTH_SHORT).show();
                } else {
                    registerTutor(); // When all fields are filled in
                }
            }
        });
    }

    // Performs the registration for the tutor
    private void registerTutor() {
        Toast.makeText(Tutor.this, "Tutor registration successful!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, Welcome.class);
        intent.putExtra("role", "Tutor");
        startActivity(intent);
    }
}
