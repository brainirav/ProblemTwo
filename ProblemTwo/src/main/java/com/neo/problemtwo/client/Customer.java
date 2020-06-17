package com.neo.problemtwo.client;

public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private float customerAverageRating;
    private float averageRating;

    public Customer(){}
    public Customer(String id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getCustomerAverageRating() {
        return customerAverageRating;
    }

    public void setCustomerAverageRating(float customerAverageRating) {
        this.customerAverageRating = customerAverageRating;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }
}
