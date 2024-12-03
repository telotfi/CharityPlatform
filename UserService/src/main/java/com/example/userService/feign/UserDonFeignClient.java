package com.example.userService.feign;

import com.example.userService.models.UserDonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author abdellah
 **/
@FeignClient(name = "DonService", path = "/api/dons")
public interface UserDonFeignClient {
    @GetMapping("/user-dons/{userId}")
    List<UserDonDTO> getUserDonsByUserId(@PathVariable Long userId);
}
