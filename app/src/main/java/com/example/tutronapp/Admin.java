package com.example.tutronapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Admin extends AppCompatActivity {

    private static String adminEmail = "admin@example.com";
    private static String adminPassword = "admin123";
    private DBHandler dbHandler;
    private ComplaintAdapter complaintAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        dbHandler = new DBHandler(this);

        // Views
        // EditText txtDeleteEmail = findViewById(R.id.txtDeleteEmail);
        // Button btnDeleteTutor = findViewById(R.id.btnDeleteTutor);
        Button btnLogout = findViewById(R.id.btnLogout);

        EditText txtSuspendEmail = findViewById(R.id.txtSuspendEmail);
        RadioGroup rgSuspensionType = findViewById(R.id.rgSuspensionType);
        EditText txtSuspensionEndDate = findViewById(R.id.txtSuspensionEndDate);
        Button btnSuspendTutor = findViewById(R.id.btnSuspendTutor);
        Button btnDismissComplaint = findViewById(R.id.btnDismissComplaint);
        EditText txtDismissComplaintId = findViewById(R.id.txtDismissComplaintId);

        btnSuspendTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtSuspendEmail.getText().toString();
                int selectedId = rgSuspensionType.getCheckedRadioButtonId();
                boolean isTemporarySuspension = (selectedId == R.id.rbTemporarySuspension);
                String suspensionEndDate = txtSuspensionEndDate.getText().toString().trim();

                // Verify for an entry
                if (email.isEmpty()) {
                    Toast.makeText(Admin.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Tutor tutor = dbHandler.getTutorByEmail(email);
                if (tutor == null) {
                    Toast.makeText(Admin.this, "Tutor not found", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isTemporarySuspension && suspensionEndDate.isEmpty()) {
                    Toast.makeText(Admin.this, "Please provide the suspension end date", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Update tutor's suspension status and end date (if temporary suspension)
                tutor.setSuspended(true);
                tutor.setTemporarySuspended(isTemporarySuspension);
                if (isTemporarySuspension) {
                    tutor.setSuspensionEndDate(suspensionEndDate);
                } else {
                    tutor.setSuspensionEndDate(""); // Clear the suspension end date for permanent suspension
                }
                dbHandler.updateTutor(tutor);

                txtSuspendEmail.setText("");
                rgSuspensionType.clearCheck();
                txtSuspensionEndDate.setText("");
                txtSuspensionEndDate.setVisibility(isTemporarySuspension ? View.VISIBLE : View.GONE);

                Toast.makeText(Admin.this, "Tutor Suspended Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform logout action here, for example, go back to the login screen or clear session data.
                // You can customize the logout behavior based on your application requirements.
                // For now, I'll just show a toast message for demonstration purposes.
                Toast.makeText(Admin.this, "Logged out as Admin", Toast.LENGTH_SHORT).show();
                finish(); // Close the Admin activity and go back to the previous screen.
            }
        });

        // Display the list of complaints using RecyclerView
        RecyclerView recyclerViewComplaints = findViewById(R.id.recyclerViewComplaints);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewComplaints.setLayoutManager(layoutManager);

        List<Complaint> complaints = dbHandler.getAllComplaints();
        complaintAdapter = new ComplaintAdapter(complaints);
        complaintAdapter.setOnComplaintActionListener(new ComplaintAdapter.OnComplaintActionListener() {
            @Override
            public void onDismiss(Complaint complaint) {
                dismissComplaint(complaint);
            }

            @Override
            public void onSuspend(Complaint complaint) {
                suspendTutor(complaint);
            }

            @Override
            public void onComplaintClicked(Complaint complaint) {
                // Handle click on a complaint (if needed)
                // For now, we'll just show a Toast message for demonstration purposes
                Toast.makeText(Admin.this, "Complaint Clicked: " + complaint.getDescription(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewComplaints.setAdapter(complaintAdapter);

        // Set the listener to toggle the Suspension End Date visibility
        rgSuspensionType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbTemporarySuspension) {
                    // Show the Suspension End Date EditText when Temporary Suspension is selected
                    txtSuspensionEndDate.setVisibility(View.VISIBLE);
                } else {
                    // Hide the Suspension End Date EditText when Permanent Suspension is selected
                    txtSuspensionEndDate.setVisibility(View.GONE);
                }
            }
        });

        btnDismissComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String complaintIdString = txtDismissComplaintId.getText().toString().trim();

                // Verify for an entry
                if (complaintIdString.isEmpty()) {
                    Toast.makeText(Admin.this, "Complaint ID cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Parse the complaint ID to an integer
                int complaintId;
                try {
                    complaintId = Integer.parseInt(complaintIdString);
                } catch (NumberFormatException e) {
                    Toast.makeText(Admin.this, "Invalid Complaint ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Find the complaint by ID
                Complaint complaint = dbHandler.getComplaintById(complaintId);
                if (complaint == null) {
                    Toast.makeText(Admin.this, "Complaint not found", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Dismiss the complaint
                dismissComplaint(complaint);

                // Clear the input field
                txtDismissComplaintId.setText("");

                Toast.makeText(Admin.this, "Complaint Dismissed Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void dismissComplaint(Complaint complaint) {
        // Remove the complaint from the list and update the database
        dbHandler.deleteComplaintById(complaint.getId());
        complaintAdapter.removeComplaint(complaint);
    }

    private void suspendTutor(Complaint complaint) {
        // Suspend the associated tutor and update the database
        String associatedTutorEmail = complaint.getAssociatedTutor();
        if (associatedTutorEmail != null) {
            Tutor associatedTutor = dbHandler.getTutorByEmail(associatedTutorEmail);
            if (associatedTutor != null) {
                associatedTutor.isSuspended();
                dbHandler.updateTutor(associatedTutor);
                complaintAdapter.notifyDataSetChanged();
                Toast.makeText(Admin.this, "Tutor Suspended Successfully", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static boolean adminCredentials(String email, String password) {
        return (adminEmail.equals(email) && adminPassword.equals(password));
    }
}
