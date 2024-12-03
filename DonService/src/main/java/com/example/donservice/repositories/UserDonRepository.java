package com.example.donservice.repositories;

import com.example.donservice.entities.UserDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @author abdellah
 **/
@Repository
public interface UserDonRepository extends JpaRepository<UserDon, Long> {

    // Find all user donations for a specific Don
    List<UserDon> findByDonId(Long donId);

    // Find all donations made by a specific user
    List<UserDon> findByUserId(Long userId);

    // Find the total amount donated by a user across all donations
//    @Query("SELECT SUM(ud.amount) FROM UserDon ud WHERE ud.userId = :userId")
//    Double findTotalDonatedByUser(@Param("userId") Long userId);


}
