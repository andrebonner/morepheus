package com.training.morepheus.dao;

import com.training.morepheus.mappers.HotelMapper;
import com.training.morepheus.mappers.HotelRoomMapper;
import com.training.morepheus.mappers.ReservationMapper;
import com.training.morepheus.models.Hotel;
import com.training.morepheus.models.HotelRoom;
import com.training.morepheus.models.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Component
public class HotelDAO implements DAO<Hotel, Long>{

    private static final Logger log = LoggerFactory.getLogger(HotelDAO.class);
    private JdbcTemplate jdbcTemplate;


    /**
     * @param jdbcTemplate
     */
    @Autowired
    public HotelDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @param page
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<Hotel> getAll(Long page, String sort, String order) {
        int limit = 2;
        int offset = page.intValue()>1 ? (page.intValue() * limit)-limit : 0;
        String sql = "SELECT * FROM hotels ORDER BY "+sort+" "+order+" LIMIT "+limit+" OFFSET "+offset;

        return jdbcTemplate.query(sql,new HotelMapper(this));
    }

    /**
     * @param id
     * @return
     */
    public List<HotelRoom> getRoomsByHotelId(Long id){
        String sql = "SELECT * FROM hotel_rooms WHERE hotel_id = ?";

        return jdbcTemplate.query(sql, new Object[]{id},new HotelRoomMapper(this));
    }

    /**
     * @param id
     * @return
     */
    public List<Reservation> getReservationsByRoomId(Long id){
        String sql = "SELECT * FROM reservations WHERE hotel_room_id = ?";

        return jdbcTemplate.query(sql, new Object[]{id},new ReservationMapper());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Hotel> getById(Long id) {
        String sql = "SELECT * FROM hotels WHERE id = ?";
        Hotel hotel = null;

        try{
            hotel = jdbcTemplate.queryForObject(sql,new Object[]{id},new HotelMapper(this));
        } catch (DataAccessException ex){
            log.info("Hotel not found");
        }

        return Optional.ofNullable(hotel);
    }

    /**
     * @param hotel
     * @return
     */
    @Override
    public Hotel create(Hotel hotel) {
        String sql = "INSERT INTO hotels (name, location, status) VALUES(?,?,?)";
        int insert = jdbcTemplate.update(sql,hotel.getName(),hotel.getLocation(),hotel.getStatus());

        if(insert==1){
            log.info("Hotel created : "+hotel.getName());
        }
        return hotel;
    }

    /**
     * @param id
     * @param hotel
     * @return
     */
    @Override
    public Hotel update(Long id, Hotel hotel) {
        String sql = "UPDATE hotels SET name=?, location=?, status=? WHERE id =?";
        int update = jdbcTemplate.update(sql,hotel.getName(),hotel.getLocation(),hotel.getStatus(), id);

        if(update==1){
            log.info("Hotel updated : "+ hotel.getName());
        }
        return hotel;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM hotels WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
