package com.training.morepheus;

import com.training.morepheus.dao.UserDAO;
import com.training.morepheus.models.User;
import com.training.morepheus.security.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class MorepheusApplication {

	public static void main(String[] args) {
		SpringApplication.run(MorepheusApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		config.setExposedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
				"Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	/**
	 * Used to call command line runner
	 */
	@Configuration
	class UserConfig {

		@Bean
		CommandLineRunner commandLineRunner(UserDAO userDAO, PasswordEncoder passwordEncoder){
			return args -> {
					userDAO.create(new User(
							"andre@email.com",
							passwordEncoder.encode("password"),
							UserRole.REG_USER.name(),
							UserRole.REG_USER.getGrantedAuthorities(),
							true,
							true,
							true,
							true
					));

					userDAO.create(new User(
							"regular@email.com",
							passwordEncoder.encode("password"),
							UserRole.REG_USER.name(),
							UserRole.REG_USER.getGrantedAuthorities(),
							true,
							true,
							true,
							true
					));

					userDAO.create(new User(
							"admin@email.com",
							passwordEncoder.encode("password"),
							UserRole.ADMIN.name(),
							UserRole.ADMIN.getGrantedAuthorities(),
							true,
							true,
							true,
							true
					));
			};
		}
	}

}
