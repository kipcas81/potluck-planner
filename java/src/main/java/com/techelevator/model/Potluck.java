package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class Potluck {
    private int id;
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String location;
    private List<String> dietaryRestrictions;
    private List<String> invitedGuests;
    private boolean isRecurring;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(List<String> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public List<String> getInvitedGuests() {
        return invitedGuests;
    }

    public void setInvitedGuests(List<String> invitedGuests) {
        this.invitedGuests = invitedGuests;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }
}