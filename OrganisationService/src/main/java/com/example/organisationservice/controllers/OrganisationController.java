package com.example.organisationservice.controllers;

import com.example.organisationservice.entities.Organisation;
import com.example.organisationservice.services.OrganisationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author abdellah
 **/
@RestController
@RequestMapping("/api/organisations")
public class OrganisationController {

    private final OrganisationService organisationService;

    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    // Get an organisation by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Organisation> getOrganisationById(@PathVariable Long id) {
        Organisation organisation = organisationService.findById(id);
        return ResponseEntity.ok(organisation);
    }

    // Get all organisations
    @GetMapping
    public ResponseEntity<List<Organisation>> getAllOrganisations() {
        List<Organisation> organisations = organisationService.findAll();
        return ResponseEntity.ok(organisations);
    }

    // Create a new organisation
    @PostMapping
    public ResponseEntity<Organisation> createOrganisation(@RequestBody Organisation organisation) {
        Organisation createdOrganisation = organisationService.save(organisation);
        return ResponseEntity.status(201).body(createdOrganisation);
    }

    // Update an existing organisation
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrganisation(@PathVariable Long id, @RequestBody Organisation updatedOrganisation) {
        Organisation existingOrganisation = organisationService.findByIdtoEdit(id);
        if (existingOrganisation == null) {
            return ResponseEntity.status(404).body("Organisation not found.");
        }
        // Update the fields of the existing organisation
        if (updatedOrganisation.getName() != null) {
            existingOrganisation.setName(updatedOrganisation.getName());
        }
        if (updatedOrganisation.getDescription() != null) {
            existingOrganisation.setDescription(updatedOrganisation.getDescription());
        }
        if (updatedOrganisation.getAddress() != null) {
            existingOrganisation.setAddress(updatedOrganisation.getAddress());
        }
        if (updatedOrganisation.getContactEmail() != null) {
            existingOrganisation.setContactEmail(updatedOrganisation.getContactEmail());
        }
        if (updatedOrganisation.getPhoneNumber() != null) {
            existingOrganisation.setPhoneNumber(updatedOrganisation.getPhoneNumber());
        }


        Organisation savedOrganisation = organisationService.save(existingOrganisation);
        return ResponseEntity.ok(savedOrganisation);
    }


    // Delete an organisation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganisation(@PathVariable Long id) {
        organisationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

