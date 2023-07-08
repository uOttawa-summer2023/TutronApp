package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin, btnRegisterStudent, btnRegisterTutor;

    public DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegisterStudent = findViewById(R.id.btnRegisterStudent);
        btnRegisterTutor = findViewById(R.id.btnRegisterTutor);

        //CREATING DATABASE TO STORE STUDENT AND TUTOR INFORMATION
        //dbHelper = new DBHelper(this);



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
   /* private DBHelper getDbHelper(){
        return dbHelper;
    }*/

    private void login(String email, String password) {
        dbHelper = new DBHelper(MainActivity.this);
        String role = "INITIALIZED";

        if (email.equals("rroma016@uottawa.ca") && password.equals("Adm!nP@s5w0rD")) {
            // Logging in as Administrator
            Toast.makeText(MainActivity.this, "Logged in as Administrator", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Welcome.class);
            intent.putExtra("role", "Administrator");
            startActivity(intent);
        } else if (dbHelper.authenticateUser(email, password)) {
            role = dbHelper.getRole();

            if (role.equals("student")) {
                // Launch Student Activity
                Toast.makeText(MainActivity.this, "Logged in as Student", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Welcome.class);
                intent.putExtra("role", "Student");
                startActivity(intent);
            } else if (role.equals("tutor")) {
                // Checks if the tutor is not suspended or dismissed
                String status = dbHelper.getStatus();
                if (status.equals("active")) {
                    // Launch Tutor activity
                    Toast.makeText(MainActivity.this, "Logged in as Tutor", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Welcome.class);
                    intent.putExtra("role", "Tutor");
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "YOU ARE LOCKED OUT", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            // If there are invalid credentials
            Toast.makeText(MainActivity.this, role, Toast.LENGTH_SHORT).show();
            etEmail.setText("");
            etPassword.setText("");
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
