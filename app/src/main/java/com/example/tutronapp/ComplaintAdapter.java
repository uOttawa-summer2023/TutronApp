package com.example.tutronapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder> {

    private List<Complaint> complaints;
    private OnComplaintActionListener listener;

    public ComplaintAdapter(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    @NonNull
    @Override
    public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_complaint, parent, false);
        return new ComplaintViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position) {
        Complaint complaint = complaints.get(position);
        holder.bind(complaint);

        // Handle item click actions
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onComplaintClicked(complaint);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return complaints.size();
    }

    public void setOnComplaintActionListener(OnComplaintActionListener listener) {
        this.listener = listener;
    }

    public void removeComplaint(Complaint complaint) {
        complaints.remove(complaint);
        notifyDataSetChanged();
    }

    // Inner interface for handling complaint actions
    public interface OnComplaintActionListener {
        void onDismiss(Complaint complaint);

        void onSuspend(Complaint complaint);

        void onComplaintClicked(Complaint complaint);
    }


    public static class ComplaintViewHolder extends RecyclerView.ViewHolder {

        private TextView txtComplaintDescription;
        private TextView txtAssociatedTutor;
        private TextView txtSuspendedStatus;

        public ComplaintViewHolder(@NonNull View itemView) {
            super(itemView);
            txtComplaintDescription = itemView.findViewById(R.id.txtComplaintDescription);
            txtAssociatedTutor = itemView.findViewById(R.id.txtAssociatedTutor);
            txtSuspendedStatus = itemView.findViewById(R.id.txtSuspendedStatus);
        }

        public void bind(Complaint complaint) {
            txtComplaintDescription.setText(complaint.getDescription());
            txtAssociatedTutor.setText("Associated Tutor: " + complaint.getAssociatedTutor());
            txtSuspendedStatus.setText(complaint.isSuspended() ? "Suspended" : "Not Suspended");
        }
    }
}
