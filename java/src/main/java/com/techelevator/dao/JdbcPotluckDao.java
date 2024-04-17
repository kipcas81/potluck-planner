package com.techelevator.dao;

import com.techelevator.model.*;
import com.techelevator.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
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
        List<Potluck> userPotlucks = new ArrayList<>();
//        String sql = "SELECT potluck_id, event_name, description, event_date, location, event_time, user_id, potluck_dietary_restrictions, frequency_days, is_private, is_recurring, is_completed " +
//                "FROM potlucks " +
//                "WHERE (user_id = ? OR potluck_id IN (SELECT potluck_id FROM potluck_guests WHERE guest_id = ?)) AND (is_completed = ? OR event_date >= CURRENT_DATE) ORDER BY event_date DESC;";
//
          String sql = "SELECT p.potluck_id as potluckId, event_name, description, event_date, location, event_time, p.user_id as userId, potluck_dietary_restrictions, frequency_days, is_private, is_recurring, is_completed, " +
                "pu.user_id, pu.user_type as userType " +
                "FROM potlucks p " +
                "join potluck_user pu on pu.potluck_id = p.potluck_id " +
                "where pu.user_id = ? " +
                "ORDER BY event_date DESC, event_time DESC;";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
            while (result.next()) {
                Potluck potluck = mapRowToPotluckUserView(result);
                userPotlucks.add(potluck);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return userPotlucks;
    }

    public List<PotluckUser> getAllPotlucksAndUserTypes(int userId) {
        List<PotluckUser> potluckUsers = new ArrayList<>();
        String sql = "SELECT p.potluck_id as potluckId, event_name, description, event_date, location, event_time, p.user_id as userId, potluck_dietary_restrictions, frequency_days, is_private, is_recurring, is_completed, " +
                "pu.user_id, pu.user_type as userType " +
                "FROM potlucks p " +
                "join potluck_user pu on pu.potluck_id = p.potluck_id " +
                "where pu.user_id = ? " +
                "ORDER BY event_date DESC, event_time DESC;";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
            while (result.next()) {
                PotluckUser potluckUser = mapRowToPotluckUser(result);
                potluckUsers.add(potluckUser);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return potluckUsers;
    }

    @Override
    public Potluck createPotluck(Potluck potluck) {
        Potluck createdPotluck = potluck;
        String insertPotluckSql = "INSERT INTO potlucks (user_id, event_name, description, event_date, event_time, location, potluck_dietary_restrictions, frequency_days, is_recurring, is_private, is_completed) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING potluck_id";

        try {
            int potluckId = jdbcTemplate.queryForObject(insertPotluckSql, Integer.class, potluck.getUserId(), potluck.getEventName(), potluck.getDescription(), potluck.getEventDate(), potluck.getEventTime(), potluck.getLocation(), potluck.getDietaryRestrictions(), potluck.getFrequencyDays(), potluck.isRecurring(), potluck.isPrivate(), potluck.isCompleted());
            createdPotluck.setPotluckId(potluckId);

            String insertPotluckUserSql = "INSERT INTO potluck_user (potluck_id, user_id, user_type) VALUES (?, ?, ?)";
            jdbcTemplate.update(insertPotluckUserSql, potluckId, potluck.getUserId(), "Host");

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
        }

        return createdPotluck;
    }

    public Potluck updatePotluck(int potluckId, Potluck potluck) {
        String updatePotluckSql = "UPDATE potlucks SET event_name = ?, description = ?, event_date = ?, event_time = ?, " +
                "location = ?, potluck_dietary_restrictions = ?, frequency_days = ?, is_recurring = ?, is_private = ?, is_completed = ? " +
                "WHERE potluck_id = ?";
        try {
            jdbcTemplate.update(updatePotluckSql, potluck.getEventName(), potluck.getDescription(), potluck.getEventDate(),
                    potluck.getEventTime(), potluck.getLocation(), potluck.getDietaryRestrictions(), potluck.getFrequencyDays(),
                    potluck.isRecurring(), potluck.isPrivate(), potluck.isCompleted(), potluckId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
        }

        return potluck;
    }

    public boolean deletePotluck(int potluckId, int userId) {
        try {
            String deleteDishNeedsSql = "DELETE FROM potluck_dish_needs WHERE potluck_id = ?";
            jdbcTemplate.update(deleteDishNeedsSql, potluckId);

            String deletePotluckGuestsSql = "DELETE FROM potluck_guests WHERE potluck_id = ?";
            jdbcTemplate.update(deletePotluckGuestsSql, potluckId);

            String deletePotluckUserDishSql = "DELETE FROM potluck_user_dish WHERE potluck_id = ?";
            jdbcTemplate.update(deletePotluckUserDishSql, potluckId);

            String deletePotluckUseSql = "DELETE FROM potluck_user WHERE potluck_id = ?";
            jdbcTemplate.update(deletePotluckUseSql, potluckId);

            String deletePotluckSql = "DELETE FROM potlucks WHERE potluck_id = ? AND user_id = ?";
            jdbcTemplate.update(deletePotluckSql, potluckId, userId);

            return true;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    public Potluck getPotluckById(int potluckId) {
        Potluck potluck = new Potluck();
        String sql = "SELECT potluck_id, event_name, description, event_date, location, event_time, user_id, potluck_dietary_restrictions, frequency_days, is_private, is_recurring, is_completed " +
                "FROM potlucks " +
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
                int friendId = jdbcTemplate.queryForObject(sql, int.class, userid, friend.getFriend_first_name(),
                        friend.getFriend_last_name(), friend.getFriend_email_address());
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


    public boolean linkRegisteredGuestsToPotluckUserTable(User user) {
        int potluckId = 0;
        String sql = "SELECT potluck_id " +
                "from potluck_guests " +
                "where guest_email_address = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, user.getEmail());
            while (result.next()) {
                potluckId = result.getInt("potluck_id");
                sql = "INSERT INTO potluck_user(potluck_id, user_id, user_type) " +
                        "VALUES(?,?,?)";
                try {
                    int numberOfRowsInserted = jdbcTemplate.update(sql, potluckId, user.getId(), "Guest");
                    if (numberOfRowsInserted != 1) {
                        throw new DaoException("while registering a guest, potluck_user table insert didn't go as expected. Please verify.");
                    }
                } catch (CannotGetJdbcConnectionException e) {
                    throw new DaoException("Unable to connect to server or database", e);
                } catch (DataIntegrityViolationException e) {
                    throw new DaoException("Data integrity violation", e);
                } catch (BadSqlGrammarException e) {
                    throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
        }

        return  true;
    }
    public List<Guest> inviteGuests(Guest[] guests, int userid) {
        List<Guest> invitedGuests = new ArrayList<>();
        Guest createdGuest = null;
        for (Guest guest : guests) {
            createdGuest = guest;
            String sql = "INSERT INTO potluck_guests(potluck_id, user_id, guest_first_name, guest_last_name, guest_email_address," +
                    " guest_already_registered) " +
                    "VALUES(?,?,?,?,?,?) returning guest_id;";
            try {
                int guestId = jdbcTemplate.queryForObject(sql, int.class, guest.getPotluck_id(), userid, guest.getGuest_first_name(),
                        guest.getGuest_last_name(), guest.getGuest_email_address(), guest.getGuest_already_registered());
                createdGuest.setGuest_id(guestId);
                invitedGuests.add(createdGuest);
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            } catch (BadSqlGrammarException e) {
                throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
            }

            int userId = 0;
            sql = "SELECT user_id " +
                    "from users " +
                    "where email = ?;";
            try {
                SqlRowSet result = jdbcTemplate.queryForRowSet(sql, guest.getGuest_email_address());
                if (result.next()) {
                    userId = result.getInt("user_id");
                    System.out.println(userId);
                }
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            } catch (BadSqlGrammarException e) {
                throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
            }

            if (userId != 0) {
                sql = "INSERT INTO potluck_user(potluck_id, user_id, user_type) " +
                        "VALUES(?,?,?)";
                try {
                    int numberOfRowsInserted = jdbcTemplate.update(sql, guest.getPotluck_id(), userId, "Guest");
                    if (numberOfRowsInserted != 1) {
                        throw new DaoException("while adding a guest, potluck_user table insert didn't go as expected. Please verify.");
                    }
                } catch (CannotGetJdbcConnectionException e) {
                    throw new DaoException("Unable to connect to server or database", e);
                } catch (DataIntegrityViolationException e) {
                    throw new DaoException("Data integrity violation", e);
                } catch (BadSqlGrammarException e) {
                    throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
                }
            }
        }
        return invitedGuests;
    }

    public List<Guest> removeGuests(Guest[] guests) {

        for (Guest guest : guests) {
            String sql = "SELECT dish_id " +
                    "from potluck_guests pg " +
                    "join users u on u.email = pg.guest_email_address " +
                    "join potluck_user_dish pud on pud.user_id = u.user_id " +
                    "where pg.guest_email_address = ?;";
            try {
                SqlRowSet result = jdbcTemplate.queryForRowSet(sql, guest.getGuest_email_address());
                while (result.next()) {
                    int dishId = result.getInt("dish_id");
                    deleteDish(guest.getPotluck_id(), dishId);
                }
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }
        }

        List<Guest> removedGuests = new ArrayList<>();
        int numberOfRowsRemoved, totalrowsDeleted = 0;
        for (Guest guest : guests) {

            int userId = 0;
            String sql = "SELECT user_id " +
                    "from users " +
                    "where email = ?;";
            try {
                SqlRowSet result = jdbcTemplate.queryForRowSet(sql, guest.getGuest_email_address());
                if (result.next()) {
                    userId = result.getInt("user_id");
                    System.out.println(userId);
                }
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            } catch (BadSqlGrammarException e) {
                throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
            }

            if (userId != 0) {
                sql = "delete from potluck_user where potluck_id = ? and user_id = ?;";
                try {
                    int numberOfRowsDeleted = jdbcTemplate.update(sql, guest.getPotluck_id(), userId);
                    if (numberOfRowsDeleted != 1) {
                        throw new DaoException("while removing a guest, potluck_user table delete didn't go as expected. Please verify.");
                    }
                } catch (CannotGetJdbcConnectionException e) {
                    throw new DaoException("Unable to connect to server or database", e);
                } catch (DataIntegrityViolationException e) {
                    throw new DaoException("Data integrity violation", e);
                } catch (BadSqlGrammarException e) {
                    throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
                }
            }

            sql = "delete from potluck_guests " +
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

        return removedGuests;
    }

    public List<Guest> getGuests(int potluckId) {
        List<Guest> allGuestsByPotluckId = new ArrayList<>();
        Guest guest = null;
        String sql = "SELECT potluck_id, user_id, guest_id, guest_first_name, guest_last_name, guest_email_address, guest_already_registered " +
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

    public PotluckDishNeeds addDishNeeds(int potluckId, PotluckDishNeeds potluckDishNeeds) {
        PotluckDishNeeds createPotluckDishNeeds = potluckDishNeeds;
        String sql = "INSERT INTO potluck_dish_needs(potluck_id, dish_category, dish_serving_count_needed, dish_serving_count_filled) " +
                "VALUES(?,?,?,?) returning dish_category_id;";
        try {
            int dishCategoryId = jdbcTemplate.queryForObject(sql, int.class, potluckId,potluckDishNeeds.getDish_category(),potluckDishNeeds.getDish_serving_count_needed(),
                    potluckDishNeeds.getDish_serving_count_filled());
            createPotluckDishNeeds.setPotluck_id(potluckId);
            createPotluckDishNeeds.setDish_category_id(dishCategoryId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
        }
        return createPotluckDishNeeds;
    }

    public PotluckDishNeeds updateDishNeeds(int potluckId, PotluckDishNeeds potluckDishNeeds) {
        PotluckDishNeeds updatedPotluckDishNeeds = potluckDishNeeds;
        String sql = "update potluck_dish_needs " +
                "set dish_category = ?, dish_serving_count_needed = ? " +
                "where potluck_id = ? and dish_category = ?;";
        try {
            int rowsUpdated = jdbcTemplate.update(sql, potluckDishNeeds.getDish_category(),potluckDishNeeds.getDish_serving_count_needed(),
                    potluckId,potluckDishNeeds.getDish_category());
            updatedPotluckDishNeeds.setPotluck_id(potluckId);
            if (rowsUpdated != 1) {
                throw new DaoException("potluck_dish_needs update didn't go as expected. Please verify.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
        }
        return updatedPotluckDishNeeds;
    }

    public List<PotluckDishNeeds> viewDishNeeds(int potluckId) {
        List<PotluckDishNeeds> potluckDishNeedsByPotluckId = new ArrayList<>();
        PotluckDishNeeds potluckDishNeeds = null;
        String sql = "SELECT potluck_id, dish_category_id, dish_category, dish_serving_count_needed, dish_serving_count_filled " +
                "FROM potluck_dish_needs where potluck_id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, potluckId);
            while (result.next()) {
                potluckDishNeeds = mapRowToPotluckDishNeeds(result);
                potluckDishNeedsByPotluckId.add(potluckDishNeeds);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return potluckDishNeedsByPotluckId;
    }

    public boolean deleteDishNeeds(int potluckId, int dishCategoryId) {
        String sql = "delete from potluck_dish_needs " +
                "where potluck_id = ? " +
                "and dish_category_id = ?;";
        try {
            int rowsDeleted = jdbcTemplate.update(sql, potluckId, dishCategoryId);
            if (rowsDeleted == 0) {
                throw new DaoException("potluck_dish_needs DELETION by dishCategoryId didn't go as expected. Please verify.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return true;
    }

    public boolean deleteAllDishNeeds(int potluckId) {
        String sql = "delete from potluck_dish_needs " +
                "where potluck_id = ?;";
        try {
            int rowsDeleted = jdbcTemplate.update(sql, potluckId);
            if (rowsDeleted == 0) {
                throw new DaoException("potluck_dish_needs DELETION didn't go as expected. Please verify.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return true;
    }

    public Dish bringDish(int userid, int potluckId, Dish dish) {
        Dish createdDish = dish;
        int dishId = 0;
        String sql = "update potluck_dish_needs set  dish_serving_count_filled = dish_serving_count_filled + ? " +
                "where potluck_id = ? " +
                "and dish_category = ?;";
        try {
            int rowsUpdated = jdbcTemplate.update(sql, dish.getDish_servings(), potluckId, dish.getDish_category());
            if (rowsUpdated != 1) {
                throw new DaoException("potluck_dish_needs:dish_serving_count_filled update didn't go as expected. Please verify.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
        }

        sql = "INSERT INTO dish(dish_name, dish_dietary_restrictions, dish_category, dish_servings," +
                " dish_recipe, user_id) " +
                "VALUES(?,?,?,?,?,?) returning dish_id;";
        try {
            dishId = jdbcTemplate.queryForObject(sql, int.class, dish.getDish_name(), dish.getDish_dietary_restrictions(),
                    dish.getDish_category(), dish.getDish_servings(), dish.getDish_recipe(), userid);
            createdDish.setDish_id(dishId);
            createdDish.setUser_id(userid);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
        }

        sql = "INSERT INTO potluck_user_dish(potluck_id, user_id, dish_id) " +
                "VALUES(?,?,?);";
        try {
            jdbcTemplate.update(sql, potluckId, userid, dishId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
        }

        return createdDish;
    }

    public Dish updateDish(int dishId, Dish dish) {
        Dish updatedDish = dish;
        String sql = "update dish set  dish_name = ?, dish_dietary_restrictions = ?, dish_recipe = ? " +
                "where dish_id = ?;";
        try {
            int rowsUpdated = jdbcTemplate.update(sql,dish.getDish_name(),dish.getDish_dietary_restrictions(),dish.getDish_recipe(),dishId);
            if (rowsUpdated != 1) {
                throw new DaoException("Dish table update didn't go as expected. Please verify.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
        }
        return updatedDish;
    }

    public Dish getDish(int dishId) {
        Dish dish = new Dish();
        String sql = "select dish_id, dish_name, dish_dietary_restrictions, dish_category, dish_servings, dish_recipe, user_id  " +
                "from dish " +
                "where dish_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, dishId);
            if (results.next()) {
                dish = mapRowToDish(results);
                int userId = 0;
                sql = "SELECT username " +
                        "from users " +
                        "where user_id = ?;";
                try {
                    SqlRowSet result = jdbcTemplate.queryForRowSet(sql, dish.getUser_id());
                    if (result.next()) {
                        String userName = result.getString("username");
                        dish.setUsername(userName);
                    }
                } catch (CannotGetJdbcConnectionException e) {
                    throw new DaoException("Unable to connect to server or database", e);
                } catch (DataIntegrityViolationException e) {
                    throw new DaoException("Data integrity violation", e);
                } catch (BadSqlGrammarException e) {
                    throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return dish;
    }


    public boolean deleteDish(int potluckId, int dishId) {
        Dish dish = getDish(dishId);

        String sql = "update potluck_dish_needs set  dish_serving_count_filled = dish_serving_count_filled - ? " +
                "where potluck_id = ? " +
                "and dish_category = ?;";
        try {
            int rowsUpdated = jdbcTemplate.update(sql, dish.getDish_servings(), potluckId, dish.getDish_category());
            if (rowsUpdated != 1) {
                throw new DaoException("While deleting dish, potluck_dish_needs:dish_serving_count_filled update didn't go as expected. Please verify.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("Bad SQL Grammar, fix it and try again.", e);
        }

        sql = "delete from potluck_user_dish " +
                "where dish_id = ?;";
        try {
            int rowsDeleted = jdbcTemplate.update(sql, dishId);
            if (rowsDeleted != 1) {
                throw new DaoException("potluck_user_dish table row DELETION didn't go as expected. Please verify.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        sql = "delete from dish " +
                "where dish_id = ?;";
        try {
            int rowsDeleted = jdbcTemplate.update(sql, dishId);
            if (rowsDeleted != 1) {
                throw new DaoException("Dish table row DELETION didn't go as expected. Please verify.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return true;
    }

    public List<Dish> getAllDishesByPotluckId(int potluckId) {
        List<Dish> dishesByPotluckId = new ArrayList<>();
        PotluckUserDish potluckUserDish = null;
        String sql = "SELECT potluck_id, user_id, dish_id " +
                "FROM potluck_user_dish where potluck_id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, potluckId);
            while (result.next()) {
                potluckUserDish = mapRowToPotluckUserDish(result);
                dishesByPotluckId.add(getDish(potluckUserDish.getDish_id()));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return dishesByPotluckId;
    }

    private PotluckUserDish mapRowToPotluckUserDish(SqlRowSet rs) {
        PotluckUserDish potluckUserDish = new PotluckUserDish();
        potluckUserDish.setPotluck_id(rs.getInt("potluck_id"));
        potluckUserDish.setUser_id(rs.getInt("user_id"));
        potluckUserDish.setDish_id(rs.getInt("dish_id"));
        return potluckUserDish;
    }

    private Dish mapRowToDish(SqlRowSet rs) {
        Dish dish = new Dish();
        dish.setDish_id(rs.getInt("dish_id"));
        dish.setDish_name(rs.getString("dish_name"));
        dish.setDish_dietary_restrictions(rs.getString("dish_dietary_restrictions"));
        dish.setDish_category(rs.getString("dish_category"));
        dish.setDish_servings(rs.getInt("dish_servings"));
        dish.setDish_recipe(rs.getString("dish_recipe"));
        dish.setUser_id(rs.getInt("user_id"));
        return dish;
    }
    private PotluckDishNeeds mapRowToPotluckDishNeeds(SqlRowSet rs) {
        PotluckDishNeeds potluckDishNeeds = new PotluckDishNeeds();
        potluckDishNeeds.setPotluck_id(rs.getInt("potluck_id"));
        potluckDishNeeds.setDish_category_id(rs.getInt("dish_category_id"));
        potluckDishNeeds.setDish_category(rs.getString("dish_category"));
        potluckDishNeeds.setDish_serving_count_needed(rs.getInt("dish_serving_count_needed"));
        potluckDishNeeds.setDish_serving_count_filled(rs.getInt("dish_serving_count_filled"));
        return potluckDishNeeds;
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
        guest.setUser_id(rs.getInt("user_id"));
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
        potluck.setFrequencyDays(rs.getInt("frequency_days"));
        potluck.setDescription(rs.getString("description"));
        potluck.setEventName(rs.getString("event_name"));
        potluck.setEventDate(rs.getString("event_date"));
        potluck.setEventTime(rs.getString("event_time"));
        potluck.setRecurring(rs.getBoolean("is_recurring"));
        potluck.setLocation(rs.getString("location"));
        potluck.setPrivate(rs.getBoolean("is_private"));

        LocalDate eventDate = LocalDate.parse(rs.getString("event_date"));
        LocalTime eventTime = LocalTime.parse(rs.getString("event_time"));
        boolean isPotluckCompleted = isPotluckCompleted(eventDate, eventTime);
        potluck.setCompleted(isPotluckCompleted);

        potluck.setDietaryRestrictions(rs.getString("potluck_dietary_restrictions"));

        return potluck;
    }

    private PotluckUser mapRowToPotluckUser(SqlRowSet rs) {
        PotluckUser potluckUser = new PotluckUser();
        Potluck potluck = new Potluck();

        potluck.setPotluckId(rs.getInt("potluckId"));
        potluck.setUserId(rs.getInt("userId"));
        potluck.setFrequencyDays(rs.getInt("frequency_days"));
        potluck.setDescription(rs.getString("description"));
        potluck.setEventName(rs.getString("event_name"));
        potluck.setEventDate(rs.getString("event_date"));
        potluck.setEventTime(rs.getString("event_time"));
        potluck.setRecurring(rs.getBoolean("is_recurring"));
        potluck.setLocation(rs.getString("location"));
        potluck.setPrivate(rs.getBoolean("is_private"));

        LocalDate eventDate = LocalDate.parse(rs.getString("event_date"));
        LocalTime eventTime = LocalTime.parse(rs.getString("event_time"));
        boolean isPotluckCompleted = isPotluckCompleted(eventDate, eventTime);
        potluck.setCompleted(isPotluckCompleted);

        potluck.setDietaryRestrictions(rs.getString("potluck_dietary_restrictions"));


        potluckUser.setPotluck(potluck);
        potluckUser.setUserType(rs.getString("userType"));

        return potluckUser;
    }

    private Potluck mapRowToPotluckUserView(SqlRowSet rs) {
        Potluck potluck = new Potluck();

        potluck.setPotluckId(rs.getInt("potluckId"));
        potluck.setUserId(rs.getInt("userId"));
        potluck.setFrequencyDays(rs.getInt("frequency_days"));
        potluck.setDescription(rs.getString("description"));
        potluck.setEventName(rs.getString("event_name"));
        potluck.setEventDate(rs.getString("event_date"));
        potluck.setEventTime(rs.getString("event_time"));
        potluck.setRecurring(rs.getBoolean("is_recurring"));
        potluck.setLocation(rs.getString("location"));
        potluck.setPrivate(rs.getBoolean("is_private"));

        LocalDate eventDate = LocalDate.parse(rs.getString("event_date"));
        LocalTime eventTime = LocalTime.parse(rs.getString("event_time"));
        boolean isPotluckCompleted = isPotluckCompleted(eventDate, eventTime);
        potluck.setCompleted(isPotluckCompleted);

        potluck.setDietaryRestrictions(rs.getString("potluck_dietary_restrictions"));


        return potluck;
    }
    public boolean isPotluckCompleted(LocalDate eventDate, LocalTime eventTime) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        if(currentDate.isAfter(eventDate) || currentDate.equals(eventDate)) {
            return currentTime.isAfter(eventTime) || currentTime.equals(eventTime);
        }
        return false;
    }
}




