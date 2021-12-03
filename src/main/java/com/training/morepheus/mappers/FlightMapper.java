package com.training.morepheus.mappers;

import com.training.morepheus.models.Flight;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class FlightMapper implements RowMapper<Flight> {
    @Override
    public Flight mapRow(ResultSet rs, int i) throws SQLException {
        Flight flight = new Flight();

        flight.setId(rs.getLong("id"));
        flight.setNumber(rs.getString("number"));
        flight.setArrival_date(rs.getObject("arrival_date", LocalDate.class));
        flight.setDeparture_date(rs.getObject("departure_date", LocalDate.class));
        flight.setOrigin(rs.getString("origin"));
        flight.setDestination(rs.getString("destination"));
        flight.setSeats(rs.getInt("seats"));

        return flight;
    }
}
