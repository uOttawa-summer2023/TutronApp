package com.example.tutronapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Admin extends AppCompatActivity {

    private ListView inboxListView;
    private ArrayAdapter<String> inboxAdapter;
    private List<String> inboxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        inboxListView = findViewById(R.id.inboxListView);
        inboxList = new ArrayList<>();

        // Populate the inbox list with sample data
        inboxList.add("Complaint 1");
        inboxList.add("Complaint 2");
        inboxList.add("Complaint 3");

        inboxAdapter = new ArrayAdapter<>(this, R.layout.list_item_compliant, R.id.studentName, inboxList);
        inboxListView.setAdapter(inboxAdapter);

        inboxListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click here (e.g., show details of the selected complaint)
                Toast.makeText(Admin.this, "Clicked on complaint: " + inboxList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void dismissComplaint(View view) {
        // Handle dismiss complaint logic here
        // Access the complaint information from the clicked item's view
        View parent = (View) view.getParent().getParent();
        TextView studentNameTextView = parent.findViewById(R.id.studentName);
        String studentName = studentNameTextView.getText().toString();

        // Perform the necessary operations (e.g., update database, remove item from list)
        inboxList.remove(studentName);
        inboxAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Complaint dismissed.", Toast.LENGTH_SHORT).show();
    }

    public void suspendTutor(View view) {
        // Handle suspend tutor logic here
        // Access the complaint information from the clicked item's view
        View parent = (View) view.getParent().getParent();
        TextView studentNameTextView = parent.findViewById(R.id.studentName);
        String studentName = studentNameTextView.getText().toString();

        // Perform the necessary operations (e.g., update database)
        // Show a success message
        Toast.makeText(this, "Tutor suspended for complaint from: " + studentName, Toast.LENGTH_SHORT).show();
    }
}
