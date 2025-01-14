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
}
