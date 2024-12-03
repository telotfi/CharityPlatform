package com.example.donservice.feign;

import com.example.donservice.models.Organisation;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author abdellah
 **/
@FeignClient(name = "OrganisationService" ,path = "/api/organisations")
public interface OrganisationRestClient {
    Logger logger = LoggerFactory.getLogger(OrganisationRestClient.class);

    @GetMapping(path = "/{id}")
    @CircuitBreaker(name = "organisationService",fallbackMethod = ("getOrgaByid"))
    Organisation getOrganisationById(@PathVariable(name = "id") Long id);

    // Fix the fallback method signature to match the Feign method's parameters
    default Organisation getOrgaByid(Long id, Throwable throwable) {
        logger.error("Circuit breaker activated for OrganisationService. Fallback method executed for id: {}. Exception: {}", id, throwable.getMessage());
        Organisation orga = new Organisation();
        orga.setId(id);
        orga.setName("Not Available");
        orga.setDescription("Not Available");
        orga.setAddress("Not Available");
        orga.setContactEmail("Not Available");
        orga.setPhoneNumber("Not Available");
        orga.setVerified(false);
        return orga;
    }
}
