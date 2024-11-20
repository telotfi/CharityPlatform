package com.example.donservice;

import com.example.donservice.config.*;
import com.example.donservice.entities.Don;
import com.example.donservice.feign.UserRestClient;
import com.example.donservice.repositories.DonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(GlobalConfig.class)
public class DonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DonServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(DonRepository donRepository, RepositoryRestConfiguration restConfiguration, UserRestClient userRestClient) {
        restConfiguration.exposeIdsFor(Don.class);
        return args -> {
//            Don don = new Don(null,1L,1L,2999.0, LocalDate.now());
//            Don don2 = new Don(null,2L,2L,5000.0,LocalDate.now());
//            Don don3 = new Don(null,3L,3L,250.0,LocalDate.now());
//            donRepository.save(don);
//            donRepository.save(don2);
//            donRepository.save(don3);
            //UserRepository.save(new User(null, "MOHAMAD","MOHAMAD@gmail.com"));
            //donRepository.save(new Don(null, 1L, 99.0, LocalDate.now()));
//            donRepository.save(new Don(null, 2L, 199.0, LocalDate.now()));
//            User user1 = new User();
//            User user2 = userRestClient.getUserById(5L);
//            Organisation organisation =new Organisation();
//            Don don1 =new Don(null, 2L, 1L, 299.0, LocalDate.now(),user2,organisation);
//            donRepository.save(don1);
//            //donRepository.save(new Don(null, 3L, 2L, 199.0, LocalDate.now()));
//            System.out.println(user2.toString());
            //donRepository.save(new Don(null,5L,3L,999d,LocalDate.now()));
            donRepository.findAll().forEach(c -> {
                System.out.println(c.toString());
            });

        };
    }
}