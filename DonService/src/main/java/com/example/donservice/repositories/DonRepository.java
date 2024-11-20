package com.example.donservice.repositories;

import com.example.donservice.entities.Don;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author abdellah
 **/
@RepositoryRestResource
public interface DonRepository extends JpaRepository<Don,Long> {

}
