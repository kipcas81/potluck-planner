package com.techelevator.dao;

import com.techelevator.model.Friend;
import com.techelevator.model.Potluck;
import com.techelevator.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
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




