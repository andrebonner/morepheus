package com.training.morepheus.dao;

import com.training.morepheus.mappers.HotelRoomMapper;
import com.training.morepheus.models.HotelRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HotelRoomDAO implements DAO<HotelRoom, Long>{

    private static final Logger log = LoggerFactory.getLogger(HotelRoomDAO.class);
    private JdbcTemplate jdbcTemplate;

    /**
     * @param jdbcTemplate
     */
    @Autowired
    public HotelRoomDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @param page
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<HotelRoom> getAll(Long page, String sort, String order) {
        String sql = "SELECT * FROM hotel_rooms";

        return jdbcTemplate.query(sql, new HotelRoomMapper(new HotelDAO(jdbcTemplate)));
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<HotelRoom> getById(Long id) {
        String sql = "SELECT * FROM hotel_rooms WHERE id = ?";
        HotelRoom hotelRoom = null;

        try {
            hotelRoom = jdbcTemplate.queryForObject(sql,new Object[]{id},new HotelRoomMapper(new HotelDAO(jdbcTemplate)));
        } catch (DataAccessException ex){
            log.info("Hotel Room doesn't exist");
        }
        return Optional.ofNullable(hotelRoom);
    }

    /**
     * @param hotelRoom
     * @return
     */
    @Override
    public HotelRoom create(HotelRoom hotelRoom) {
        String sql = "INSERT INTO hotel_rooms (name, location, status, hotel_id) VALUES(?,?,?,?)";
        int insert = jdbcTemplate.update(sql,hotelRoom.getName(),hotelRoom.getLocation(),hotelRoom.getStatus(),hotelRoom.getHotel_id());

        if(insert==1){
            log.info("Hotel Room created : "+hotelRoom.getName());
        }

        return hotelRoom;
    }

    /**
     * @param id
     * @param hotelRoom
     * @return
     */
    @Override
    public HotelRoom update(Long id, HotelRoom hotelRoom) {
        String sql = "UPDATE hotel_rooms SET name=?, location=?, status=?, hotel_id=? WHERE id=?";
        int update = jdbcTemplate.update(sql,hotelRoom.getName(),hotelRoom.getLocation(),hotelRoom.getStatus(),hotelRoom.getHotel_id(), id);

        if(update==1){
            log.info("Hotel Room updated : "+ hotelRoom.getName());
        }

        return hotelRoom;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM hotel_rooms WHERE id = ?";

        jdbcTemplate.update(sql,id);
    }
}
