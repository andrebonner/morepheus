package com.training.morepheus.controllers;

import com.training.morepheus.models.Hotel;
import com.training.morepheus.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/hotels")
public class HotelController implements ApiController<Hotel, Long> {

    private final ApiService<Hotel, Long> hotelService;

    /**
     * @param hotelService
     */
    @Autowired
    public HotelController(ApiService<Hotel, Long> hotelService) {
        this.hotelService = hotelService;
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
    public List<Hotel> getAll(@RequestParam(name = "page", required = false) Long page,
                                                                             @RequestParam(name = "sort", required = false) String sort,
                                                                             @RequestParam(name = "order", required = false) String order) {
        page = page==null ? 1 : page;
        sort = sort==null ? "id" : sort;
        order = order==null ? "ASC" : order;
        return hotelService.getAll(page, sort,order);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping(path = "{id}")
    @Override
    @PreAuthorize("hasAnyRole('REG_USER','ADMIN')")
    public Hotel getById(@PathVariable("id") Long id) {
        return hotelService.getById(id);
    }

    /**
     * @param hotel
     * @return
     */
    @PostMapping
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Hotel create(@RequestBody Hotel hotel) {
        return hotelService.create(hotel);
    }

    /**
     * @param id
     * @param hotel
     * @return
     */
    @PutMapping(path = "{id}")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Hotel update(@PathVariable("id") Long id, @RequestBody Hotel hotel) {
        return hotelService.update(id, hotel);
    }

    /**
     * @param id
     */
    @DeleteMapping(path = "{id}")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        hotelService.delete(id);
    }
}
