package com.techelevator.model;

public class PotluckUserDish {
    private int potluck_id;
    private int user_id;
    private int dish_id;


    public PotluckUserDish() {}

    public PotluckUserDish(int potluckId, int userId, int dishId) {
        this.potluck_id = potluckId;
        this.user_id = userId;
        this.dish_id = dishId;
    }

    public int getPotluck_id() {
        return potluck_id;
    }

    public int getUser_id() {
        return user_id;
    }
    public int getDish_id() {
        return dish_id;
    }

    public void setPotluck_id(int potluckId) {
        this.potluck_id = potluckId;
    }

    public void setUser_id(int userId) {
        this.user_id = userId;
    }
    public void setDish_id(int dishId) {
        this.dish_id = dishId;
    }
}
