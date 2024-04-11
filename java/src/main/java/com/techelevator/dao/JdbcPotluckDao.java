package com.techelevator.dao;

import com.techelevator.model.Friend;
import com.techelevator.model.Guest;
import com.techelevator.model.Potluck;
import com.techelevator.exception.DaoException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class JdbcPotluckDao implements PotluckDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPotluckDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Potluck> getAllPotlucks(int userId) {
        List<Potluck> allPotlucks = new ArrayList<>();
        String sql = "select potluck_id, event_name, description, event_date, event_time, user_id, potluck_dietary_restrictions, is_private, is_recurring from potlucks;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            while (result.next()) {
                Potluck potluck = mapRowToPotluck(result);
                allPotlucks.add(potluck);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return allPotlucks;
    }

    @Override
    public Potluck createPotluck(Potluck potluck) {
        Potluck createdPotluck = new Potluck();
        String insertPotluckSql = "INSERT INTO potlucks (event_name, event_date, event_time, is_recurring, is_private) VALUES (?, ?, ?, ?, ?)";
        try {
            int newPotluckId = jdbcTemplate.queryForObject(insertPotluckSql, int.class, potluck.getEventName(), potluck.getEventDate(), potluck.getEventTime(), potluck.isRecurring(), potluck.isPrivate());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
        }

        return createdPotluck;
    }

    public Potluck getPotluckById(int potluckId) {
        Potluck potluck = new Potluck();
        String sql = "select potluck_id, potluck_name, user_id " +
                "from potluck " +
                "where potluck_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, potluckId);
            if (results.next()) {
                potluck = mapRowToPotluck(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return potluck;
    }

    public List<Friend> saveFriends(int userid, Friend[] friends) {
        List<Friend> savedFriends = new ArrayList<>();
        Friend createdFriend = null;
        for (Friend friend : friends) {
            createdFriend = friend;
            String sql = "INSERT INTO users_friends(user_id, friend_first_name, friend_last_name, friend_email_address) " +
                    "VALUES(?,?,?,?) returning friend_id;";
            try {
                int friendId = jdbcTemplate.queryForObject(sql, int.class, userid,friend.getFriend_first_name(),
                        friend.getFriend_last_name(),friend.getFriend_email_address());
                createdFriend.setUser_id(userid);
                createdFriend.setFriend_id(friendId);
                savedFriends.add(createdFriend);
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            } catch (BadSqlGrammarException e) {
                throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
            }
        }
        return savedFriends;
    }

    public List<Friend> getFriends(int userid) {
        List<Friend> getFriendsByUserid = new ArrayList<>();
        Friend friend = null;
        String sql = "SELECT user_id, friend_id, friend_first_name, friend_last_name, friend_email_address " +
                "FROM users_friends where user_id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userid);
            while (result.next()) {
                friend = mapRowToFriend(result);
                getFriendsByUserid.add(friend);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return getFriendsByUserid;
    }
    public List<Guest> inviteGuests(Guest[] guests) {
        List<Guest> invitedGuests = new ArrayList<>();
        Guest createdGuest = null;
        for (Guest guest : guests) {
            createdGuest = guest;
            String sql = "INSERT INTO potluck_guests(potluck_id, guest_first_name, guest_last_name, guest_email_address," +
                    " guest_already_registered) " +
                    "VALUES(?,?,?,?,?) returning guest_id;";
            try {
                int guestId = jdbcTemplate.queryForObject(sql, int.class, guest.getPotluck_id(),guest.getGuest_first_name(),
                        guest.getGuest_last_name(),guest.getGuest_email_address(),guest.getGuest_already_registered());
                createdGuest.setGuest_id(guestId);
                invitedGuests.add(createdGuest);
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            } catch (BadSqlGrammarException e) {
                throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
            }
        }
        return invitedGuests;
    }
    public List<Guest> removeGuests(Guest[] guests) {
        List<Guest> removedGuests = new ArrayList<>();
        int numberOfRowsRemoved, totalrowsDeleted = 0;
        for (Guest guest : guests) {
            String sql = "delete from potluck_guests " +
                    "where potluck_id = ? " +
                    "and guest_email_address = ?;";
            try {
                numberOfRowsRemoved = jdbcTemplate.update(sql, guest.getPotluck_id(), guest.getGuest_email_address());
                totalrowsDeleted += numberOfRowsRemoved;
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            } catch (BadSqlGrammarException e) {
                throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
            }
        }
        System.out.println(totalrowsDeleted);
        return removedGuests;
    }
    public List<Guest> getGuests(int potluckId) {
        List<Guest> allGuestsByPotluckId = new ArrayList<>();
        Guest guest = null;
        String sql = "SELECT potluck_id, guest_id, guest_first_name, guest_last_name, guest_email_address, guest_already_registered " +
                "FROM potluck_guests where potluck_id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, potluckId);
            while (result.next()) {
                guest = mapRowToGuest(result);
                allGuestsByPotluckId.add(guest);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return allGuestsByPotluckId;
    }
    private Friend mapRowToFriend(SqlRowSet rs) {
        Friend friend = new Friend();
        friend.setUser_id(rs.getInt("user_id"));
        friend.setFriend_id(rs.getInt("friend_id"));
        friend.setFriend_first_name(rs.getString("friend_first_name"));
        friend.setFriend_last_name(rs.getString("friend_last_name"));
        friend.setFriend_email_address(rs.getString("friend_email_address"));
        return friend;
    }
    private Guest mapRowToGuest(SqlRowSet rs) {
        Guest guest = new Guest();
        guest.setPotluck_id(rs.getInt("potluck_id"));
        guest.setGuest_id(rs.getInt("guest_id"));
        guest.setGuest_first_name(rs.getString("guest_first_name"));
        guest.setGuest_last_name(rs.getString("guest_last_name"));
        guest.setGuest_email_address(rs.getString("guest_email_address"));
        guest.setGuest_already_registered(rs.getBoolean("guest_already_registered"));
        return guest;
    }








    private Potluck mapRowToPotluck(SqlRowSet rs) {
        Potluck potluck = new Potluck();
        potluck.setPotluckId(rs.getInt("potluck_id"));
        potluck.setUserId(rs.getInt("user_id"));
        potluck.setEventName(rs.getString("event_name"));
        potluck.getDescription(rs.getString("description"));
        potluck.setEventDate(rs.getString("event_date"));
        potluck.setEventTime(rs.getString("event_time"));
        potluck.setRecurring(rs.getBoolean("is_recurring"));
        potluck.setLocation(rs.getString("location"));
        potluck.setPrivate(rs.getBoolean("is_private"));


        List<String> dietaryRestrictions = Arrays.asList(rs.getString("potluck_dietary_restrictions").split(","));
        potluck.setDietaryRestrictions(dietaryRestrictions);

        return potluck;
    }
}




