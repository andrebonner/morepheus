package com.training.morepheus.mappers;

import com.training.morepheus.dao.UserDAO;
import com.training.morepheus.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    private final UserDAO userDAO;

    public UserMapper(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();

        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setAccountNonExpired(rs.getBoolean("is_account_non_expired"));
        user.setEnabled(rs.getBoolean("is_enabled"));
        user.setAccountNonLocked(rs.getBoolean("is_account_non_locked"));
        user.setCredentialsNonExpired(rs.getBoolean("is_credentials_non_expired"));
        user.setRole(rs.getString("role"));

        user.setReservations(userDAO.getReservationsByUserId(user.getId()));

        return user;
    }
}
