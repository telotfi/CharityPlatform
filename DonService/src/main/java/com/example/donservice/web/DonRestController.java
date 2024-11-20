package com.example.donservice.web;

import com.example.donservice.entities.Don;
import com.example.donservice.feign.OrganisationRestClient;
import com.example.donservice.feign.UserRestClient;
import com.example.donservice.models.Organisation;
import com.example.donservice.models.User;
import com.example.donservice.repositories.DonRepository;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author abdellah
 **/
@RestController
public class DonRestController  {
    private DonRepository donRepository;
    private UserRestClient userRestClient;
    private OrganisationRestClient organisationRestClient;

    public DonRestController(DonRepository donRepository, UserRestClient userRestClient, OrganisationRestClient organisationRestClient) {
        this.donRepository = donRepository;
        this.userRestClient = userRestClient;
        this.organisationRestClient = organisationRestClient;
    }
    @GetMapping(path = "/dons/{id}")
    public Don getDon(@PathVariable Long id){
        // Use orElseThrow to directly retrieve or throw an exception if not found
        Don don = donRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Don with id " + id + " not found"));

        // Fetch and set user and organisation data, handling null cases
        User user = userRestClient.getUserById(don.getUserId());
        don.setUser(user != null ? user : new User());  // Default to an empty User if not found

        Organisation organisation = organisationRestClient.getOrganisationById(don.getOrganisationId());
        don.setOrganisation(organisation != null ? organisation : new Organisation());  // Default to an empty Organisation if not found
        return don;
    }
    @GetMapping(path = "/dons")
    public List<Don> donsList(){
        // Use a lambda to set the user and organisation for each donation
        List<Don> dons = donRepository.findAll();
        dons.forEach(don -> {
            User user = userRestClient.getUserById(don.getUserId());
            don.setUser(user != null ? user : new User());  // Default to an empty User if not found

            Organisation organisation = organisationRestClient.getOrganisationById(don.getOrganisationId());
            don.setOrganisation(organisation != null ? organisation : new Organisation());  // Default to an empty Organisation if not found
        });
//        donRepository.findAll().forEach(don -> {
//            don.setUser(userRestClient.getUserById(don.getUserId()));
//            don.setOrganisation(organisationRestClient.getOrganisationById(don.getOrganisationId()));
//        });
        return donRepository.findAll();
    }
}
