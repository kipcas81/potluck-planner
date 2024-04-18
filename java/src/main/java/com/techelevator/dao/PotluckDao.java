package com.techelevator.dao;

import com.techelevator.model.*;

import java.util.List;

public interface PotluckDao {

    PotluckDishNeeds addDishNeeds(int potluckId, PotluckDishNeeds potluckDishNeeds);

    PotluckDishNeeds updateDishNeeds(int potluckId, PotluckDishNeeds potluckDishNeeds);

    List<PotluckDishNeeds> viewDishNeeds(int potluckId);

    boolean deleteDishNeeds(int potluckId, int dishCategoryId);
    boolean deleteAllDishNeeds(int potluckId);

    Dish bringDish(int userid, int potluckId, Dish dish);

    Dish updateDish(int dishId, Dish dish);

    Dish getDish(int dishId);

    boolean deleteDish(int potluckId, int dishId);

    List<Dish> getAllDishesByPotluckId(int potluckId);

    List<Potluck> getAllPotlucks(int userid);
    List<PotluckUser> getAllPotlucksAndUserTypes(int userid);

    Potluck duplicatePotluck(int potluckId, int userId);
    Potluck createPotluck(Potluck potluck);

    boolean deletePotluck(int potluckId, int userId);

    Potluck updatePotluck(int potluckId, Potluck potluck);

    Potluck getPotluckById(int potluckId);

    List<Friend> saveFriends(int userid, Friend[] friends);

    List<Friend> getFriends(int userid);

    List<Guest> inviteGuests(Guest[] guests, int userId);

    List<Guest> removeGuests(Guest[] guests);

    List<Guest> getGuests(int potluckId);

    boolean linkRegisteredGuestsToPotluckUserTable(User user);



}
