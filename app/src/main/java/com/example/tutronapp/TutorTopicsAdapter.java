package com.example.tutronapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TutorTopicsAdapter extends RecyclerView.Adapter<TutorTopicsAdapter.TopicViewHolder> {

    private List<TutorTopics> topicsList;

    public TutorTopicsAdapter(List<TutorTopics> topicsList) {
        this.topicsList = topicsList;
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
        holder.tvYearsOfExperience.setText(String.valueOf(topic.getYearsOfExperience()));
        holder.tvDescription.setText(topic.getBriefDescription());
    }

    @Override
    public int getItemCount() {
        return topicsList.size();
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView tvTopicName, tvYearsOfExperience, tvDescription;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTopicName = itemView.findViewById(R.id.tvTopicName);
            tvYearsOfExperience = itemView.findViewById(R.id.tvYearsOfExperience);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}
