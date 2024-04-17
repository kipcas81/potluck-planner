package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DishUser {

    private String username;
    private Dish dish;

    public DishUser() {
    }

    public DishUser(String username, Dish dish) {
        this.username = username;
        this.dish = dish;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Dish getDish() {
        return this.dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
