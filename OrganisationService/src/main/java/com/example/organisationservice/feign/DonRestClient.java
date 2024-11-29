package com.example.organisationservice.feign;

import com.example.organisationservice.models.DonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author abdellah
 **/
@FeignClient(name = "DonService", path = "/api/dons")
public interface DonRestClient {

    @GetMapping("/organisation/{organisationId}")
    List<DonDTO> getDonsByOrganisation(@PathVariable Long organisationId);
}