package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.List;

public class TutorProfile extends AppCompatActivity {

    private TextView introductionTextView;
    private ListView topicsRecyclerView;
    private ListView reviewsRecyclerView;
    private CalendarView calendarView;
    private TimePicker timePicker;
    private List<String> lessons;
    private List<String> offeredTopics;

    private TopicListAdapter topicListAdapter;
    private Button btnAddLesson;
    private Button btnRemoveLesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_profile);

        introductionTextView = findViewById(R.id.selfIntroductionTextView);
        topicsRecyclerView = findViewById(R.id.topicsTaughtListView);
        reviewsRecyclerView = findViewById(R.id.reviewsListView);
        calendarView = findViewById(R.id.calendarView);
        timePicker = findViewById(R.id.timePicker);
        btnAddLesson = findViewById(R.id.btnAddLesson);
        btnRemoveLesson = findViewById(R.id.btnRemoveLesson);

        topicListAdapter = new TopicListAdapter(lessons);

        // Retrieve tutor's information from the database or API based on student's selection
        TutorProfileManager tutorProfile = getTutorInfo();

        // Populate the views with tutor's information
        introductionTextView.setText(tutorProfile.getTutorIntro());

        // Set up RecyclerViews with appropriate adapters for topics and reviews
        TopicListAdapter topicsAdapter = new TopicListAdapter(tutorProfile.getTopics());
        topicsRecyclerView.setAdapter(topicsAdapter);

        ReviewsAdapter reviewsAdapter = new ReviewsAdapter(tutorProfile.getReviews());
        reviewsRecyclerView.setAdapter(reviewsAdapter);

        // Set up CalendarView and TimePicker with tutor's available schedule
        calendarView.setDate(tutorProfile.getAvailableDate());
        timePicker.setHour(tutorProfile.getAvailableHour());
        timePicker.setMinute(tutorProfile.getAvailableMinute());

        btnAddLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform add lesson operation
                addLesson();
            }
        });

        btnRemoveLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform remove lesson operation
                removeLesson();
            }
        });
    }

    // Method to retrieve tutor's information from the database or API
    private TutorProfileManager getTutorInfo() {
        // Implement the logic to retrieve the tutor's information here
        // This can involve querying a database or making an API call
        // Return a Tutor object with the relevant information

        return null;
    }

    private void addLesson() {
        // Get the selected topic from the tutor's offered topics
        String selectedTopic = offeredTopics.get(selectedPosition);

        // Create a new lesson object with the selected topic and other relevant details
        Lesson newLesson = new Lesson(selectedTopic, startTime, duration);

        // Add the new lesson to the tutor's profile
        tutorProfile.addLesson(newLesson);

        // Update the database or storage mechanism with the new lesson

        // Update the UI or refresh the lesson list
        refreshLessonList();
    }

    private void removeLesson() {
        // Get the selected lesson from the tutor's lessons
        Lesson selectedLesson = lessons.get(selectedPosition);

        // Remove the selected lesson from the tutor's profile
        tutorProfile.removeLesson(selectedLesson);

        // Update the database or storage mechanism to remove the lesson

        // Update the UI or refresh the lesson list
        refreshLessonList();
    }

    public class TopicListAdapter extends BaseAdapter {
        private List<String> dataList;

        public TopicListAdapter(List<String> dataList) {
            this.dataList = dataList;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater;
                inflater = LayoutInflater.from(TutorProfile.this);
                convertView = inflater.inflate(R.layout.list_item_compliant, parent, false);
            }

            // Get the views within the item layout
            TextView tutorName = convertView.findViewById(R.id.tutorName);
            TextView studentName = convertView.findViewById(R.id.studentName);
            TextView description = convertView.findViewById(R.id.description);
            Button dismiss = convertView.findViewById(R.id.dismissButton);
            dismiss.setTag(position);
            Button suspend = convertView.findViewById(R.id.suspendButton);
            suspend.setTag(position);

            return null; // to be completed


        }
    }

}



