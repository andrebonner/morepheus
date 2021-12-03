package com.training.morepheus.services;

import com.training.morepheus.dao.DAO;
import com.training.morepheus.dao.UserDAO;
import com.training.morepheus.models.User;
import com.training.morepheus.security.jwt.JwtConfig;
import com.training.morepheus.security.jwt.UsernameAndPasswordAuthenticationRequest;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService, ApiService<User,Long> {

    private DAO<User, Long> dao;
    private UserDAO userDAO;
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final PasswordEncoder passwordEncoder;

    /**
     * @param dao
     * @param userDAO
     * @param authenticationManager
     * @param jwtConfig
     * @param secretKey
     * @param passwordEncoder
     */
    @Autowired
    public UserService(DAO<User, Long> dao, UserDAO userDAO, AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.userDAO = userDAO;
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param page
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<User> getAll(Long page, String sort, String order) {
        return dao.getAll(page, sort, order);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User getById(Long id) {
        Optional<User> user = dao.getById(id);
        return user.get();
    }

    /**
     * @param user
     * @return
     */
    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return dao.create(user);
    }

    /**
     * @param id
     * @param user
     * @return
     */
    @Override
    public User update(Long id, User user) {
        return dao.update(id, user);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
          return userDAO.getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User "+username+" does not exist!"));
    }

    public String loginUser(UsernameAndPasswordAuthenticationRequest authRequest) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(), authRequest.getPassword()));
        User user = (User) authenticate.getPrincipal();

        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("authorities", user.getAuthorities())
                .setIssuedAt(new Date())
                .setIssuer(jwtConfig.getIssuer())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey).compact();

        return token;
    }
}
