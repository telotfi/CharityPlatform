package com.example.userService;

import com.example.userService.config.GlobalConfig;
import com.example.userService.entities.User;
import com.example.userService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class userServiceApplication implements CommandLineRunner{
    @Autowired
    private UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(userServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


//        for (int i = 1; i <= 10; i++) {
//            User user = new User(i, "user" + i, "user" + i + "@mail.com");
//            userRepository.save(user);
//        }
        userRepository.findAll().forEach(c->{
                System.out.println(c.toString());
            });

        };
    }

