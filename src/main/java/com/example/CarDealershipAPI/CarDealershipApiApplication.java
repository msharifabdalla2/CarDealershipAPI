package com.example.CarDealershipAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Base64;

@SpringBootApplication
public class CarDealershipApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarDealershipApiApplication.class, args);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()).httpBasic();
		return http.build();
	}

	// Basic authentication
	@Autowired
	public void configuredGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		String username = "user";
		String password = "password";
		String encodedpassword = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
		authenticationManagerBuilder.inMemoryAuthentication().withUser(username).password("{noop}" + encodedpassword).roles("user");
	}

}
