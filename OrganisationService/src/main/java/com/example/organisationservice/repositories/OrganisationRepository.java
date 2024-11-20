package com.example.organisationservice.repositories;

import com.example.organisationservice.entities.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author abdellah
 **/
@RepositoryRestResource
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

}
