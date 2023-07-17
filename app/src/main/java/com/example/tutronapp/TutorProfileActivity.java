package com.example.tutronapp;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TutorProfileActivity extends AppCompatActivity {

    // Request code for handling the result when the tutor saves changes
    public static final int REQUEST_EDIT_PROFILE = 1;

    Tutor tutor; // To hold the tutor object

    EditText editEducationLevel, editNativeLanguage, editDescription;
    Button btnSaveChanges;

    // Declare the RecyclerViews and their respective Adapters
    //private RecyclerView rvProfileTopics, rvOfferedTopics;
    //private TopicsAdapter profileTopicsAdapter, offeredTopicsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_profile);

        // Get the tutor object passed from TutorWelcome activity
        tutor = (Tutor) getIntent().getSerializableExtra("TUTOR");

        // Initialize UI components
        editEducationLevel = findViewById(R.id.editEducationLevel);
        editNativeLanguage = findViewById(R.id.editNativeLanguage);
        editDescription = findViewById(R.id.editDescription);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);

        // Populate the EditTexts with the tutor's existing profile data
        editEducationLevel.setText(tutor.getEducationLevel());
        editNativeLanguage.setText(tutor.getNativeLanguage());
        editDescription.setText(tutor.getDescription());

        // Set up the Save Changes button click listener
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save changes to the tutor's profile
                saveChanges();
            }
        });
    }

    private void saveChanges() {
        // Get the edited profile information from the EditTexts
        String newEducationLevel = editEducationLevel.getText().toString();
        String newNativeLanguage = editNativeLanguage.getText().toString();
        String newDescription = editDescription.getText().toString();

        // Create a new Tutor object with the updated data
        Tutor updatedTutor = new Tutor(tutor.getID(), tutor.getEmail(), tutor.getPassword(),
                tutor.getFirstName(), tutor.getLastName(), newEducationLevel, newNativeLanguage, newDescription);
        // Set the suspended status and suspension end date from the original tutor object
        updatedTutor.setSuspended(tutor.isSuspended());
        updatedTutor.setTemporarySuspended(tutor.isTemporarySuspended());
        updatedTutor.setSuspensionEndDate(tutor.getSuspensionEndDate());

        // Return the updated tutor object to TutorWelcome activity
        Intent intent = new Intent();
        intent.putExtra("UPDATED_TUTOR", updatedTutor);
        setResult(RESULT_OK, intent);
        finish(); // Close the activity and return to TutorWelcome activity
    }



}
