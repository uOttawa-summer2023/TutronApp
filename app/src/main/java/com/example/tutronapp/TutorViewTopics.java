package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TutorViewTopics extends AppCompatActivity {

    ListView topicsListView;
    List<TutorTopics> allTopicsList; // Replace this with your actual list of available topics

    Tutor tutor; // Reference to the tutor object passed from the previous activity

    private TutorTopicsAdapter tutorTopicsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_view_topics);

        // Get the tutor object passed from the previous activity
        tutor = (Tutor) getIntent().getSerializableExtra("TUTOR");

        topicsListView = findViewById(R.id.topicsListView);

        // Replace the following line with your actual data retrieval logic to get the list of available topics
        // For now, we'll use a dummy list for demonstration purposes.
        allTopicsList = getAvailableTopics();

        ArrayAdapter<TutorTopics> topicsArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allTopicsList);

        // Initialize the TutorTopicsAdapter with the list of available topics and the tutor object
        tutorTopicsAdapter = new TutorTopicsAdapter(allTopicsList, tutor);

        topicsListView.setAdapter(topicsArrayAdapter);



        // Set up the item click listener for the ListView
        topicsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TutorTopics selectedTopic = allTopicsList.get(position);

                // Check if the topic is already offered by the Tutor
                boolean isOffered = tutor.isTopicOffered(selectedTopic);

                if (isOffered) {
                    // If the topic is already offered, remove it from the offered topics list
                    tutor.removeFromOfferedTopics(selectedTopic);
                    Toast.makeText(TutorViewTopics.this, "Topic removed from offered list.", Toast.LENGTH_SHORT).show();
                } else {
                    // If the topic is not offered, add it to the offered topics list
                    int numOfferedTopics = tutor.getNumOfferedTopics();
                    if (numOfferedTopics < 5) {
                        tutor.addToOfferedTopics(selectedTopic);
                        Toast.makeText(TutorViewTopics.this, "Topic added to offered list.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TutorViewTopics.this, "You can offer up to 5 topics.", Toast.LENGTH_SHORT).show();
                    }
                }

                // Update the ListView to reflect the changes
                topicsArrayAdapter.notifyDataSetChanged();
            }
        });

        // Set up the Manage Topics button click listener
        Button btnManageTopics = findViewById(R.id.btnManageTopics);
        btnManageTopics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to TutorManageTopics activity
                Intent intent = new Intent(TutorViewTopics.this, TutorManageTopics.class);
                intent.putExtra("TUTOR", tutor); // Pass the tutor object to the ManageTopics activity
                startActivity(intent);
            }
        });
    }

    // Replace this method with actual data retrieval logic to get the list of available topics
    private List<TutorTopics> getAvailableTopics() {
        List<TutorTopics> topics = new ArrayList<>();

        // Get tutor's profile topics
        List<TutorTopics> profileTopics = tutor.getProfileTopics();
        if (profileTopics != null && !profileTopics.isEmpty()) {
            topics.addAll(profileTopics);
        }

        // Get tutor's offered topics
        List<TutorTopics> offeredTopics = tutor.getOfferedTopics();
        if (offeredTopics != null && !offeredTopics.isEmpty()) {
            topics.addAll(offeredTopics);
        }

        return topics;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Update the ListView to reflect the changes
        tutorTopicsAdapter.updateTopicsList(getAvailableTopics());

        // Show a message if there are no courses in the list
        if (tutorTopicsAdapter.getItemCount() == 0) {
            Toast.makeText(this, "No courses added yet.", Toast.LENGTH_SHORT).show();
        }
    }
}
