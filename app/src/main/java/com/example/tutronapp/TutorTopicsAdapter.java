package com.example.tutronapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TutorTopicsAdapter extends RecyclerView.Adapter<TutorTopicsAdapter.TopicViewHolder> {

    private List<TutorTopics> topicsList;
    private Tutor tutor;

    public TutorTopicsAdapter(List<TutorTopics> topicsList, Tutor tutor) {
        this.topicsList = topicsList;
        this.tutor = tutor;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topics, parent, false);
        return new TopicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        TutorTopics topic = topicsList.get(position);
        holder.tvTopicName.setText(topic.getTopicName());
        holder.tvDescription.setText(topic.getBriefDescription());
        holder.tvYearsOfExperience.setText("Years of Experience: " + topic.getYearsOfExperience());

        boolean isOffered = tutor.isTopicOffered(topic);
        boolean isProfile = tutor.isTopicInProfile(topic);

        holder.btnAddToProfile.setVisibility(isProfile ? View.GONE : View.VISIBLE);
        holder.btnAddToOfferedTopics.setVisibility(isOffered ? View.GONE : View.VISIBLE);
        holder.btnRemoveFromProfile.setVisibility(isProfile ? View.VISIBLE : View.GONE);
        holder.btnRemoveFromOfferedTopics.setVisibility(isOffered ? View.VISIBLE : View.GONE);

        // Set click listeners for the Add and Remove buttons
        holder.btnAddToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTopicToProfile(topic);
            }
        });

        holder.btnAddToOfferedTopics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTopicToOfferedTopics(topic);
            }
        });

        holder.btnRemoveFromProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeTopicFromProfile(topic);
            }
        });

        holder.btnRemoveFromOfferedTopics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeTopicFromOfferedTopics(topic);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicsList.size();
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView tvTopicName, tvYearsOfExperience, tvDescription;
        Button btnAddToProfile, btnAddToOfferedTopics, btnRemoveFromProfile, btnRemoveFromOfferedTopics;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTopicName = itemView.findViewById(R.id.tvTopicName);
            tvYearsOfExperience = itemView.findViewById(R.id.tvYearsOfExperience);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            btnAddToProfile = itemView.findViewById(R.id.btnAddToProfile);
            btnAddToOfferedTopics = itemView.findViewById(R.id.btnAddToOfferedTopics);
            btnRemoveFromProfile = itemView.findViewById(R.id.btnRemoveFromProfile);
            btnRemoveFromOfferedTopics = itemView.findViewById(R.id.btnRemoveFromOfferedTopics);
        }
    }

    private void addTopicToProfile(TutorTopics topic) {
        // Check if the topic is already in the tutor's profile
        if (tutor.findTopicInProfile(topic.getTopicName()) == null) {
            // If not, add it to the profile
            tutor.addTopicToProfile(topic);
            notifyDataSetChanged();
        }
    }

    private void addTopicToOfferedTopics(TutorTopics topic) {
        // Check if the topic is already offered by the tutor
        if (tutor.findTopicInOfferedTopics(topic.getTopicName()) == null) {
            // If not, add it to the offered topics list
            tutor.addToOfferedTopics(topic);
            notifyDataSetChanged();
        }
    }

    private void removeTopicFromProfile(TutorTopics topic) {
        // Check if the topic is in the tutor's profile
        if (tutor.findTopicInProfile(topic.getTopicName()) != null) {
            // If yes, remove it from the profile
            tutor.removeTopicFromProfile(topic);
            notifyDataSetChanged();
        }
    }

    private void removeTopicFromOfferedTopics(TutorTopics topic) {
        // Check if the topic is offered by the tutor
        if (tutor.findTopicInOfferedTopics(topic.getTopicName()) != null) {
            // If yes, remove it from the offered topics list
            tutor.removeFromOfferedTopics(topic);
            notifyDataSetChanged();
        }
    }

    // Method to update the list of topics
    public void updateTopicsList(List<TutorTopics> updatedList) {
        this.topicsList = updatedList;
        notifyDataSetChanged();
    }

    // Method to check if a topic list is empty
    public boolean isEmpty() {
        return topicsList.isEmpty();
    }
}
