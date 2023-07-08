package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class Student extends AppCompatActivity {

    private EditText etFirstName, etLastName, etEmail, etPassword, etAddress, etCardNumber;
    private Button btnRegister;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etAddress = findViewById(R.id.etAddress);
        etCardNumber = findViewById(R.id.etCardNumber);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String cardNumber = etCardNumber.getText().toString().trim();

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
                        password.isEmpty() || address.isEmpty() || cardNumber.isEmpty()) {
                    Toast.makeText(Student.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper = new DBHelper(Student.this);
                    dbHelper.registerStudent(firstName,lastName,email,password,address,"student",cardNumber);
                    registerStudent(); // When all fields are filled in
                }
            }
        });
    }

    // Performs the registration for the student
    private void registerStudent() {
        Toast.makeText(Student.this, "Student registration successful!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Welcome.class);
        intent.putExtra("role", "Student");
        startActivity(intent);
    }
}
