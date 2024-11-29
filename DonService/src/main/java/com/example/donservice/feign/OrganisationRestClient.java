package com.example.donservice.feign;

import com.example.donservice.models.Organisation;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author abdellah
 **/
@FeignClient(name = "OrganisationService" ,path = "/api/organisations")
public interface OrganisationRestClient {
    @GetMapping(path = "/{id}")
    @CircuitBreaker(name = "organisationService",fallbackMethod = ("getOrgaByid"))
    Organisation getOrganisationById(@PathVariable(name = "id") Long id);

    default Organisation getOrgaByid(Long id, Exception exception){
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
