package com.example.tutronapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {

    boolean makeStudentAccount = true;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DBHandler(Registration.this);

        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etConfirmPassword = findViewById(R.id.etConfirmPassword);
        EditText etFirstName = findViewById(R.id.etFirstName);
        EditText etLastName = findViewById(R.id.etLastName);

        // Tutor
        EditText etEducationLevel = findViewById(R.id.etEducationLevel);
        EditText etNativeLanguage = findViewById(R.id.etNativeLanguage);
        EditText etDescription = findViewById(R.id.etDescription);

        // Student
        EditText etAddress = findViewById(R.id.etAddress);
        EditText etCardNumber = findViewById(R.id.etCardNumber);

        Button btnRegister = findViewById(R.id.createAccount);
        RadioGroup rgTypeAccount = findViewById(R.id.createAccountType);

        rgTypeAccount.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                makeStudentAccount = checkedId == R.id.createStudent;
                updateLayout();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEmail = etEmail.getText().toString().trim();
                String newPassword = etPassword.getText().toString();
                String newFirstName = etFirstName.getText().toString().trim();
                String newLastName = etLastName.getText().toString().trim();

                if (newEmail.isEmpty() || newPassword.isEmpty() || newFirstName.isEmpty() || newLastName.isEmpty()) {
                    Toast.makeText(Registration.this, "All fields must be completed", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!newPassword.equals(etConfirmPassword.getText().toString())) {
                    Toast.makeText(Registration.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (makeStudentAccount) {
                    // Validate and create student account
                    String newAddress = etAddress.getText().toString().trim();
                    String newCardNumber = etCardNumber.getText().toString().trim();

                    if (newAddress.isEmpty() || newCardNumber.isEmpty()) {
                        Toast.makeText(Registration.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                    } else {
                        db.registerStudent(newFirstName, newLastName, newEmail, newPassword, newAddress, "student", newCardNumber);
                        Toast.makeText(Registration.this, "Student account created", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    // Validate and create tutor account
                    String newEducationLevel = etEducationLevel.getText().toString().trim();
                    String newNativeLanguage = etNativeLanguage.getText().toString().trim();
                    String newDescription = etDescription.getText().toString().trim();

                    if (newEducationLevel.isEmpty() || newNativeLanguage.isEmpty() || newDescription.isEmpty()) {
                        Toast.makeText(Registration.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                    } else if (newDescription.length() >= 600) {
                        Toast.makeText(Registration.this, "Description should not exceed 600 characters", Toast.LENGTH_SHORT).show();
                    } else {
                        db.registerTutor(newFirstName, newLastName, newEmail, newPassword, newEducationLevel, "tutor",
                                newNativeLanguage, newDescription, "active");
                        Toast.makeText(Registration.this, "Tutor account created", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

        // Initially update the layout based on the default selection (student account)
        updateLayout();
    }

    private void updateLayout() {
        if (makeStudentAccount) {
            // Show student registration fields and hide tutor registration fields
            findViewById(R.id.includeStudent).setVisibility(View.VISIBLE);
            findViewById(R.id.includeTutor).setVisibility(View.GONE);
        } else {
            // Show tutor registration fields and hide student registration fields
            findViewById(R.id.includeStudent).setVisibility(View.GONE);
            findViewById(R.id.includeTutor).setVisibility(View.VISIBLE);
        }
    }
}
