package com.example.donservice.repositories;

import com.example.donservice.entities.Don;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author abdellah
 **/
@Repository
public interface DonRepository extends JpaRepository<Don, Long> {

    List<Don> findByOrganisationId(Long organisationId);

    List<Don> findByIsAchievedFalse();

    List<Don> findByCurrentAmountGreaterThan(double amount);

//    @Query("SELECT d FROM Don d ORDER BY (d.montantToAchieve - d.currentAmount) ASC")
//    List<Don> findAllOrderByRemainingAmount();

    //Finding Progress of a Donation
//    @Query("SELECT (d.currentAmount / d.montantToAchieve) * 100 FROM Don d WHERE d.id = :donId")
//    Optional<Double> findDonationProgress(@Param("donId") Long donId);


}
