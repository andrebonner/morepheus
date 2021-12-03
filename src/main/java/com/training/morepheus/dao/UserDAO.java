package com.training.morepheus.dao;

import com.training.morepheus.mappers.ReservationMapper;
import com.training.morepheus.mappers.UserMapper;
import com.training.morepheus.models.Reservation;
import com.training.morepheus.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAO implements DAO<User, Long>{

    private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
    private JdbcTemplate jdbcTemplate;


    /**
     * @param jdbcTemplate
     */
    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    /**
     * @param page
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<User> getAll(Long page, String sort, String order) {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql,new UserMapper(this));
    }

    /**
     * @param id
     * @return
     */
    public List<Reservation> getReservationsByUserId(Long id){
        String sql = "SELECT * FROM reservations WHERE user_id = ?";

        return jdbcTemplate.query(sql, new Object[]{id},new ReservationMapper());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<User> getById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try {
            user = jdbcTemplate.queryForObject(sql,new Object[]{id},new UserMapper(this));
        } catch (DataAccessException ex) {
            log.info("User not found");
        }

        return Optional.ofNullable(user);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public User create(User user) {
        String sql = "INSERT INTO users ( is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password, role, username) VALUES (?,?,?,?,?,?,?)";

        int insert = jdbcTemplate.update(sql,user.isAccountNonExpired(),user.isAccountNonLocked(),user.isCredentialsNonExpired(),user.isEnabled(),user.getPassword(), user.getRole(), user.getUsername());
        if(insert==1){
            log.info("New User created : " + user.getUsername());
        }

        return user;
    }

    /**
     * @param id
     * @param user
     * @return
     */
    @Override
    public User update(Long id, User user) {
        String sql = "UPDATE users SET is_account_non_expired=?, is_account_non_locked=?, is_credentials_non_expired=?, is_enabled=?, password=?, role=?, username=? WHERE id=?";

        int update = jdbcTemplate.update(sql,user.isAccountNonExpired(),user.isAccountNonLocked(),user.isCredentialsNonExpired(),user.isEnabled(),user.getPassword(), user.getRole(), user.getUsername(), id);
        if(update==1){
            log.info("User updated : "+ user.getUsername());
        }

        return user;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        String sql ="DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

    /**
     * @param username
     * @return
     */
    public Optional<User> getByUsername(String username){
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = null;

        try {
            user = jdbcTemplate.queryForObject(sql,new Object[]{username},new UserMapper(this));
        } catch (DataAccessException ex) {
            log.info("User not found");
        }

        return Optional.ofNullable(user);
    }

}
