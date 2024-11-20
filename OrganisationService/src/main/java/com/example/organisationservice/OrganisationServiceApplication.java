package com.example.organisationservice;

import com.example.organisationservice.entities.Organisation;
import com.example.organisationservice.repositories.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@EnableConfigurationProperties
public class OrganisationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganisationServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(OrganisationRepository organisationRepository, RepositoryRestConfiguration restConfiguration) {
        restConfiguration.exposeIdsFor(Organisation.class);
        return args -> {
//            Organisation org1 = new Organisation(null, "Organisation 4", "Description for Organisation 4", "contact1@example.com", "123-456-7890", "123 Example St", false);
//            Organisation org2 = new Organisation(null, "Organisation 5", "Description for Organisation 5", "contact2@example.com", "987-654-3210", "456 Sample Ave", true);
//            Organisation org3 = new Organisation(null, "Organisation 6", "Description for Organisation 6", "contact3@example.com", "555-555-5555", "789 Test Blvd", false);
//
//            organisationRepository.save(org1);
//            organisationRepository.save(org2);
//            organisationRepository.save(org3);

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