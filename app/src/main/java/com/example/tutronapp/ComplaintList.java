package com.example.tutronapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ComplaintList extends AppCompatActivity {

    private DBHandler dbHandler;
    private ComplaintAdapter complaintAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_list);

        dbHandler = new DBHandler(this);

        // Display the list of complaints
        RecyclerView recyclerViewComplaints = findViewById(R.id.recyclerViewComplaints);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewComplaints.setLayoutManager(layoutManager);

        List<Complaint> complaints = dbHandler.getAllComplaints();
        complaintAdapter = new ComplaintAdapter(complaints);
        recyclerViewComplaints.setAdapter(complaintAdapter);
    }

    public void onDismiss(Complaint complaint) {
        // Handle the dismissal action here
        // For example, you can delete the complaint from the database
        // and remove it from the complaints list in the adapter
        dbHandler.deleteComplaintById(complaint.getId());
        complaintAdapter.removeComplaint(complaint);
    }

    public void onSuspend(Complaint complaint) {
        // Handle the suspension action here
        // For example, you can update the suspension status in the database
        // and update the complaint in the complaints list in the adapter
        boolean isSuspended = !complaint.isSuspended();
        String suspensionEndDate = ""; // need to set the suspension end date here (if applicable)
        dbHandler.updateComplaintStatus(complaint.getId(), isSuspended, true, suspensionEndDate);
        complaint.setSuspended(isSuspended);
        complaint.setTemporarySuspension(true);
        complaint.setSuspensionEndDate(suspensionEndDate);
        complaintAdapter.notifyDataSetChanged();
    }
}
