//package com.example.donservice.web;
//
//import com.example.donservice.entities.Don;
//import com.example.donservice.feign.OrganisationRestClient;
//import com.example.donservice.feign.UserRestClient;
//import com.example.donservice.models.Organisation;
//import com.example.donservice.models.User;
//import com.example.donservice.repositories.DonRepository;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Optional;
//
///**
// * @author abdellah
// **/
//@RestController
//public class DonRestController  {
//    private DonRepository donRepository;
//    private UserRestClient userRestClient;
//    private OrganisationRestClient organisationRestClient;
//
//    public DonRestController(DonRepository donRepository, UserRestClient userRestClient, OrganisationRestClient organisationRestClient) {
//        this.donRepository = donRepository;
//        this.userRestClient = userRestClient;
//        this.organisationRestClient = organisationRestClient;
//    }
//    @GetMapping(path = "/dons/{id}")
//    public Don getDon(@PathVariable Long id){
//        Don don = donRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("Don with id " + id + " not found"));
//
//        User user = userRestClient.getUserById(don.getUserId());
//        don.setUser(user != null ? user : new User());
//
//        Organisation organisation = organisationRestClient.getOrganisationById(don.getOrganisationId());
//        don.setOrganisation(organisation != null ? organisation : new Organisation());
//        return don;
//    }
//    @GetMapping(path = "/dons")
//    public List<Don> donsList(){
//        // Use a lambda to set the user and organisation for each donation
//        List<Don> dons = donRepository.findAll();
//        dons.forEach(don -> {
//            User user = userRestClient.getUserById(don.getUserId());
//            don.setUser(user != null ? user : new User());
//
//            Organisation organisation = organisationRestClient.getOrganisationById(don.getOrganisationId());
//            don.setOrganisation(organisation != null ? organisation : new Organisation());
//        });
////        donRepository.findAll().forEach(don -> {
////            don.setUser(userRestClient.getUserById(don.getUserId()));
////            don.setOrganisation(organisationRestClient.getOrganisationById(don.getOrganisationId()));
////        });
//        return donRepository.findAll();
//    }
//}

package com.example.donservice.web;

import com.example.donservice.entities.Don;
import com.example.donservice.entities.UserDon;
import com.example.donservice.feign.OrganisationRestClient;
import com.example.donservice.feign.UserRestClient;
import com.example.donservice.models.Organisation;
import com.example.donservice.models.User;
import com.example.donservice.repositories.UserDonRepository;
import com.example.donservice.services.DonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 */
@RestController
@RequestMapping("/api/dons")
public class DonRestController {

    private final DonService donService;
    private final UserRestClient userRestClient;
    private final OrganisationRestClient organisationRestClient;
    private final UserDonRepository userDonRepository;

    public DonRestController(DonService donService, UserRestClient userRestClient, OrganisationRestClient organisationRestClient, UserDonRepository userDonRepository) {
        this.donService = donService;
        this.userRestClient = userRestClient;
        this.organisationRestClient = organisationRestClient;
        this.userDonRepository = userDonRepository;
    }

    // Get all Dons with Organisation and User details
    @GetMapping
    public ResponseEntity<List<Don>> getAllDons() {
        List<Don> dons = donService.getAllDons();
        dons.forEach(this::enrichDonDetails); // Enrich each Don with Organisation and User details
        return ResponseEntity.ok(dons);
    }

    // Get a single Don by ID with Organisation and User details
    @GetMapping("/{id}")
    public ResponseEntity<Don> getDonById(@PathVariable Long id) {
        Don don = donService.getDonById(id);
        enrichDonDetails(don);
        return ResponseEntity.ok(don);
    }

    // Get all Dons for a specific Organisation
    @GetMapping("/organisation/{organisationId}")
    public ResponseEntity<List<Don>> getDonsByOrganisation(@PathVariable Long organisationId) {
        List<Don> dons = donService.getDonsByOrganisation(organisationId);
        dons.forEach(this::enrichDonDetails);
        return ResponseEntity.ok(dons);
    }

    // Enrich a Don with Organisation and User details
    private void enrichDonDetails(Don don) {
        Organisation organisation = organisationRestClient.getOrganisationById(don.getOrganisationId());
        don.setOrganisation(organisation != null ? organisation : new Organisation());

        if (don.getUserDons() != null) {
            don.getUserDons().forEach(userDon -> {
                User user = userRestClient.getUserById(userDon.getUserId());
                userDon.setUser(user != null ? user : new User());
            });
        }
    }

    // Add a User Donation to a Don
    @PostMapping("/{donId}/user-donations")
    public ResponseEntity<UserDon> addUserDonation(@PathVariable Long donId, @RequestBody UserDon userDon) {
        User user = userRestClient.getUserById(userDon.getUserId());
        if (user == null) {
            return ResponseEntity.badRequest().body(null); // Return a bad request if user is not found
        }
        UserDon savedUserDon = donService.addUserDonation(donId, userDon);
        savedUserDon.setUser(user); // Attach user details to the response
        return ResponseEntity.ok(savedUserDon);
    }

    @GetMapping("/user-dons/{userId}")
    public List<UserDon> getUserDonsByUserId(@PathVariable Long userId) {
        return userDonRepository.findByUserId(userId);
    }
    // Archive Achieved Donations
    @PostMapping("/archive-achieved")
    public ResponseEntity<Void> archiveAchievedDonations() {
        donService.archiveAchievedDonations();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/organisation")
    public Don getDonWithOrganisation(@PathVariable Long id) {
        return donService.getDonWithOrganisation(id);
    }

}

