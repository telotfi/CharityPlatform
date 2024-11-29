package com.example.organisationservice.services;

import com.example.organisationservice.entities.Organisation;
import com.example.organisationservice.feign.DonRestClient;
import com.example.organisationservice.models.DonDTO;
import com.example.organisationservice.repositories.OrganisationRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author abdellah
 **/
@Service
public class OrganisationService {
    private final DonRestClient donRestClient;
    private final OrganisationRepository organisationRepository;

    public OrganisationService(DonRestClient donRestClient, OrganisationRepository organisationRepository) {
        this.donRestClient = donRestClient;
        this.organisationRepository = organisationRepository;
    }

    public Organisation findById(Long id) {
        Organisation organisation = organisationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organisation not found with id: " + id));

        // Fetch related dons and set them on the Organisation entity
        List<DonDTO> dons = donRestClient.getDonsByOrganisation(id);
        organisation.setDons(dons);

        return organisation;
    }

    public List<Organisation> findAll() {
        List<Organisation> organisations = organisationRepository.findAll();

        // Fetch related dons for each organisation
        organisations.forEach(organisation -> {
            List<DonDTO> dons = donRestClient.getDonsByOrganisation(organisation.getId());
            organisation.setDons(dons);
        });

        return organisations;
    }

    public Organisation save(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    public Organisation update(Long id, Organisation updatedOrganisation) {
        Organisation existingOrganisation = findById(id);
        existingOrganisation.setName(updatedOrganisation.getName());
        existingOrganisation.setDescription(updatedOrganisation.getDescription());
        existingOrganisation.setContactEmail(updatedOrganisation.getContactEmail());
        existingOrganisation.setPhoneNumber(updatedOrganisation.getPhoneNumber());
        existingOrganisation.setAddress(updatedOrganisation.getAddress());
        existingOrganisation.setVerified(updatedOrganisation.isVerified());
        return organisationRepository.save(existingOrganisation);
    }

    public void delete(Long id) {
        Organisation organisation = findById(id);
        organisationRepository.delete(organisation);
    }
}
