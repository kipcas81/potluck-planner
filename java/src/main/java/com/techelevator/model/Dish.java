package com.techelevator.model;


public class Dish {
    private int dish_id;
    private String dish_name;
    private String dish_dietary_restrictions;
    private String dish_category;
    private int dish_servings;
    private String dish_recipe;
    private int user_id;

    public Dish() {}

    public Dish(int dishId, String dishName, String dishDietaryRestrictions, String dishCategory, int dishServings, String dishRecipe, int userId) {

        this.dish_id = dishId;
        this.dish_name = dishName;
        this.dish_dietary_restrictions = dishDietaryRestrictions;
        this.dish_category = dishCategory;
        this.dish_servings = dishServings;
        this.dish_recipe = dishRecipe;
        this.user_id = userId;
    }

    public int getDish_id() {
        return dish_id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public String getDish_dietary_restrictions() {
        return dish_dietary_restrictions;
    }

    public String getDish_category() {
        return dish_category;
    }

    public int getDish_servings() {
        return dish_servings;
    }
    public String getDish_recipe() {
        return dish_recipe;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setDish_id(int dishId) {
        this.dish_id = dishId;
    }
    public void setDish_name(String dishName) {
        this.dish_name = dishName;
    }

    public void setDish_dietary_restrictions(String dishDietaryRestrictions) {
        this.dish_dietary_restrictions = dishDietaryRestrictions;
    }

    public void setDish_category(String dishCategory) {
        this.dish_category = dishCategory;
    }

    public void setDish_servings(int dishServings) {
        this.dish_servings = dishServings;
    }

    public void setDish_recipe(String dishRecipe) {
        this.dish_recipe = dishRecipe;
    }

    public void setUser_id(int userId) {
        this.user_id = userId;
    }


}
