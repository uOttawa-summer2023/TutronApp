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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_view_topics);

        topicsListView = findViewById(R.id.topicsListView);

        // Replace the following line with your actual data retrieval logic to get the list of available topics
        // For now, we'll use a dummy list for demonstration purposes.
        allTopicsList = getAvailableTopics();

        ArrayAdapter<TutorTopics> topicsArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allTopicsList);
        topicsListView.setAdapter(topicsArrayAdapter);

        topicsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TutorTopics selectedTopic = allTopicsList.get(position);
                // TODO: Implement logic to add the selected topic to the profile or offered topics list.
                // For now, we'll just display a toast message to indicate the selected topic.
                Toast.makeText(TutorViewTopics.this, "Selected topic: " + selectedTopic.getTopicName(), Toast.LENGTH_SHORT).show();
            }
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to the TutorWelcome activity
                Intent intent = new Intent(TutorViewTopics.this, TutorWelcome.class);
                startActivity(intent);
            }
        });
    }

    // Replace this method with actual data retrieval logic to get the list of available topics
    // For now, we'll use a dummy list for demonstration purposes.
    private List<TutorTopics> getAvailableTopics() {
        List<TutorTopics> topics = new ArrayList<>();
        /*topics.add(new TutorTopics("Mathematics"));
        topics.add(new TutorTopics("Science"));
        topics.add(new TutorTopics("English"));
        topics.add(new TutorTopics("History"));*/
        return topics;
    }
}
