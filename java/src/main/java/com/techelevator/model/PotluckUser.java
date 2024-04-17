package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PotluckUser {

    private String userType;
    private Potluck potluck;

    public PotluckUser() {
    }

    public PotluckUser(String userType, Potluck potluck) {
        this.userType = userType;
        this.potluck = potluck;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Potluck getPotluck() {
        return this.potluck;
    }

    public void setPotluck(Potluck potluck) {
        this.potluck = potluck;
    }
}
