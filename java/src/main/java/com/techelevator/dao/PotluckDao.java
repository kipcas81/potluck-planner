package com.techelevator.dao;

import com.techelevator.model.Friend;
import com.techelevator.model.Guest;
import com.techelevator.model.Potluck;

import java.util.List;

public interface PotluckDao {
    List<Potluck> getAllPotlucks(int userid);

    Potluck createPotluck(Potluck potluck);

    Potluck updatePotluck(Potluck potluck);

    Potluck getPotluckById(int potluckId);

    List<Friend> saveFriends(int userid, Friend[] friends);

    List<Friend> getFriends(int userid);

    List<Guest> inviteGuests(Guest[] guests);

    List<Guest> removeGuests(Guest[] guests);

    List<Guest> getGuests(int potluckId);



}
