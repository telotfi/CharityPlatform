package com.example.donservice;

import com.example.donservice.config.*;
import com.example.donservice.entities.Don;
import com.example.donservice.entities.UserDon;
import com.example.donservice.feign.UserRestClient;
import com.example.donservice.repositories.DonRepository;
import com.example.donservice.repositories.UserDonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(GlobalConfig.class)
public class DonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DonServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(DonRepository donRepository, UserDonRepository userDonRepository) {
        return args -> {
//            Don don1 = new Don( 1L, "Donation A", "Description A", 5000.0, 0.0, false);
//            donRepository.save(don1);
            // Create test UserDon entries
//            UserDon userDon1 = new UserDon();
//            userDon1.setDon(donRepository.findById(1L).get());
//            userDon1.setUserId(1L); // Replace with actual user ID
//            userDon1.setAmount(200.00);
//            userDon1.setLocalDate(LocalDate.now());
//            userDonRepository.save(userDon1);
//
//            UserDon userDon2 = new UserDon();
//            userDon2.setDon(donRepository.findById(1L).get());
//            userDon2.setUserId(2L); // Replace with actual user ID
//            userDon2.setAmount(300.00);
//            userDon2.setLocalDate(LocalDate.now());
//            userDonRepository.save(userDon2);

            donRepository.findAll().forEach(c -> {
                System.out.println(c.toString());
            });
            userDonRepository.findAll().forEach(userDon -> {
                System.out.println(userDon.toString());
            });

        };
    }
}