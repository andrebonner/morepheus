package com.training.morepheus.security;

public enum UserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    RESERVATION_READ("reservation:read"),
    RESERVATION_WRITE("reservation:write"),
    HOTEL_ROOM_READ("hotel_room:read"),
    HOTEL_ROOM_WRITE("hotel_room:write"),
    HOTEL_READ("hotel:read"),
    HOTEL_WRITE("hotel:write"),
    FLIGHT_READ("flight:read"),
    FLIGHT_WRITE("flight:write")
    ;

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
