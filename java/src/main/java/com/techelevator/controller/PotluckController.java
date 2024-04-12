package com.techelevator.controller;


import com.techelevator.dao.PotluckDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class PotluckController {

    private PotluckDao potluckDao;
    private UserDao userDao;

    public PotluckController(PotluckDao potluckDao, UserDao userDao) {
        this.potluckDao = potluckDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/potlucks", method = RequestMethod.GET)
    public List<Potluck> getAllPotlucks(Principal principal) {

        String username = principal.getName();
        int userid = userDao.getUserByUsername(username).getId();
        return potluckDao.getAllPotlucks(userid);
    }


    @RequestMapping(path = "/potlucks", method = RequestMethod.POST)
    public Potluck createPotluck(@RequestBody Potluck potluck, Principal principal) {

        String username = principal.getName();
        int userid = userDao.getUserByUsername(username).getId();
        potluck.setUserId(userid);
        return potluckDao.createPotluck(potluck);
    }


    @RequestMapping(path = "/potlucks/{potluckId}", method = RequestMethod.PUT)
    public Potluck updatePotluck(@PathVariable("potluckId") int potluckId, @RequestBody Potluck updatedPotluck) {
        return potluckDao.updatePotluck(potluckId, updatedPotluck);

    }

    @RequestMapping(path = "/potlucks/{potluckId}", method = RequestMethod.GET)
    public Potluck getPotluckById(@PathVariable("potluckId") int potluckId) {
        return potluckDao.getPotluckById(potluckId);

    }

    @RequestMapping(path = "/friends", method = RequestMethod.POST)
    public List<Friend> saveFriends(@RequestBody Friend[] friends, Principal principal) {
        String username = principal.getName();
        int userid = userDao.getUserByUsername(username).getId();
        return potluckDao.saveFriends(userid, friends);
    }

    @RequestMapping(path = "/friends", method = RequestMethod.GET)
    public List<Friend> saveFriends(Principal principal) {
        String username = principal.getName();
        int userid = userDao.getUserByUsername(username).getId();
        return potluckDao.getFriends(userid);
    }

    @RequestMapping(path = "/guests", method = RequestMethod.POST)
    public List<Guest> inviteGuests(@RequestBody Guest[] guests) {
        return potluckDao.inviteGuests(guests);
    }

    @RequestMapping(path = "/guests", method = RequestMethod.DELETE)
    public List<Guest> removeGuests(@RequestBody Guest[] guests) {
        return potluckDao.removeGuests(guests);
    }

    @RequestMapping(path = "/guests", method = RequestMethod.GET)
    public List<Guest> getGuests(@RequestParam int potluckId) {
        return potluckDao.getGuests(potluckId);
    }

    @RequestMapping(path = "/dishNeeds", method = RequestMethod.POST)
    public PotluckDishNeeds addDishNeeds(@RequestParam int potluckId, @RequestBody PotluckDishNeeds potluckDishNeeds) {
        return potluckDao.addDishNeeds(potluckId, potluckDishNeeds);
    }

    @RequestMapping(path = "/dishNeeds", method = RequestMethod.PUT)
    public PotluckDishNeeds updateDishNeeds(@RequestParam int potluckId, @RequestBody PotluckDishNeeds potluckDishNeeds) {
        return potluckDao.updateDishNeeds(potluckId, potluckDishNeeds);
    }

    @RequestMapping(path = "/dishNeeds", method = RequestMethod.GET)
    public List<PotluckDishNeeds> viewDishNeeds(@RequestParam int potluckId) {
        return potluckDao.viewDishNeeds(potluckId);
    }
    @RequestMapping(path = "/dishNeeds", method = RequestMethod.DELETE)
    public boolean deleteDishNeeds(@RequestParam int potluckId) {
        return potluckDao.deleteDishNeeds(potluckId);
    }

    @RequestMapping(path = "/dish", method = RequestMethod.POST)
    public Dish bringDish(Principal principal, @RequestParam int potluckId, @RequestBody Dish dish) {
        String username = principal.getName();
        int userid = userDao.getUserByUsername(username).getId();
        return potluckDao.bringDish(userid, potluckId, dish);
    }

    @RequestMapping(path = "/dish", method = RequestMethod.PUT)
    public Dish updateDish(@RequestParam int dishId, @RequestBody Dish dish) {
        return potluckDao.updateDish(dishId, dish);
    }

    @RequestMapping(path = "/dish", method = RequestMethod.GET)
    public Dish getDish(@RequestParam int dishId) {
        return potluckDao.getDish(dishId);
    }

    @RequestMapping(path = "/dish", method = RequestMethod.DELETE)
    public boolean deleteDish(@RequestParam int potluckId, @RequestParam int dishId) {
        return potluckDao.deleteDish(potluckId, dishId);
    }

    @RequestMapping(path = "/allDishes", method = RequestMethod.GET)
    public List<Dish> getAllDishesByPotluckId(@RequestParam int potluckId) {
        return potluckDao.getAllDishesByPotluckId(potluckId);
    }

}