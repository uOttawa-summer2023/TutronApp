package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Intent;


public class TutorManageTopics extends AppCompatActivity {

    private Tutor tutor; // Reference to the tutor object passed from the previous activity

    EditText etTopicName, etYearsOfExperience, etBriefDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_manage_topics);

        // Get the tutor object passed from the previous activity
        tutor = (Tutor) getIntent().getSerializableExtra("TUTOR");

        etTopicName = findViewById(R.id.etTopicName);
        etYearsOfExperience = findViewById(R.id.etYearsOfExperience);
        etBriefDescription = findViewById(R.id.etBriefDescription);

        Button btnAddToProfile = findViewById(R.id.btnAddToProfile);
        btnAddToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToProfile();
            }
        });

        Button btnAddToOfferedTopics = findViewById(R.id.btnAddToOfferedTopics);
        btnAddToOfferedTopics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToOfferedTopics();
            }
        });

        Button btnRemoveFromProfile = findViewById(R.id.btnRemoveFromProfile);
        btnRemoveFromProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFromProfile();
            }
        });

        Button btnRemoveFromOfferedTopics = findViewById(R.id.btnRemoveFromOfferedTopics);
        btnRemoveFromOfferedTopics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFromOfferedTopics();
            }
        });
    }

    private void addToProfile() {
        String topicName = etTopicName.getText().toString().trim();
        String yearsOfExperienceStr = etYearsOfExperience.getText().toString().trim();
        String briefDescription = etBriefDescription.getText().toString().trim();

        if (topicName.isEmpty() || yearsOfExperienceStr.isEmpty() || briefDescription.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
        } else {
            int yearsOfExperience = Integer.parseInt(yearsOfExperienceStr);
            TutorTopics newTopic = new TutorTopics(topicName, yearsOfExperience, briefDescription);

            // Add the topic to the tutor's profile
            tutor.addTopicToProfile(newTopic);

            // Show a success message
            Toast.makeText(this, "Topic added to profile.", Toast.LENGTH_SHORT).show();

            // Update the topics list in TutorViewTopics activity
            Intent intent = new Intent(TutorManageTopics.this, TutorViewTopics.class);
            intent.putExtra("TUTOR", tutor); // Pass the updated tutor object
            startActivity(intent);
        }
    }

    private void addToOfferedTopics() {
        String topicName = etTopicName.getText().toString().trim();
        String yearsOfExperienceStr = etYearsOfExperience.getText().toString().trim();
        String briefDescription = etBriefDescription.getText().toString().trim();

        if (topicName.isEmpty() || yearsOfExperienceStr.isEmpty() || briefDescription.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
        } else {
            int yearsOfExperience = Integer.parseInt(yearsOfExperienceStr);
            TutorTopics newTopic = new TutorTopics(topicName, yearsOfExperience, briefDescription);

            // Add the topic to the tutor's offered topics list
            tutor.addToOfferedTopics(newTopic);

            // Show a success message
            Toast.makeText(this, "Topic added to offered topics list.", Toast.LENGTH_SHORT).show();

            // Update the topics list in TutorViewTopics activity
            Intent intent = new Intent(TutorManageTopics.this, TutorViewTopics.class);
            intent.putExtra("TUTOR", tutor); // Pass the updated tutor object
            startActivity(intent);
        }
    }


    private void removeFromProfile() {
        // Get the topic details from the EditText fields
        String topicName = etTopicName.getText().toString();

        // Find the TutorTopics object with the given topic name in the profile topics list
        TutorTopics topicToRemove = tutor.findTopicInProfile(topicName);

        // Remove the topic from the tutor's profile
        if (topicToRemove != null) {
            tutor.removeTopicFromProfile(topicToRemove);
            // Show a success message
            Toast.makeText(this, "Topic removed from profile.", Toast.LENGTH_SHORT).show();
        } else {
            // Show an error message if the topic was not found in the profile
            Toast.makeText(this, "Topic not found in profile.", Toast.LENGTH_SHORT).show();
        }
    }

    private void removeFromOfferedTopics() {
        // Get the topic details from the EditText fields
        String topicName = etTopicName.getText().toString();

        // Find the TutorTopics object with the given topic name in the offered topics list
        TutorTopics topicToRemove = tutor.findTopicInOfferedTopics(topicName);

        // Remove the topic from the tutor's offered topics list
        if (topicToRemove != null) {
            tutor.removeFromOfferedTopics(topicToRemove);
            // Show a success message
            Toast.makeText(this, "Topic removed from offered topics list.", Toast.LENGTH_SHORT).show();
        } else {
            // Show an error message if the topic was not found in the offered topics list
            Toast.makeText(this, "Topic not found in offered topics list.", Toast.LENGTH_SHORT).show();
        }
    }
}

