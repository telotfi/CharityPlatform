package com.example.donservice.web;

import com.example.donservice.dtos.DonDTO;
import com.example.donservice.dtos.DonOrgaDTO;
import com.example.donservice.dtos.UserDonDTO;
import com.example.donservice.dtos.UserDonDTOUSER;
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
import java.util.stream.Collectors;

import static com.example.donservice.feign.OrganisationRestClient.logger;

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
    public ResponseEntity<List<DonDTO>> getAllDons() {
        List<Don> dons = donService.getAllDons();
        dons.forEach(this::enrichDonDetails); // Enrich each Don with Organisation and User details
        List<DonDTO> donDTOs = dons.stream()
                .map(this::toDonDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(donDTOs);
    }

    // Get a single Don by ID with Organisation and User details
    @GetMapping("/{id}")
    public ResponseEntity<DonDTO> getDonById(@PathVariable Long id) {
        Don don = donService.getDonById(id);
        enrichDonDetails(don);
        DonDTO donDTO = toDonDTO(don);
//        Organisation organisation = organisationRestClient.getOrganisationById(don.getOrganisationId());
//        don.setOrganisation(organisation != null ? organisation : new Organisation());
//        DonDTO donDTO = toDonDTO(don);
        return ResponseEntity.ok(donDTO);
    }




    @GetMapping("/organisation/{organisationId}")
    public ResponseEntity<List<DonOrgaDTO>> getDonsByOrganisation(@PathVariable Long organisationId) {
        List<Don> dons = donService.getDonsByOrganisation(organisationId);
        List<DonOrgaDTO> donDTOs = dons.stream()
                .map(this::toDonOrgaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(donDTOs);
    }



    private void enrichDonDetails(Don don) {
        Organisation organisation = organisationRestClient.getOrganisationById(don.getOrganisationId());

        // Check if the organisation is the default fallback one
        if (organisation.getName().equals("Not Available")) {

            logger.warn("Fallback organisation for Don with ID: {}", don.getId());
        }

        don.setOrganisation(organisation != null ? organisation : new Organisation());

        // Enrich User details for UserDon objects if present
        if (don.getUserDons() != null) {
            don.getUserDons().forEach(userDon -> {
                User user = userRestClient.getUserById(userDon.getUserId());
                userDon.setUser(user != null ? user : new User());
            });
        }
    }


    private DonDTO toDonDTO(Don don) {
        DonDTO donDTO = new DonDTO();
        donDTO.setId(don.getId());
        donDTO.setOrganisationId(don.getOrganisationId());
        donDTO.setTitle(don.getTitle());
        donDTO.setDescription(don.getDescription());
        donDTO.setMontantToAchieve(don.getMontantToAchieve());
        donDTO.setCurrentAmount(don.getCurrentAmount());
        donDTO.setAchieved(don.isAchieved());
        donDTO.setOrganisation(don.getOrganisation());

        List<UserDonDTO> userDonDTOS = don.getUserDons().stream()
                .map(userDon -> new UserDonDTO(
                        userDon.getId(),
                        userDon.getAmount(),
                        userDon.getLocalDate(),
                        userDon.getUser()
                ))
                .collect(Collectors.toList());

        donDTO.setUserDonDTOS(userDonDTOS);

        return donDTO;
    }

    private DonOrgaDTO toDonOrgaDTO(Don don) {
        return new DonOrgaDTO(
                don.getId(),
                don.getOrganisationId(),
                don.getTitle(),
                don.getDescription(),
                don.getMontantToAchieve(),
                don.getCurrentAmount(),
                don.isAchieved()
        );
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
    public List<UserDonDTOUSER> getUserDonsByUserId(@PathVariable Long userId) {
        List<UserDon> userDonList= userDonRepository.findByUserId(userId);
        List<UserDonDTOUSER> userDonDTOList = userDonList.stream()
                .map(userDon -> new UserDonDTOUSER(
                        userDon.getId(),
                        organisationRestClient.getOrganisationById(userDon.getDon().getOrganisationId()).getName(),
                        userDon.getDon().getTitle(),
                        userDon.getAmount(),
                        userDon.getLocalDate()
                ))
                .collect(Collectors.toList());

        return userDonDTOList;
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

