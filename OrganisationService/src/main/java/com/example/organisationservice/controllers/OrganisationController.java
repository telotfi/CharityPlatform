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
    public ResponseEntity<Organisation> updateOrganisation(@PathVariable Long id, @RequestBody Organisation updatedOrganisation) {
        Organisation organisation = organisationService.update(id, updatedOrganisation);
        return ResponseEntity.ok(organisation);
    }

    // Delete an organisation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganisation(@PathVariable Long id) {
        organisationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

