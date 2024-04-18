package com.techelevator.controller;


import com.techelevator.dao.EmailService;
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

    private EmailService emailService;

    public PotluckController(PotluckDao potluckDao, UserDao userDao, EmailService emailService) {
        this.potluckDao = potluckDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @RequestMapping(path = "/potlucks", method = RequestMethod.GET)
    public List<Potluck> getAllUserPotlucks(Principal principal) {
        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        return potluckDao.getAllPotlucks(user.getId());
    }

    @RequestMapping(path = "/potluckUsers", method = RequestMethod.GET)
    public List<PotluckUser> getAllPotlucksAndUserTypes(Principal principal) {
        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        return potluckDao.getAllPotlucksAndUserTypes(user.getId());
    }

    @RequestMapping(path = "/duplicatePotluck", method = RequestMethod.POST)
    public Potluck duplicatePotluck(@RequestParam int potluckId, Principal principal) {

        String username = principal.getName();
        int userId = userDao.getUserByUsername(username).getId();
        return potluckDao.duplicatePotluck(potluckId,userId);
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

    @RequestMapping(path = "/potlucks/{potluckId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePotluck(@PathVariable int potluckId, Principal principal) {
        String username = principal.getName();
        int userId = userDao.getUserByUsername(username).getId();

        boolean deleted = potluckDao.deletePotluck(potluckId, userId);
        if (deleted) {
            return ResponseEntity.ok("Potluck deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unable to delete potluck");
        }
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
    public List<Guest> inviteGuests(@RequestBody Guest[] guests, Principal principal) {

        for(Guest guest: guests) {
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setTo(guest.getGuest_email_address());
            emailDetails.setBody("You are invited to a Potluck. Please use the link below, " +
                    "and complete your sign up process and feel free to bring your favorite dish.\n\n" +
                    "All the fun is at http://127.0.0.1:5173/" +
                    "\n\n" +
                    "Hope to see you there soon!\n\n" +
                    "Hurry up and register now.\n" +
                    "Potluck App team!" +
                    "");
            emailDetails.setSubject("Welcome to the Potluck Planner Application, please sign up and have fun!");
            String result = emailService.sendSimpleMail(emailDetails);
            System.out.println(result);
        }
        String username = principal.getName();
        int userid = userDao.getUserByUsername(username).getId();
        return potluckDao.inviteGuests(guests, userid);
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
    public boolean deleteDishNeeds(@RequestParam int potluckId, @RequestParam int dishCategoryId) {
        return potluckDao.deleteDishNeeds(potluckId, dishCategoryId);
    }

    @RequestMapping(path = "/allDishNeeds", method = RequestMethod.DELETE)
    public boolean deleteAllDishNeeds(@RequestParam int potluckId) {
        return potluckDao.deleteAllDishNeeds(potluckId);
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