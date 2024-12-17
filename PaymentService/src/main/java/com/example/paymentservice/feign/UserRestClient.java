package com.example.paymentservice.feign;

import com.example.paymentservice.models.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author abdellah
 **/
@FeignClient(name = "UserService" ,path = "/api/users")
public interface UserRestClient {
    @GetMapping(path = "/{id}")
    @CircuitBreaker(name = "userService",fallbackMethod = ("getUserByid"))
    User getUserById(@PathVariable(name = "id") Long id);

default User getUserByid(Long id,Exception exception){
    User user = new User();
    user.setId(id);
    user.setName("not available");
    user.setEmail("not available");
    return user;
}
}
