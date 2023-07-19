package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class TutorWelcome extends AppCompatActivity {

    TextView printID, printEmail, printFirstName, printLastName, printEducationLevel, printNativeLanguage, printDescription;
    TextView txtTutorSuspensionStatus, txtTutorSuspensionEndDate; // TextView to display suspended message
    Button btnLogOut;

    private static final int REQUEST_EDIT_PROFILE = 1;

    private Tutor tutor;

    private List<TutorTopics> offeredTopicsList; // List to hold offered topics

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_welcome);

        // Setup text boxes
        printID = findViewById(R.id.printID);
        printEmail = findViewById(R.id.printEmail);
        printFirstName = findViewById(R.id.printFirstName);
        printLastName = findViewById(R.id.printLastName);
        printEducationLevel = findViewById(R.id.printEducationLevel);
        printNativeLanguage = findViewById(R.id.printNativeLanguage);
        printDescription = findViewById(R.id.printDescription);
        txtTutorSuspensionStatus = findViewById(R.id.txtTutorSuspensionStatus);
        txtTutorSuspensionEndDate = findViewById(R.id.txtTutorSuspensionEndDate); // Initialize TextView

        // Get user data
        Tutor tutor = (Tutor) getIntent().getSerializableExtra("TUTOR");

        // Check if the tutor is suspended and show the appropriate message
        if (tutor.isSuspended()) {
            showSuspendedMessage(tutor);
        } else {
            // If the tutor is not suspended, display the tutor's details and enable actions
            displayTutorDetails(tutor);

            // Set up the Log Out button
            btnLogOut = findViewById(R.id.btnLogOut);
            btnLogOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Perform log out action here (e.g., clear user session, navigate back to login)
                    logout();
                }
            });

            // Set up the Edit Profile button click listener
            Button btnEditProfile = findViewById(R.id.btnEditProfile);
            btnEditProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Navigate to TutorProfileActivity for editing the tutor's profile
                    Intent intent = new Intent(TutorWelcome.this, TutorProfileActivity.class);
                    intent.putExtra("TUTOR", tutor); // Pass the tutor object to the profile activity
                    startActivityForResult(intent, REQUEST_EDIT_PROFILE);
                }
            });

            // Set up the View Topics button click listener
            Button btnViewTopics = findViewById(R.id.btnViewTopics);
            btnViewTopics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Navigate to TutorViewTopics activity
                    Intent intent = new Intent(TutorWelcome.this, TutorViewTopics.class);
                    intent.putExtra("TUTOR", tutor); // Pass the tutor object to the view topics activity
                    startActivity(intent);
                }
            });
        }
    }

    private void showSuspendedMessage(Tutor tutor) {
        // Hide all other views and only show the suspension message
        findViewById(R.id.textView7).setVisibility(View.GONE);
        findViewById(R.id.printID).setVisibility(View.GONE);
        findViewById(R.id.printEmail).setVisibility(View.GONE);
        findViewById(R.id.printFirstName).setVisibility(View.GONE);
        findViewById(R.id.printLastName).setVisibility(View.GONE);
        findViewById(R.id.printEducationLevel).setVisibility(View.GONE);
        findViewById(R.id.printNativeLanguage).setVisibility(View.GONE);
        findViewById(R.id.printDescription).setVisibility(View.GONE);
        findViewById(R.id.btnLogOut).setVisibility(View.GONE);
        findViewById(R.id.btnEditProfile).setVisibility(View.GONE);
        findViewById(R.id.btnViewTopics).setVisibility(View.GONE);

        // Display the suspension message
        TextView txtTutorSuspensionStatus = findViewById(R.id.txtTutorSuspensionStatus);
        TextView txtTutorSuspensionEndDate = findViewById(R.id.txtTutorSuspensionEndDate);

        if (tutor.isTemporarySuspended()) {
            // Formulate the temporary suspension message
            String suspensionMessage = "Your account is temporarily suspended until " + tutor.getSuspensionEndDate() + ".";
            txtTutorSuspensionEndDate.setText(suspensionMessage);
            txtTutorSuspensionEndDate.setVisibility(View.VISIBLE);
        } else {
            // For permanent suspension
            String suspensionMessage = "Your account has been permanently suspended.\nYou can no longer use the application.";
            txtTutorSuspensionStatus.setText(suspensionMessage);
            txtTutorSuspensionStatus.setVisibility(View.VISIBLE);
        }

        // Set up the Log Out button
        Button btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform log out action here (e.g., clear user session, navigate back to login)
                logout();
            }
        });
    }

    private void displayTutorDetails(Tutor tutor) {
        // Display tutor details and enable actions
        findViewById(R.id.textView7).setVisibility(View.VISIBLE);
        findViewById(R.id.printID).setVisibility(View.VISIBLE);
        findViewById(R.id.printEmail).setVisibility(View.VISIBLE);
        findViewById(R.id.printFirstName).setVisibility(View.VISIBLE);
        findViewById(R.id.printLastName).setVisibility(View.VISIBLE);
        findViewById(R.id.printEducationLevel).setVisibility(View.VISIBLE);
        findViewById(R.id.printNativeLanguage).setVisibility(View.VISIBLE);
        findViewById(R.id.printDescription).setVisibility(View.VISIBLE);
        findViewById(R.id.btnLogOut).setVisibility(View.VISIBLE);
        findViewById(R.id.btnEditProfile).setVisibility(View.VISIBLE);
        findViewById(R.id.btnViewTopics).setVisibility(View.VISIBLE);

        // Display tutor's details
        printID.setText("Account ID: " + tutor.getID());
        printEmail.setText("Email: " + tutor.getEmail());
        printFirstName.setText("First name: " + tutor.getFirstName());
        printLastName.setText("Last name: " + tutor.getLastName());
        printEducationLevel.setText("Education Level: " + tutor.getEducationLevel());
        printNativeLanguage.setText("Native Language: " + tutor.getNativeLanguage());
        printDescription.setText("Description: " + tutor.getDescription());

        // Set up the Log Out button
        Button btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform log out action here (e.g., clear user session, navigate back to login)
                logout();
            }
        });

        // Set up the Edit Profile button click listener
        Button btnEditProfile = findViewById(R.id.btnEditProfile);
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to TutorProfileActivity for editing the tutor's profile
                Intent intent = new Intent(TutorWelcome.this, TutorProfileActivity.class);
                intent.putExtra("TUTOR", tutor); // Pass the tutor object to the profile activity
                startActivityForResult(intent, REQUEST_EDIT_PROFILE);
            }
        });

        // Set up the View Topics button click listener
        Button btnViewTopics = findViewById(R.id.btnViewTopics);
        btnViewTopics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to TutorViewTopics activity
                Intent intent = new Intent(TutorWelcome.this, TutorViewTopics.class);
                intent.putExtra("TUTOR", tutor); // Pass the tutor object to the view topics activity
                startActivity(intent);
            }
        });
    }

    private void logout() {
        // Perform any necessary log out actions (e.g., clear user session, reset data, etc.)
        Intent intent = new Intent(TutorWelcome.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish(); // Finish this activity to prevent the user from coming back via the back button
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_EDIT_PROFILE && resultCode == RESULT_OK && data != null) {
            // Check if the data contains the updated tutor object
            if (data.hasExtra("UPDATED_TUTOR")) {
                // Get the updated tutor object from the intent
                Tutor updatedTutor = (Tutor) data.getSerializableExtra("UPDATED_TUTOR");

                if (updatedTutor != null) {
                    // Update the tutor object with the new data
                    tutor = updatedTutor; // Update the local tutor object with the updated one

                    // Update the UI with the new data
                    printEducationLevel.setText("Education Level: " + tutor.getEducationLevel());
                    printNativeLanguage.setText("Native Language: " + tutor.getNativeLanguage());
                    printDescription.setText("Description: " + tutor.getDescription());

                    // Check if the tutor is suspended and show the appropriate message
                    if (tutor.isSuspended()) {
                        showSuspendedMessage(tutor);
                    }
                }
            }
        }
    }
}
