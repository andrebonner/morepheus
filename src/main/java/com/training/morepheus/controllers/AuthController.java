package com.training.morepheus.controllers;

import com.training.morepheus.security.jwt.JwtConfig;
import com.training.morepheus.security.jwt.UsernameAndPasswordAuthenticationRequest;
import com.training.morepheus.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path="api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtConfig jwtConfig;

    public AuthController(UserService userService, JwtConfig jwtConfig) {
        this.userService = userService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping(path = "login")
    public ResponseEntity<Map<String, String>> handleLogin(@RequestBody UsernameAndPasswordAuthenticationRequest authenticationRequest){

        try{
            String token = userService.loginUser(authenticationRequest);
            HttpHeaders headers = new HttpHeaders();
                    headers.set(jwtConfig.getAuthorizationHeader(),jwtConfig.getTokenPrefix() + token);
            return ResponseEntity.ok().headers(headers).body(Map.of("token",token));
        } catch (BadCredentialsException ex){
            throw new IllegalStateException("Token was not created");
        }
    }
}
