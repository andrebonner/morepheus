package com.training.morepheus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class HotelRoom {

    private Long id;
    private String name;
    private String location;
    private String status;
    @JsonIgnore
    private Long hotel_id;
    private List<Reservation> reservations;

    public HotelRoom() {
    }

    /**
     * @param name
     * @param location
     * @param status
     * @param hotel_id
     * @param reservations
     */
    public HotelRoom( String name,
                      String location,
                      String status,
                      Long hotel_id,
                      List<Reservation> reservations) {
        this.name = name;
        this.location = location;
        this.status = status;
        this.hotel_id = hotel_id;
        this.reservations = reservations;
    }

    public Long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "HotelRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
