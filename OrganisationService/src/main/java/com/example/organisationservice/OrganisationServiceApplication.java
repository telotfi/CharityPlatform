package com.example.organisationservice;

import com.example.organisationservice.entities.Organisation;
import com.example.organisationservice.repositories.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties
@EnableFeignClients
public class OrganisationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganisationServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(OrganisationRepository organisationRepository) {
        return args -> {

//            Organisation org1 = new Organisation(
//                    null,
//                    "Charity Org 1",
//                    "A charity organization for good causes.",
//                    "contact@charityorg1.com",
//                    "+123456789",
//                    "123 Street, City, Country",
//                    true
//            );
//            organisationRepository.save(org1);
            organisationRepository.findAll().forEach(org -> {
                System.out.println(org.toString());
            });
        };
//    @Override
//    public void run(String... args) throws Exception {
//        Organisation org1 = new Organisation(null, "Organisation One", "Description for Organisation One", "contact1@example.com", "123-456-7890", "123 Example St", false);
//        Organisation org2 = new Organisation(null, "Organisation Two", "Description for Organisation Two", "contact2@example.com", "987-654-3210", "456 Sample Ave", true);
//        Organisation org3 = new Organisation(null, "Organisation Three", "Description for Organisation Three", "contact3@example.com", "555-555-5555", "789 Test Blvd", false);
//
//        // Save sample organisations to the database
////        organisationRepository.save(org1);
////        organisationRepository.save(org2);
////        organisationRepository.save(org3);
//
//        // Print all organisations to the console
//        organisationRepository.findAll().forEach(org -> {
//            System.out.println(org.toString());
//        });

    }
}