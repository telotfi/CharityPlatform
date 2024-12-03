package com.example.organisationservice.repositories;

import com.example.organisationservice.entities.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author abdellah
 **/
@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

}
