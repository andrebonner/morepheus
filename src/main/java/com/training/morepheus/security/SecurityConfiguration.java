package com.training.morepheus.security;

import com.training.morepheus.dao.UserDAO;
import com.training.morepheus.security.jwt.JwtConfig;
import com.training.morepheus.security.jwt.JwtTokenVerifier;
import com.training.morepheus.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDAO userDAO;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @param passwordEncoder
     * @param userDAO
     * @param secretKey
     * @param jwtConfig
     */
    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder, UserDAO userDAO, SecretKey secretKey, JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            logger.warn(ex.getMessage());
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                        }
                )
                .and()
//                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", "/api/auth/**", "index", "/css/*","/js/*").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest()
                .authenticated()
        ;
    }

    /**
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * @return
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(username -> userDAO.getByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        format("User: %s, not found", username)
                )
        ));

        return provider;
    }

    // Expose authentication manager bean
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
