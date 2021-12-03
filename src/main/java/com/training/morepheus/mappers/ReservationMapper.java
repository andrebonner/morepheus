package com.training.morepheus.mappers;

import com.training.morepheus.models.Reservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationMapper implements RowMapper<Reservation> {
    @Override
    public Reservation mapRow(ResultSet rs, int i) throws SQLException {
        Reservation reservation = new Reservation();

        reservation.setHotel_room_id(rs.getLong("hotel_room_id"));
        reservation.setUser_id(rs.getLong("user_id"));
        reservation.setCheck_in_time(rs.getTimestamp("check_in_time"));
        reservation.setCheck_out_time(rs.getTimestamp("check_out_time"));

        return reservation;
    }
}
