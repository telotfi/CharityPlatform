package com.example.userService.repositories;

import com.example.userService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author abdellah
 **/

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
