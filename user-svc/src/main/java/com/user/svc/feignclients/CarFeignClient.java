package com.user.svc.feignclients;

import com.user.svc.models.Car;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "car-service",
        // Used only if the service has an unique port
        //url = "http://localhost:8003",
        path = "/car"
)
public interface CarFeignClient {

    @PostMapping
    public Car save(
            @RequestBody Car car
    );

    @GetMapping("/user/{userId}")
    public List<Car> getCars(
            @PathVariable("userId") Long userId
    );

}
