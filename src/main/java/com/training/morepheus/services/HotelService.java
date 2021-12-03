package com.training.morepheus.services;

import com.training.morepheus.dao.DAO;
import com.training.morepheus.models.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService implements ApiService<Hotel,Long> {

    private final DAO<Hotel, Long> hotelDAO;

    /**
     * @param hotelDAO
     */
    @Autowired
    public HotelService(DAO<Hotel, Long> hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    /**
     * @param page
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<Hotel> getAll(Long page, String sort, String order) {
        return hotelDAO.getAll(page, sort, order);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Hotel getById(Long id) {
        Optional<Hotel> hotel = hotelDAO.getById(id);
        return hotel.get();
    }

    /**
     * @param hotel
     * @return
     */
    @Override
    public Hotel create(Hotel hotel) {
        return hotelDAO.create(hotel);
    }

    /**
     * @param id
     * @param hotel
     * @return
     */
    @Override
    public Hotel update(Long id, Hotel hotel) {
        return hotelDAO.update(id, hotel);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        hotelDAO.delete(id);
    }
}
