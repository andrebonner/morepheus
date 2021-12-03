package com.training.morepheus.mappers;

import com.training.morepheus.dao.HotelDAO;
import com.training.morepheus.models.HotelRoom;
import com.training.morepheus.models.Reservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HotelRoomMapper implements RowMapper<HotelRoom> {
    private final HotelDAO hotelDAO;

    public HotelRoomMapper(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    @Override
    public HotelRoom mapRow(ResultSet rs, int i) throws SQLException {
        HotelRoom hotelRoom = new HotelRoom();

        hotelRoom.setId(rs.getLong("id"));
        hotelRoom.setName(rs.getString("name"));
        hotelRoom.setLocation(rs.getString("location"));
        hotelRoom.setStatus(rs.getString("status"));
        hotelRoom.setHotel_id(rs.getLong("hotel_id"));

        List<Reservation> reservations = hotelDAO.getReservationsByRoomId(hotelRoom.getId());
        if(reservations.stream().count()>1)
            hotelRoom.setReservations(reservations);

        return hotelRoom;
    }
}
