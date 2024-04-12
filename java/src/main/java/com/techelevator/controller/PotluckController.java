package com.techelevator.controller;



import com.techelevator.dao.PotluckDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Friend;
import com.techelevator.model.Guest;
import com.techelevator.model.Potluck;
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



}