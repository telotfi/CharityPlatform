package com.example.donservice.services;

import com.example.donservice.entities.Don;
import com.example.donservice.entities.UserDon;
import com.example.donservice.feign.OrganisationRestClient;
import com.example.donservice.models.Organisation;
import com.example.donservice.repositories.DonRepository;
import com.example.donservice.repositories.UserDonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new RuntimeException("Don not found"));
    }

    public Don createDon(Don don) {
        don.setCurrentAmount(0.0); // Ensure the initial amount is set
        return donRepository.save(don);
    }

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

    public UserDon addUserDonation(Long donId, UserDon userDon) {
        Don don = getDonById(donId);
        userDon.setDon(don);
        don.setCurrentAmount(don.getCurrentAmount() + userDon.getAmount());
        donRepository.save(don);
        return userDonRepository.save(userDon);
    }

    public double getDonationProgress(Long donId) {
        return donRepository.findDonationProgress(donId)
                .orElseThrow(() -> new RuntimeException("Donation not found or progress cannot be calculated"));
    }


    public List<Don> getDonsByOrganisation(Long organisationId) {
        return donRepository.findByOrganisationId(organisationId);
    }

    public void archiveAchievedDonations() {
        List<Don> achievedDons = donRepository.findByIsAchievedFalse()
                .stream()
                .filter(don -> don.getCurrentAmount() >= don.getMontantToAchieve())
                .toList();

        for (Don don : achievedDons) {
            don.setAchieved(true);
            donRepository.save(don);
        }
    }

    public Don getDonWithOrganisation(Long donId) {
        Don don = donRepository.findById(donId)
                .orElseThrow(() -> new RuntimeException("Donation not found"));

        Organisation organisation = organisationRestClient.getOrganisationById(don.getOrganisationId());
        don.setOrganisation(organisation);
        return don;
    }

}
