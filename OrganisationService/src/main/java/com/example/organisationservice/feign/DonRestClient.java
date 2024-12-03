package com.example.organisationservice.feign;

import com.example.organisationservice.models.DonDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

/**
 * @author abdellah
 **/
@FeignClient(name = "DonService", path = "/api/dons")
public interface DonRestClient {
    Logger logger = LoggerFactory.getLogger(DonRestClient.class);

    @GetMapping("/organisation/{organisationId}")
    @CircuitBreaker(name = "DonService",fallbackMethod = ("getDonsByOrgaFallback"))
    List<DonDTO> getDonsByOrganisation(@PathVariable("organisationId") Long organisationId);

    default List<DonDTO> getDonsByOrgaFallback(Long organisationId, Throwable throwable) {
        // Log detailed error information
        logger.error("Error calling DonService for organisationId {}: {}", organisationId, throwable.getMessage());

        // Return an empty list or any other meaningful response
        return Collections.emptyList();
    }
}