package com.example.tutronapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PurchaseRequestAdapter extends RecyclerView.Adapter<PurchaseRequestAdapter.ViewHolder> {
    private List<PurchaseRequest> purchaseRequests;

    public PurchaseRequestAdapter(List<PurchaseRequest> purchaseRequests) {
        this.purchaseRequests = purchaseRequests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_purchase_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PurchaseRequest purchaseRequest = purchaseRequests.get(position);
        holder.topicTextView.setText(purchaseRequest.getTopic());
        holder.studentNameTextView.setText(purchaseRequest.getStudentName());
        holder.dateTextView.setText(purchaseRequest.getDate());
        holder.timeTextView.setText(purchaseRequest.getTime());

        // Handle approval button click
        holder.approveButton.setOnClickListener(v -> {
            purchaseRequest.setApproved(true);
            // Update the purchase request in the storage mechanism

            // Remove the approved purchase request from the list
            purchaseRequests.remove(position);
            notifyItemRemoved(position);
        });

        // Handle rejection button click
        holder.rejectButton.setOnClickListener(v -> {
            // Remove the rejected purchase request from the list
            purchaseRequests.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return purchaseRequests.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView topicTextView;
        public TextView studentNameTextView;
        public TextView dateTextView;
        public TextView timeTextView;
        public Button approveButton;
        public Button rejectButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topicTextView = itemView.findViewById(R.id.topicTextView);
            studentNameTextView = itemView.findViewById(R.id.studentNameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            approveButton = itemView.findViewById(R.id.approveButton);
            rejectButton = itemView.findViewById(R.id.rejectButton);
        }
    }
}
