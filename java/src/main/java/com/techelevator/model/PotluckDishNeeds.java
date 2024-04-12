package com.techelevator.model;

public class PotluckDishNeeds {
    private int potluck_id;
    private int dish_category_id;
    private String dish_category;
    private int dish_serving_count_needed;
    private int dish_serving_count_filled;

    public PotluckDishNeeds() {}

    public PotluckDishNeeds(int potluckId, int dishCategoryId, String dishCategory, int dishServingCountNeeded, int dishServingCountFilled) {

        this.potluck_id = potluckId;
        this.dish_category_id = dishCategoryId;
        this.dish_category = dishCategory;
        this.dish_serving_count_needed = dishServingCountNeeded;
        this.dish_serving_count_filled = dishServingCountFilled;
    }

    public int getPotluck_id() {
        return potluck_id;
    }

    public int getDish_category_id() {
        return dish_category_id;
    }

    public String getDish_category() {
        return dish_category;
    }

    public int getDish_serving_count_needed() {
        return dish_serving_count_needed;
    }

    public int getDish_serving_count_filled() {
        return dish_serving_count_filled;
    }

    public void setPotluck_id(int potluckId) {
        this.potluck_id = potluckId;
    }
    public void setDish_category_id(int dishCategoryId) {
        this.dish_category_id = dishCategoryId;
    }


    public void setDish_category(String dishCategory) {
        this.dish_category = dishCategory;
    }

    public void setDish_serving_count_needed(int dishServingCountNeeded) {
        this.dish_serving_count_needed = dishServingCountNeeded;
    }


    public void setDish_serving_count_filled(int dishServingCountFilled) {
        this.dish_serving_count_filled = dishServingCountFilled;
    }

}
