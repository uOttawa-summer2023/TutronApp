package com.example.tutronapp;

import java.io.Serializable;

public class TutorTopics implements Serializable {

    private int topicId;
    private String topicName;
    private int yearsOfExperience;
    private String briefDescription;

    public TutorTopics(String topicName, int yearsOfExperience, String briefDescription) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.yearsOfExperience = yearsOfExperience;
        this.briefDescription = briefDescription;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String name) {
        this.topicName = topicName;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }
}
