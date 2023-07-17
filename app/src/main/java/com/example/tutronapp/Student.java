package com.example.tutronapp;

import java.util.ArrayList;

public class Student extends User {

    private String address;
    private String cardNumber;


    public Student(int id, String email, String password, String firstName, String lastName) {
        super(id, email, password, firstName, lastName);
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

}

