package com.example.tutronapp;

import java.util.ArrayList;
import java.util.List;

// Tutor class representing the Tutor's profile
public class TutorProfileManager {

    private String tutorIntro;
    private List<Topic> offeredTopics;//topics offered by tutor.These appear on their profile
    private List<Topic> teachingTopics;//topics tutor is currently teaching...students can view them in searches
    private List<PurchaseRequest> purchaseRequests;
    private boolean isSuspended;

    public TutorProfileManager(String tutorIntro) {
        this.tutorIntro = tutorIntro;
        this.teachingTopics = new ArrayList<>();
        this.offeredTopics = new ArrayList<>();
        this.purchaseRequests = new ArrayList<>();
        this.isSuspended = false;
    }


    public void addTeachingTopic(String name, int yearsOfExperience, String description) {
        Topic topic = new Topic(name, yearsOfExperience, description);
        teachingTopics.add(topic);
    }

    public void removeTeachingTopic(String name) {
        Topic topicToRemove = null;
        for (Topic topic : teachingTopics) {
            if (topic.getName().equals(name)) {
                topicToRemove = topic;
                break;
            }
        }
        if (topicToRemove != null) {
            teachingTopics.remove(topicToRemove);
        }
    }
    public void offerTopic(String topicName) {
        Topic selectedTopic = null;
        for (Topic topic : teachingTopics) {
            if (topic.getName().equals(topicName)) {
                selectedTopic = topic;
                break;
            }
        }

        if (selectedTopic != null && !offeredTopics.contains(selectedTopic)) {
            offeredTopics.add(selectedTopic);
            System.out.println("Topic '" + selectedTopic.getName() + "' offered successfully.");
        } else {
            System.out.println("Error: Topic not found or already offered.");
        }
    }
    public void deleteTopic(String topic) {
        // Remove the topic from both teachingTopics and offered topics
        teachingTopics.remove(topic);
        offeredTopics.remove(topic);
    }

    public void suspendAccount() {
        isSuspended = true;
    }

    public void unsuspendAccount() {
        isSuspended = false;
    }

    public void addPurchaseRequest(PurchaseRequest request) {
        purchaseRequests.add(request);
    }

    public void approvePurchaseRequest(PurchaseRequest request) {
        purchaseRequests.remove(request);
        // Logic to process the approved purchase request
    }

    public void rejectPurchaseRequest(PurchaseRequest request) {
        purchaseRequests.remove(request);
        // Logic to handle the rejected purchase request
    }
    public String getTutorIntro(){
        return tutorIntro;
    }

    public long getAvailableDate() {
        return 0;
    }

    public int getAvailableHour() {
        return 0;
    }

    public int getAvailableMinute() {
        return 0;
    }

    public List<String> getTopics() {
        return null;
    }

    public List<String> getReviews() {
        return null;
    }
}