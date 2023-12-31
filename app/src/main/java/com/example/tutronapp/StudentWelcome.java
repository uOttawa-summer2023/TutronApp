package com.example.tutronapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentWelcome extends AppCompatActivity {

    TextView printID, printEmail, printFirstName, printLastName;
    Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_welcome);

        // Setup text boxes
        printID = findViewById(R.id.printID);
        printEmail = findViewById(R.id.printEmail);
        printFirstName = findViewById(R.id.printFirstName);
        printLastName = findViewById(R.id.printLastName);

        // Get user data
        Student student = (Student) getIntent().getSerializableExtra("STUDENT");

        // Display data
        printID.setText("Account ID: " + student.getID());
        printEmail.setText("Email: " + student.getEmail());
        printFirstName.setText("First name: " + student.getFirstName());
        printLastName.setText("Last name: " + student.getLastName());

        // Set up the Log Out button
        btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform log out action here (e.g., clear user session, navigate back to login)
                logout();
            }
        });

        /*// Set up the View Lessons button click listener
        Button btnViewLessons = findViewById(R.id.btnViewTopics);
        btnViewLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to StudentViewLessons activity
                Intent intent = new Intent(StudentWelcome.this, StudentViewLessons.class);
                intent.putExtra("STUDENT", student); // Pass the student object to the view lessons activity
                startActivityForResult(intent, REQUEST_VIEW_TOPICS);
            }
        });*/
    }
    private void logout() {
        // Perform any necessary log out actions (e.g., clear user session, reset data, etc.)
        // In this example, I'm assuming you have a LoginActivity as your login screen
        Intent intent = new Intent(StudentWelcome.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish(); // Finish this activity to prevent user from coming back via back button
    }

}

