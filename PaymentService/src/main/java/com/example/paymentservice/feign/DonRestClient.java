package com.example.paymentservice.feign;

import com.example.paymentservice.models.Don;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author abdellah
 **/
@FeignClient("DonService")
public interface DonRestClient {
    @GetMapping(path = "/dons/{id}")
    @CircuitBreaker(name="donService", fallbackMethod = "getDonByid")
    Don getDonById(@PathVariable(name = "id") Long id);

    default Don getDonByid(Long id, Exception exception){
        Don don = new Don();
        don.setId(id);
        don.setUserId(null);
        don.setOrganisationId(null);
        don.setMontant(null);
        don.setDate(null);
        return don;
    }
}
