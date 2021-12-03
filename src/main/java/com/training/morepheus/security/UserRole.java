package com.training.morepheus.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    ADMIN(Sets.newHashSet(
            UserPermission.USER_READ,
            UserPermission.USER_WRITE,
            UserPermission.RESERVATION_READ,
            UserPermission.RESERVATION_WRITE,
            UserPermission.HOTEL_ROOM_READ,
            UserPermission.HOTEL_ROOM_WRITE,
            UserPermission.HOTEL_READ,
            UserPermission.HOTEL_WRITE,
            UserPermission.FLIGHT_READ,
            UserPermission.FLIGHT_WRITE
    )),
    REG_USER(Sets.newHashSet(
            UserPermission.USER_READ,
            UserPermission.RESERVATION_READ,
            UserPermission.HOTEL_ROOM_READ,
            UserPermission.HOTEL_READ,
            UserPermission.FLIGHT_READ
    ));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> grantedAuthorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return grantedAuthorities;
    }
}
