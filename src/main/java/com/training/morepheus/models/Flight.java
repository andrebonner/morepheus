package com.training.morepheus.models;

import java.time.LocalDate;


public class Flight {


    private Long id;
    private String number;
    private LocalDate arrival_date;
    private LocalDate departure_date;
    private String origin;
    private String destination;
    private Integer seats;

    public Flight() {
    }

    /**
     * @param number
     * @param arrival_date
     * @param departure_date
     * @param origin
     * @param destination
     * @param seats
     */
    public Flight( String number,
                   LocalDate arrival_date,
                   LocalDate departure_date,
                   String origin,
                   String destination,
                   Integer seats) {

        this.number = number;
        this.arrival_date = arrival_date;
        this.departure_date = departure_date;
        this.origin = origin;
        this.destination = destination;
        this.seats = seats;
    }

    /**
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return
     */
    public LocalDate getArrival_date() {
        return arrival_date;
    }

    /**
     * @param arrival_date
     */
    public void setArrival_date(LocalDate arrival_date) {
        this.arrival_date = arrival_date;
    }

    /**
     * @return
     */
    public LocalDate getDeparture_date() {
        return departure_date;
    }

    /**
     * @param departure_date
     */
    public void setDeparture_date(LocalDate departure_date) {
        this.departure_date = departure_date;
    }

    /**
     * @return
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * @return
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return
     */
    public Integer getSeats() {
        return seats;
    }

    /**
     * @param seats
     */
    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", arrival_date=" + arrival_date +
                ", departure_date=" + departure_date +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", seats=" + seats +
                '}';
    }
}
