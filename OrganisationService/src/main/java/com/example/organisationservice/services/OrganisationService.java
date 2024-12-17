package com.example.organisationservice.services;

import com.example.organisationservice.entities.Organisation;
import com.example.organisationservice.feign.DonRestClient;
import com.example.organisationservice.models.DonDTO;
import com.example.organisationservice.repositories.OrganisationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author abdellah
 **/
@Service
public class OrganisationService {
    @Autowired
    private DonRestClient donRestClient;
    @Autowired
    private OrganisationRepository organisationRepository;

    public OrganisationService(DonRestClient donRestClient, OrganisationRepository organisationRepository) {
        this.donRestClient = donRestClient;
        this.organisationRepository = organisationRepository;
    }


    public Organisation findById(Long id) {
        Organisation organisation = organisationRepository.findById(id).get();

        // Fetch related dons and set them on the Organisation entity
        List<DonDTO> dons = donRestClient.getDonsByOrganisation(id);
        organisation.setDons(dons);

        return organisation;
    }
    public Organisation findByIdtoEdit(Long id) {
        return organisationRepository.findById(id)
                .orElse(null); // Handle null in the controller for meaningful errors
    }



//    public List<Organisation> findAll() {
//        List<Organisation> organisations = organisationRepository.findAll();
//
//        // Fetch related dons for each organisation
//        organisations.forEach(organisation -> {
//            List<DonDTO> dons = donRestClient.getDonsByOrganisation(organisation.getId());
//            organisation.setDons(dons);
//        });
//
//        return organisations;
//    }
private static final Logger logger = LoggerFactory.getLogger(OrganisationService.class);

    @Transactional
    public List<Organisation> findAll() {
    List<Organisation> organisations = organisationRepository.findAll();

    // Fetch related dons for each organisation
    organisations.forEach(organisation -> {
        List<DonDTO> dons = donRestClient.getDonsByOrganisation(organisation.getId());

        if (dons.isEmpty()) {
            // Log or handle empty donation list from fallback

            logger.warn("No donations found for organisation ID: {}", organisation.getId());
        }

        organisation.setDons(dons);
    });

    return organisations;
}
    @Transactional
    public Organisation save(Organisation organisation) {
        return organisationRepository.save(organisation);
    }



    @Transactional
    public void delete(Long id) {
        Organisation organisation = findById(id);
        organisationRepository.delete(organisation);
    }
}
