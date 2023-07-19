package com.example.tutronapp;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends User {

    private String address;
    private String cardNumber;

    private List<PurchaseRequest> purchaseRequests;
    private Map<Integer, Float> tutorRatings; // Tutor ID -> Rating


    public Student(int id, String email, String password, String firstName, String lastName) {
        super(id, email, password, firstName, lastName);
        this.purchaseRequests = new ArrayList<>();
        this.tutorRatings = new HashMap<>();
    }

    // Additional getters and setters for address and cardNumber
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // Additional methods specific to Student class (if any)

    // Additional getters and setters for address, cardNumber, and other fields (if any)

    public List<PurchaseRequest> getPurchaseRequests() {
        return purchaseRequests;
    }

    public void addPurchaseRequest(PurchaseRequest purchaseRequest) {
        purchaseRequests.add(purchaseRequest);
    }

    public Map<Integer, Float> getTutorRatings() {
        return tutorRatings;
    }

    public void addTutorRating(int tutorId, float rating) {
        tutorRatings.put(tutorId, rating);
    }

    public void removeTutorRating(int tutorId) {
        tutorRatings.remove(tutorId);
    }

}

