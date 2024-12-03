package com.example.userService;

import com.example.userService.config.GlobalConfig;
import com.example.userService.entities.User;
import com.example.userService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
@EnableFeignClients
public class userServiceApplication{

    public static void main(String[] args) {
        SpringApplication.run(userServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserRepository userRepository){
        return args -> {
//            for (int i = 1; i <= 10; i++) {
//            User user = new User("user" + i, "user" + i + "@mail.com");
//            userRepository.save(user);
//        }
//            User user = new User(1L, "user" + 1, "user" + 1 + "@mail.com");
//            userRepository.save(user);
        userRepository.findAll().forEach(c->{
                System.out.println(c.toString());
            });
        };
    }
//    @Override
//    public void run(String... args) throws Exception {
//
//
//        for (int i = 1; i <= 10; i++) {
//            User user = new User((long) i, "user" + i, "user" + i + "@mail.com");
//            userRepository.save(user);
//        }
//        userRepository.findAll().forEach(c->{
//                System.out.println(c.toString());
//            });
//
//        };
    }

