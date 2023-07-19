/*package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class StudentViewTopics extends AppCompatActivity {

    private static final int REQUEST_VIEW_LESSONS = 1;
    private Student student;
    private List<Tutor> nonSuspendedTutors; // You need to populate this list with non-suspended tutors

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_lessons);

        // Get the student object from the intent
        student = (Student) getIntent().getSerializableExtra("STUDENT");

        // ... (code to get non-suspended tutors and lessons based on search criteria)

        // Example: Display lessons offered by non-suspended tutors in a ListView
        ListView listViewLessons = findViewById(R.id.listViewLessons);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getLessonNames()); // Update getLessonNames() with actual data
        listViewLessons.setAdapter(adapter);

        // Set item click listener to view lesson details and submit purchase request
        listViewLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Get the selected lesson and tutor
                Lesson selectedLesson = getSelectedLesson(position);
                Tutor selectedTutor = nonSuspendedTutors.get(position);

                if (selectedLesson != null && selectedTutor != null) {
                    // Navigate to the lesson details screen and pass the selected lesson and tutor objects
                    Intent intent = new Intent(StudentViewLessons.this, LessonDetailsActivity.class);
                    intent.putExtra("SELECTED_LESSON", selectedLesson);
                    intent.putExtra("SELECTED_TUTOR", selectedTutor);
                    startActivityForResult(intent, REQUEST_VIEW_LESSONS); // Use a different request code to distinguish between this and the next startActivityForResult call
                } else {
                    Toast.makeText(StudentViewLessons.this, "Error: Lesson or tutor not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // ... (other methods to retrieve lessons, tutors, etc.)

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_VIEW_LESSONS && resultCode == RESULT_OK && data != null) {
            // Check if the data contains the selected lesson and tutor
            if (data.hasExtra("SELECTED_LESSON") && data.hasExtra("SELECTED_TUTOR")) {
                // Get the selected lesson and tutor from the intent
                Lesson selectedLesson = (Lesson) data.getSerializableExtra("SELECTED_LESSON");
                Tutor selectedTutor = (Tutor) data.getSerializableExtra("SELECTED_TUTOR");

                // Check if the selectedLesson and selectedTutor are not null
                if (selectedLesson != null && selectedTutor != null) {
                    // Create a purchase request for the selected lesson and tutor
                    PurchaseRequest purchaseRequest = new PurchaseRequest(selectedLesson.getId(), selectedTutor.getID());

                    // Add the purchase request to the student's list
                    student.addPurchaseRequest(purchaseRequest);

                    // Show a message or perform any other action based on the successful purchase request submission
                    // For example, you could show a toast message like "Purchase request submitted successfully."

                    // If you have a database or server, you could save the purchase request there for the tutor to view and approve/reject later.
                }
            }
        } else if (requestCode == REQUEST_PURCHASE && resultCode == RESULT_OK && data != null) {
            // Check if the data contains the selected lesson and tutor after purchase request submission
            if (data.hasExtra("SELECTED_LESSON") && data.hasExtra("SELECTED_TUTOR")) {
                // Get the selected lesson and tutor from the intent
                Lesson selectedLesson = (Lesson) data.getSerializableExtra("SELECTED_LESSON");
                Tutor selectedTutor = (Tutor) data.getSerializableExtra("SELECTED_TUTOR");

                // Check if the selectedLesson and selectedTutor are not null
                if (selectedLesson != null && selectedTutor != null) {
                    // Process the purchase request approval here (you can handle this based on your app's flow)
                    // For simplicity, we assume the purchase request is automatically approved.
                    // In a real app, you might have a separate activity for the Tutor to view and approve/reject requests.

                    // Show a message or perform any other action based on the purchase request approval
                    // For example, you could show a toast message like "Purchase request approved. Lesson purchased successfully."

                    // If you have a database or server, you could update the purchase request status there.
                }
            }
        }
    }

    // Additional methods for searching lessons, viewing lesson details, handling purchase request status, rating tutors, etc.

}*/
