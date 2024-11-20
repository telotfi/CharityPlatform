package com.example.userService.repositories;

import com.example.userService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author abdellah
 **/

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

}
