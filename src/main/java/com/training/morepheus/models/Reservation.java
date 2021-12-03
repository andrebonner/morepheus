package com.training.morepheus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

public class Reservation {

    @JsonIgnore
    private Long hotel_room_id;
    @JsonIgnore
    private Long user_id;
    private Timestamp check_in_time;
    private Timestamp check_out_time;

    public Reservation() {
    }

    /**
     * @param hotel_room_id
     * @param user_id
     * @param check_in_time
     * @param check_out_time
     */
    public Reservation(Long hotel_room_id, Long user_id, Timestamp check_in_time, Timestamp check_out_time) {
        this.hotel_room_id = hotel_room_id;
        this.user_id = user_id;
        this.check_in_time = check_in_time;
        this.check_out_time = check_out_time;
    }


    public Long getHotel_room_id() {
        return hotel_room_id;
    }

    public void setHotel_room_id(Long hotel_room_id) {
        this.hotel_room_id = hotel_room_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Timestamp getCheck_in_time() {
        return check_in_time;
    }

    public void setCheck_in_time(Timestamp check_in_time) {
        this.check_in_time = check_in_time;
    }

    public Timestamp getCheck_out_time() {
        return check_out_time;
    }

    public void setCheck_out_time(Timestamp check_out_time) {
        this.check_out_time = check_out_time;
    }


}
