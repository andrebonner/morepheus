package com.training.morepheus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.morepheus.security.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class User implements UserDetails {

    private Long id;
    private String username;

    private String password;
    private String role;
    private Set<? extends GrantedAuthority> grantedAuthorities;
    private  boolean isAccountNonExpired;
    private  boolean isAccountNonLocked;
    private  boolean isCredentialsNonExpired;
    private  boolean isEnabled;
    private List<Reservation> reservations;

    public User() {
    }

    /**
     * @param username
     * @param password
     * @param role
     * @param grantedAuthorities
     * @param isAccountNonExpired
     * @param isAccountNonLocked
     * @param isCredentialsNonExpired
     * @param isEnabled
     */
    public User(
            String username,
            String password,
            String role,
            Set<? extends GrantedAuthority> grantedAuthorities,
            boolean isAccountNonExpired,
            boolean isAccountNonLocked,
            boolean isCredentialsNonExpired,
            boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    /**
     * @param username
     * @param password
     * @param role
     * @param grantedAuthorities
     * @param isAccountNonExpired
     * @param isAccountNonLocked
     * @param isCredentialsNonExpired
     * @param isEnabled
     */
    public User(
            String username,
            String password,
            String role,
            Set<? extends GrantedAuthority> grantedAuthorities,
            boolean isAccountNonExpired,
            boolean isAccountNonLocked,
            boolean isCredentialsNonExpired,
            boolean isEnabled, List<Reservation> reservations) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserRole ur = UserRole.valueOf(this.getRole()) ;
        Collection<SimpleGrantedAuthority> grantedAuthorities = ur.getGrantedAuthorities();

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setGrantedAuthorities(Set<? extends GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
