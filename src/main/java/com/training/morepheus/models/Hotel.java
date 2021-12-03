package com.training.morepheus.models;

import java.util.List;

public class Hotel {

    private Long id;
    private String name;
    private String location;
    private String status;
    private List<HotelRoom> rooms;

    public Hotel() {
    }

    /**
     * @param id
     * @param name
     * @param location
     * @param status
     * @param rooms
     */
    public Hotel(Long id, String name, String location, String status, List<HotelRoom> rooms) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.status = status;
        this.rooms = rooms;
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

    public List<HotelRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<HotelRoom> rooms) {
        this.rooms = rooms;
    }
    public void addRoom(HotelRoom room) {
        this.rooms.add(room);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
