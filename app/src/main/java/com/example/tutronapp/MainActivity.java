package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etFirstName, etLastName, etEmail, etPassword;
    private Button btnRegisterStudent, btnRegisterTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegisterStudent = findViewById(R.id.btnRegisterStudent);
        btnRegisterTutor = findViewById(R.id.btnRegisterTutor);

        btnRegisterStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                registerStudent(firstName, lastName, email, password);
            }
        });

        btnRegisterTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                registerTutor(firstName, lastName, email, password);
            }
        });
    }

    private void registerStudent(String firstName, String lastName, String email, String password) {
        Toast.makeText(MainActivity.this, "Student registration successful!", Toast.LENGTH_SHORT).show();
    }

    private void registerTutor(String firstName, String lastName, String email, String password) {
        Toast.makeText(MainActivity.this, "Tutor registration successful!", Toast.LENGTH_SHORT).show();
    }
}