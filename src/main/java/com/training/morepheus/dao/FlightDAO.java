package com.training.morepheus.dao;

import com.training.morepheus.mappers.FlightMapper;
import com.training.morepheus.models.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FlightDAO implements DAO<Flight, Long> {

    private static final Logger log = LoggerFactory.getLogger(FlightDAO.class);
    private JdbcTemplate jdbcTemplate;


    /**
     * @param jdbcTemplate
     */
    @Autowired
    public FlightDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @param page
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<Flight> getAll(Long page, String sort, String order) {
        String sql = "SELECT * FROM flights";

        return jdbcTemplate.query(sql,new FlightMapper());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Flight> getById(Long id) {
        String sql = "SELECT * FROM flights WHERE id = ?";
        Flight flight = null;

        try{
            flight = jdbcTemplate.queryForObject(sql, new Object[]{id}, new FlightMapper());
        } catch (DataAccessException ex) {
            log.info("Flight not found");
        }

        return Optional.ofNullable(flight);
    }

    /**
     * @param flight
     * @return
     */
    @Override
    public Flight create(Flight flight) {
        String sql = "INSERT INTO flights (number, arrival_date, departure_date, origin, destination, seats) VALUES(?,?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql, flight.getNumber(), flight.getArrival_date(), flight.getDeparture_date(), flight.getOrigin(), flight.getDestination(), flight.getSeats());
        if(insert==1){
            log.info("New Flight created: "+ flight.getNumber());
        }
        return flight;
    }

    /**
     * @param id
     * @param flight
     * @return
     */
    @Override
    public Flight update(Long id, Flight flight) {
        String sql = "UPDATE flights SET number=?, arrival_date=?, departure_date=?, origin=?, destination=?, seats=? WHERE id = ?";
        int update = jdbcTemplate.update(sql,flight.getNumber(), flight.getArrival_date(), flight.getDeparture_date(), flight.getOrigin(), flight.getDestination(), flight.getSeats(), id);

        if(update==1){
            log.info("Flight updated: "+ flight.getNumber());
        }
        return flight;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM flights WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
