package com.training.morepheus.services;

import com.training.morepheus.dao.DAO;
import com.training.morepheus.models.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements ApiService<Flight, Long>{

    private final DAO<Flight, Long> flightDAO;

    /**
     * @param flightDAO
     */
    @Autowired
    public FlightService(DAO<Flight, Long> flightDAO) {
        this.flightDAO = flightDAO;
    }

    /**
     * @param page
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<Flight> getAll(Long page, String sort, String order ) {
        return flightDAO.getAll(page, sort, order);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Flight getById(Long id) {
        Optional<Flight> flight = flightDAO.getById(id);
        return flight.get();
    }

    /**
     * @param flight
     * @return
     */
    @Override
    public Flight create(Flight flight) {
        return this.flightDAO.create(flight);
    }

    /**
     * @param id
     * @param flight
     * @return
     */
    @Override
    public Flight update(Long id, Flight flight) {
        return flightDAO.update(id, flight);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        flightDAO.delete(id);
    }
}
