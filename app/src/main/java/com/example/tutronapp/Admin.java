package com.example.tutronapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class Admin extends AppCompatActivity {

    private ListView complaintsListView;
    private List<Complaints> complaintsList;
    private CustomAdapter adapter;
    private DatabaseHelper databaseHelper;
    private Button dismiss,suspend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.addComplaint(new Complaints("John", "Kenyi", "Few lectures attended"));
        databaseHelper.addComplaint(new Complaints("Alice", "Mohammad", "Late all the time"));
        databaseHelper.addComplaint(new Complaints("Bob", "Dillon", "No explanation"));

        complaintsListView = findViewById(R.id.inboxListView);
        complaintsList = new ArrayList<>();

       complaintsList = loadComplaints();
        adapter = new CustomAdapter(complaintsList);
        complaintsListView.setAdapter(adapter);


       /*View includedLayout;
        dismiss = includedLayout.findViewById(R.id.dismissButton);
        suspend = includedLayout.findViewById(R.id.suspendButton);

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {dismissComplaint(v);
            }
        });
        suspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {suspendTutor(v);
            }
        });*/





        // Insert each complaint into the database
       // for (Complaints complaint : complaintsList) {
            //databaseHelper.addComplaint(complaint);
       // }




        // Initialize the adapter with a list of complaint details();


        //complaintsListView.setOnItemClickListener((parent, view, position, id) -> {
            //complaintsList.remove(position);
            //adapter.notifyDataSetChanged();
            //Toast.makeText(Admin.this, "Complaint handled", Toast.LENGTH_SHORT).show();
        //});
    }
   public void dismissComplaint(View view) {
       Toast.makeText(this,view.getTag().toString(), Toast.LENGTH_SHORT).show();

       int position = (int) view.getTag();


        // Delete the item from the list
        complaintsList.remove(position);

        // Notify the adapter that the data set has changed
        adapter.notifyDataSetChanged();

    }

    public void suspendTutor(View view) {
        // Get the position of the clicked item in the ListView

        int position = (int) view.getTag();

        // Delete the item from the list
        complaintsList.remove(position);

        // Notify the adapter that the data set has changed
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
    }
    private class CustomAdapter extends BaseAdapter {
        private List<Complaints> dataList;

        public CustomAdapter(List<Complaints> dataList) {
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
                LayoutInflater inflater = LayoutInflater.from(Admin.this);
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


            // Bind the data to the views
            Complaints complaint = dataList.get(position);
            tutorName.setText(complaint.getTutorName());
            studentName.setText(complaint.getStudentName());
            description.setText(complaint.getDescription());
            dismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissComplaint(v);
                }
            });
            suspend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    suspendTutor(v);
                }
            });

            return convertView;
        }

    }

    private List<Complaints> loadComplaints() {
        return databaseHelper.getAllComplaints();
    }
}
