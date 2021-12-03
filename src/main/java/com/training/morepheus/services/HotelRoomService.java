package com.training.morepheus.services;

import com.training.morepheus.dao.DAO;
import com.training.morepheus.models.HotelRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelRoomService implements ApiService<HotelRoom, Long>{

    private final DAO<HotelRoom, Long> hotelRoomDAO;

    @Autowired
    public HotelRoomService(DAO<HotelRoom, Long> hotelRoomDAO) {
        this.hotelRoomDAO = hotelRoomDAO;
    }

    /**
     * @param page
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<HotelRoom> getAll(Long page, String sort, String order) {
        return hotelRoomDAO.getAll(page,sort,order);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public HotelRoom getById(Long id) {
        Optional<HotelRoom> hotelRoom = hotelRoomDAO.getById(id);
        return hotelRoom.get();
    }

    /**
     * @param hotelRoom
     * @return
     */
    @Override
    public HotelRoom create(HotelRoom hotelRoom) {
        return hotelRoomDAO.create(hotelRoom);
    }

    /**
     * @param id
     * @param hotelRoom
     * @return
     */
    @Override
    public HotelRoom update(Long id, HotelRoom hotelRoom) {
        return hotelRoomDAO.update(id,hotelRoom);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        hotelRoomDAO.delete(id);
    }
}
