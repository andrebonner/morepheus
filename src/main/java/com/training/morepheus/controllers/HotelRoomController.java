package com.training.morepheus.controllers;

import com.training.morepheus.models.HotelRoom;
import com.training.morepheus.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/hotel-rooms")
public class HotelRoomController implements ApiController<HotelRoom, Long>{

    private final ApiService<HotelRoom, Long> hotelRoomService;

    @Autowired
    public HotelRoomController(ApiService<HotelRoom, Long> hotelRoomService) {
        this.hotelRoomService = hotelRoomService;
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
    public List<HotelRoom> getAll(@RequestParam(name = "page", required = false) Long page,
                                  @RequestParam(name = "sort", required = false) String sort,
                                  @RequestParam(name = "order", required = false) String order) {
        page = page==null ? 1 : page;
        sort = sort==null ? "id" : sort;
        order = order==null ? "ASC" : order;
        return hotelRoomService.getAll(page, sort, order);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping(path = "{id}")
    @Override
    @PreAuthorize("hasAnyRole('REG_USER','ADMIN')")
    public HotelRoom getById(@PathVariable("id") Long id) {
        return hotelRoomService.getById(id);
    }

    /**
     * @param hotelRoom
     * @return
     */
    @PostMapping
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public HotelRoom create(@RequestBody HotelRoom hotelRoom) {
        return hotelRoomService.create(hotelRoom);
    }

    /**
     * @param id
     * @param hotelRoom
     * @return
     */
    @PutMapping(path="{id}")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public HotelRoom update(@PathVariable("id") Long id, @RequestBody HotelRoom hotelRoom) {
        return hotelRoomService.update(id, hotelRoom);
    }

    /**
     * @param id
     */
    @DeleteMapping(path="{id}")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        hotelRoomService.delete(id);
    }
}
