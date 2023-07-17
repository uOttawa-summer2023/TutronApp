package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

//import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.widget.RadioGroup;

//import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    //int rgType = 0; // askes user to select if student, instructor, or admin
    EditText etEmail, etPassword;
    Button btnLogin, btnRegister;
    RadioGroup rgTypeAccount;
    //public DBHelper dbHelper;
    int typeAccount =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        rgTypeAccount = findViewById(R.id.rgTypeAccount);

        rgTypeAccount.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.selectStudent) {
                    typeAccount = 0; // Student
                } else if (checkedId == R.id.selectTutor) {
                    typeAccount = 1; // Tutor
                } else if (checkedId == R.id.selectAdmin) {
                    typeAccount = 2; // Admin
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler db = new DBHandler(MainActivity.this);

                String inputEmail = etEmail.getText().toString().trim();
                String inputPassword = etPassword.getText().toString().trim();

                if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please complete all fields", Toast.LENGTH_SHORT).show();
                } else {
                    int selectedId = rgTypeAccount.getCheckedRadioButtonId();
                    if (selectedId == R.id.selectStudent) {
                        Student student = (Student) db.userLogin(inputEmail, inputPassword);
                        if (student != null) {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            studentScreenActivity(student);
                        } else {
                            Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    } else if (selectedId == R.id.selectTutor) {
                        Tutor tutor = (Tutor) db.userLogin(inputEmail, inputPassword);
                        if (tutor != null) {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            tutorScreenActivity(tutor);
                        } else {
                            Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    } else if (selectedId == R.id.selectAdmin) {
                        if (Admin.adminCredentials(inputEmail, inputPassword)) {
                            adminScreenActivity();
                        } else {
                            Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Please select an account type", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegistration();
            }
        });


        //CREATING DATABASE TO STORE STUDENT AND TUTOR INFORMATION
        //dbHelper = new DBHelper(this);
    }

    public void openRegistration() {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

    public void adminScreenActivity() {
        Intent intent = new Intent(this, Admin.class);
        startActivity(intent);
    }

    public void studentScreenActivity(Student student) {
        Intent intent = new Intent(MainActivity.this, StudentWelcome.class);
        intent.putExtra("STUDENT", student);
        startActivity(intent);
    }

    public void tutorScreenActivity(Tutor tutor) {
        Intent intent = new Intent(MainActivity.this, TutorWelcome.class);
        intent.putExtra("TUTOR", tutor);
        startActivity(intent);
    }


}
