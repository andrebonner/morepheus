package com.training.morepheus.mappers;

import com.training.morepheus.dao.HotelDAO;
import com.training.morepheus.models.Hotel;
import com.training.morepheus.models.HotelRoom;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HotelMapper implements RowMapper<Hotel> {
    private final HotelDAO hotelDAO;

    public HotelMapper(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    @Override
    public Hotel mapRow(ResultSet rs, int i) throws SQLException {
        Hotel hotel = new Hotel();

        hotel.setId(rs.getLong("id"));
        hotel.setName(rs.getString("name"));
        hotel.setLocation(rs.getString("location"));
        hotel.setStatus(rs.getString("status"));
        List<HotelRoom> hotelRooms = hotelDAO.getRoomsByHotelId(hotel.getId());

        if(hotelRooms.stream().count()>1)
            hotel.setRooms(hotelRooms);

        return hotel;
    }
}
