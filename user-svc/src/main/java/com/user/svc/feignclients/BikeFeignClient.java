package com.user.svc.feignclients;

import com.user.svc.models.Bike;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "bike-service",
        url = "http://localhost:8002",
        path = "/bike"
)
public interface BikeFeignClient {

    @PostMapping
    public Bike save(@RequestBody Bike bike);

    @GetMapping("/user/{userId}")
    public List<Bike> getBikes(
            @PathVariable("userId") Long userId
    );

}
