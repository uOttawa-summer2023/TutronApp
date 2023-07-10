package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {
    private TextView tvWelcome;
    private Button btnLogOff,btnInbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogOff = findViewById(R.id.btnLogOff);
        btnInbox = findViewById(R.id.btnInbox);

        // Getting the role passed from the previous activity
        String role = getIntent().getStringExtra("role");

        // Displays the welcome message with the user's role
        String welcomeMessage = "Welcome! You are logged in as " + role;
        tvWelcome.setText(welcomeMessage);

        btnLogOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logOff();
                Log.d("hi", "hi");
            }
        });
        btnInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(role.equals("Administrator")){
                    Log.d("hi", "hi");
                    inbox();
                    Log.d("hi", "hi");
                } else if (role.equals("Student")) {
                    Log.d("hi", "hi");
                    stdDashBoard();
                    Log.d("hi", "hi");
                }
                else {
                    Log.d("hi", "hi");
                    Toast.makeText(Welcome.this,role, Toast.LENGTH_SHORT).show();
                    tutorDashBoard();
                    Log.d("hi", "hi");
                }

            }
        });

    }

    private void tutorDashBoard() {
        Intent intent = new Intent(Welcome.this, Tutor.class);
        startActivity(intent);
        finish(); // Finish the Welcome activity

    }

    private void stdDashBoard() {
        Intent intent = new Intent(Welcome.this, Student.class);
        startActivity(intent);
        finish(); // Finish the Welcome activity

    }

    private void logOff() {
        // Navigating back to the MainActivity (login page)
        Intent intent = new Intent(Welcome.this, MainActivity.class);
        startActivity(intent);
        finish(); // Finish the Welcome activity
    }
    private void inbox() {
        Intent intent = new Intent(Welcome.this, Admin.class);
        startActivity(intent);
        finish(); // Finish the Welcome activity

    }

}
