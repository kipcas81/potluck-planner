package com.techelevator.dao;

import com.techelevator.model.Potluck;
import com.techelevator.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
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
        String sql = "select potluck_id, event_name from potlucks;";
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
        String sql = "INSERT INTO potlucks (event_name, event_date, event_time) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(sql, potluck.getEventName(), potluck.getEventDate(), potluck.getEventTime());

        } catch (Exception e) {
            throw new DaoException("An error occurred while creating the potluck", e);
        }
        return potluck;
    }

    private Potluck mapRowToPotluck(SqlRowSet rs) {
        Potluck potluck = new Potluck();
        potluck.setPotluckId(rs.getInt("potluck_id"));
       // potluck.setUserId(rs.getInt("user_id"));
        potluck.setEventName(rs.getString("event_name"));
        potluck.setEventDate(rs.getString("event_date"));
        potluck.setEventTime(rs.getString("event_time"));
        //potluck.setLocation(rs.getString("location"));
        //potluck.setRecurring(rs.getBoolean("is_recurring"));

      //  List<String> dietaryRestrictions = Arrays.asList(rs.getString("potluck_dietary_restrictions").split(","));
       // potluck.setDietaryRestrictions(dietaryRestrictions);

        return potluck;
    }
}




