package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin, btnRegisterStudent, btnRegisterTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegisterStudent = findViewById(R.id.btnRegisterStudent);
        btnRegisterTutor = findViewById(R.id.btnRegisterTutor);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                login(email, password);
            }
        });

        btnRegisterStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerStudent();
            }
        });

        btnRegisterTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerTutor();
            }
        });
    }

    private void login(String email, String password) {
        if (email.equals("rroma016@uottawa.ca") && password.equals("Adm!nP@s5w0rD")) {
            // Logging in as Administrator
            Toast.makeText(MainActivity.this, "Logged in as Administrator", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Welcome.class);
            intent.putExtra("role", "Administrator");
            startActivity(intent);
        } else {
            // If there is invalid credentials
            Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }

    }

    public void registerStudent() {
        Intent intent = new Intent(this, Student.class);
        startActivity(intent);
    }

    public void registerTutor() {
        Intent intent = new Intent(this, Tutor.class);
        startActivity(intent);
    }
}
