package com.example.discoveryclientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiscoveryClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryClientServiceApplication.class, args);
	}

}
