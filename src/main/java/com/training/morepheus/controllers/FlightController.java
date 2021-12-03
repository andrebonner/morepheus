package com.training.morepheus.controllers;

import com.training.morepheus.models.Flight;
import com.training.morepheus.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/flights")
public class FlightController implements ApiController<Flight, Long>{

    private final ApiService<Flight, Long> flightService;

    /**
     * @param flightService
     */
    @Autowired
    public FlightController(ApiService<Flight, Long> flightService) {
        this.flightService = flightService;
    }

    /**
     * @param page
     * @param sort
     * @param order
     * @return
     */
    @GetMapping
    @Override
    @PreAuthorize("hasAnyRole('REG_USER','ADMIN')")
    public List<Flight> getAll(@RequestParam(name = "page", required = false) Long page,
                                @RequestParam(name = "sort", required = false) String sort,
                               @RequestParam(name = "order", required = false) String order) {
        page = page==null ? 1 : page;
        sort = sort==null ? "id" : sort;
        order = order==null ? "ASC" : order;
        return flightService.getAll(page, sort, order);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping(path="{id}")
    @Override
    @PreAuthorize("hasAnyRole('REG_USER','ADMIN')")
    public Flight getById(@PathVariable("id") Long id) {
        return flightService.getById(id);
    }

    /**
     * @param flight
     * @return
     */
    @PostMapping
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Flight create(@RequestBody Flight flight) {
        return flightService.create(flight);
    }

    /**
     * @param id
     * @param flight
     * @return
     */
    @PutMapping(path = "{id}")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Flight update(@PathVariable("id") Long id, @RequestBody Flight flight) {
        return flightService.update(id, flight);
    }

    /**
     * @param id
     */
    @DeleteMapping(path = "{id}")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        flightService.delete(id);
    }
}
