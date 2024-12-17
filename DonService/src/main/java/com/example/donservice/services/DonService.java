//package com.example.donservice.services;
//
//import com.example.donservice.entities.Don;
//import com.example.donservice.entities.UserDon;
//import com.example.donservice.feign.OrganisationRestClient;
//import com.example.donservice.models.Organisation;
//import com.example.donservice.repositories.DonRepository;
//import com.example.donservice.repositories.UserDonRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author abdellah
// **/
//@Service
//public class DonService {
//
//    private final DonRepository donRepository;
//    private final UserDonRepository userDonRepository;
//    private final OrganisationRestClient organisationRestClient;
//
//
//    public DonService(DonRepository donRepository, UserDonRepository userDonRepository, OrganisationRestClient organisationRestClient) {
//        this.donRepository = donRepository;
//        this.userDonRepository = userDonRepository;
//        this.organisationRestClient = organisationRestClient;
//    }
//
//    public List<Don> getAllDons() {
//        return donRepository.findAll();
//    }
//
//    public Don getDonById(Long id) {
//        return donRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Don not found"));
//    }
//
//    public Don createDon(Don don) {
//        don.setCurrentAmount(0.0); // Ensure the initial amount is set
//        return donRepository.save(don);
//    }
//
//    public Don updateDon(Long id, Don updatedDon) {
//        Don existingDon = getDonById(id);
//        existingDon.setTitle(updatedDon.getTitle());
//        existingDon.setDescription(updatedDon.getDescription());
//        existingDon.setMontantToAchieve(updatedDon.getMontantToAchieve());
//        return donRepository.save(existingDon);
//    }
//
//    public void deleteDon(Long id) {
//        donRepository.deleteById(id);
//    }
//
//    public UserDon addUserDonation(Long donId, UserDon userDon) {
//        Don don = getDonById(donId);
//        userDon.setDon(don);
//        don.setCurrentAmount(don.getCurrentAmount() + userDon.getAmount());
//        donRepository.save(don);
//        return userDonRepository.save(userDon);
//    }
//
////    public double getDonationProgress(Long donId) {
////        return donRepository.findDonationProgress(donId)
////                .orElseThrow(() -> new RuntimeException("Donation not found or progress cannot be calculated"));
////    }
//
//
//    public List<Don> getDonsByOrganisation(Long organisationId) {
//        return donRepository.findByOrganisationId(organisationId);
//    }
//
//    public void archiveAchievedDonations() {
//        List<Don> achievedDons = donRepository.findByIsAchievedFalse()
//                .stream()
//                .filter(don -> don.getCurrentAmount() >= don.getMontantToAchieve())
//                .toList();
//
//        for (Don don : achievedDons) {
//            don.setAchieved(true);
//            donRepository.save(don);
//        }
//    }
//
//    public Don getDonWithOrganisation(Long donId) {
//        Don don = donRepository.findById(donId)
//                .orElseThrow(() -> new RuntimeException("Donation not found"));
//
//        Organisation organisation = organisationRestClient.getOrganisationById(don.getOrganisationId());
//        don.setOrganisation(organisation);
//        return don;
//    }
//
//}
package com.example.donservice.services;

import com.example.donservice.entities.Don;
import com.example.donservice.entities.UserDon;
import com.example.donservice.exception.DonNotFoundException;
import com.example.donservice.feign.OrganisationRestClient;
import com.example.donservice.models.Organisation;
import com.example.donservice.repositories.DonRepository;
import com.example.donservice.repositories.UserDonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author abdellah
 **/
@Service
public class DonService {

    private final DonRepository donRepository;
    private final UserDonRepository userDonRepository;
    private final OrganisationRestClient organisationRestClient;

    public DonService(DonRepository donRepository, UserDonRepository userDonRepository, OrganisationRestClient organisationRestClient) {
        this.donRepository = donRepository;
        this.userDonRepository = userDonRepository;
        this.organisationRestClient = organisationRestClient;
    }

    public List<Don> getAllDons() {
        return donRepository.findAll();
    }

    public Don getDonById(Long id) {
        return donRepository.findById(id)
                .orElseThrow(() -> new DonNotFoundException("Don with ID " + id + " not found"));
    }

    public UserDon getUserDonById(Long id) {
        return userDonRepository.findById(id)
                .orElseThrow(() -> new DonNotFoundException("UserDon with ID " + id + " not found"));
    }

    public Don createDon(Don don) {
        don.setCurrentAmount(0.0); // Ensure the initial amount is set
        return donRepository.save(don);
    }

    @Transactional
    public Don updateDon(Long id, Don updatedDon) {
        Don existingDon = getDonById(id);
        existingDon.setTitle(updatedDon.getTitle());
        existingDon.setDescription(updatedDon.getDescription());
        existingDon.setMontantToAchieve(updatedDon.getMontantToAchieve());
        return donRepository.save(existingDon);
    }

    public void deleteDon(Long id) {
        donRepository.deleteById(id);
    }

    @Transactional
    public UserDon addUserDonation(Long donId, UserDon userDon) {
        Don don = getDonById(donId);
        userDon.setDon(don);
        don.setCurrentAmount(don.getCurrentAmount() + userDon.getAmount());
        donRepository.save(don);  // Save the updated Don
        return userDonRepository.save(userDon);  // Save the new UserDon
    }

    public List<Don> getDonsByOrganisation(Long organisationId) {
        return donRepository.findByOrganisationId(organisationId);
    }

    @Transactional
    public void archiveAchievedDonations() {
        List<Don> achievedDons = donRepository.findByIsAchievedFalse()
                .stream()
                .filter(don -> don.getCurrentAmount() >= don.getMontantToAchieve())
                .collect(Collectors.toList());

        for (Don don : achievedDons) {
            don.setAchieved(true);
        }
        donRepository.saveAll(achievedDons);  // Save all the modified donations in bulk
    }

    @Transactional
    public Don getDonWithOrganisation(Long donId) {
        Don don = donRepository.findById(donId)
                .orElseThrow(() -> new DonNotFoundException("Don with ID " + donId + " not found"));

        Organisation organisation = organisationRestClient.getOrganisationById(don.getOrganisationId());
        if (organisation == null) {
            throw new RuntimeException("Organisation not found for Don ID: " + donId);
        }
        don.setOrganisation(organisation);
        return don;
    }


    public void handleDonation(Long donationId, Long userId, double amount) {
        Don don = donRepository.findById(donationId)
                .orElseThrow(() -> new RuntimeException("Don not found with id: " + donationId));

        // Simulate user donation and update don
        UserDon userDon = new UserDon();
        userDon.setDon(don);
        userDon.setUserId(userId);
        userDon.setAmount(amount); // Example: full payment
        userDon.setLocalDate(LocalDate.now());

        userDonRepository.save(userDon);

        // Update Don's current amount and achievement status
        don.updateCurrentAmount(userDon.getAmount());
        donRepository.save(don);
    }

}
