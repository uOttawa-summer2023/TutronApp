package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class Welcome extends AppCompatActivity {
    private TextView tvWelcome;
    private Button btnLogOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogOff = findViewById(R.id.btnLogOff);

        // Getting the role passed from the previous activity
        String role = getIntent().getStringExtra("role");

        // Displays the welcome message with the user's role
        String welcomeMessage = "Welcome! You are logged in as " + role;
        tvWelcome.setText(welcomeMessage);

        btnLogOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOff();
            }
        });
    }

    private void logOff() {
        // Navigating back to the MainActivity (login page)
        Intent intent = new Intent(Welcome.this, MainActivity.class);
        startActivity(intent);
        finish(); // Finish the Welcome activity
    }

}
