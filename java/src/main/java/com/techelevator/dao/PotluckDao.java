package com.techelevator.dao;

import com.techelevator.model.Friend;
import com.techelevator.model.Potluck;

import java.util.List;

public interface PotluckDao {
    List<Potluck> getAllPotlucks(int userid);

    Potluck createPotluck(Potluck potluck);

    List<Friend> saveFriends(int userid, Friend[] friends);


}
