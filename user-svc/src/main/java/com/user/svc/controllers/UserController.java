package com.user.svc.controllers;

import com.user.svc.models.Bike;
import com.user.svc.models.Car;
import com.user.svc.models.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.user.svc.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Map;
import javax.management.RuntimeErrorException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userSvc;

    @GetMapping
    public ResponseEntity<List<User>> list() {
        List<User> users = userSvc.getAll();

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(
            @PathVariable("id") Long id
    ) {
        User user = userSvc.getById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> save(
            @RequestBody User user
    ) {
        return ResponseEntity.ok(userSvc.save(user));
    }

    // *** *** *** Cars *** *** ***
    @CircuitBreaker(
            name = "carsCB",
            fallbackMethod = "fallbackGetCarsByUserId"
    )
    @GetMapping("/{userId}/cars/")
    public ResponseEntity<List<Car>> getCarsByUserId(
            @PathVariable("userId") Long userId
    ) {
        User user = userSvc.getById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Car> cars = userSvc.getCars(userId);
        return ResponseEntity.ok(cars);
    }

    private ResponseEntity<List<Car>> fallbackGetCarsByUserId(
            @PathVariable("userId") Long userId,
            RuntimeException exc
    ) {
        return new ResponseEntity("Failing to get cars from User " + userId, HttpStatus.OK);
    }

    @CircuitBreaker(
            name = "carsCB",
            fallbackMethod = "fallbackSaveCar"
    )
    @PostMapping("/{userId}/car")
    public ResponseEntity<Car> saveCar(
            @PathVariable("userId") Long userId,
            @RequestBody Car car
    ) {
        return ResponseEntity.ok(userSvc.saveCar(userId, car));
    }

    private ResponseEntity<Car> fallbackSaveCar(
            @PathVariable("userId") Long userId,
            @RequestBody Car car,
            RuntimeException exp
    ) {
        return new ResponseEntity("Failing to save car to User " + userId, HttpStatus.OK);
    }

    // *** *** *** Bikes **** ** ***
    @CircuitBreaker(
            name = "bikesCB",
            fallbackMethod = "fallbackGetBikesByUserId"
    )
    @GetMapping("/{userId}/bikes/")
    public ResponseEntity<List<Bike>> getBikesByUserId(
            @PathVariable("userId") Long userId
    ) {
        User user = userSvc.getById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Bike> bikes = userSvc.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }

    private ResponseEntity<List<Bike>> fallbackGetBikesByUserId(
            @PathVariable("userId") Long userId,
            RuntimeException exc
    ) {
        return new ResponseEntity("Failing to get bikes from User " + userId, HttpStatus.OK);
    }

    @CircuitBreaker(
            name = "bikesCB",
            fallbackMethod = "fallbackSaveBike"
    )
    @PostMapping("/{userId}/bike")
    public ResponseEntity<Bike> saveBike(
            @PathVariable("userId") Long userId,
            @RequestBody Bike bike
    ) {
        return ResponseEntity.ok(userSvc.saveBike(userId, bike));
    }

    private ResponseEntity<Bike> fallbackSaveBike(
            @PathVariable("userId") Long userId,
            @RequestBody Bike bike,
            RuntimeException exp
    ) {
        return new ResponseEntity("Failing to save bike to User " + userId, HttpStatus.OK);
    }

    // *** *** *** All Vehicles *** *** ***
    @CircuitBreaker(
            name = "vehiclesCB",
            fallbackMethod = "fallbackGetAllVehicles"
    )
    @GetMapping("/{userId}/vehicles")
    public ResponseEntity<Map<String, Object>> getAllVehicles(
            @PathVariable("userId") Long userId
    ) {
        return ResponseEntity.ok(userSvc.getUserAndVehicles(userId));
    }

    private ResponseEntity<Map<String, Object>> fallbackGetAllVehicles(
            @PathVariable("userId") Long userId,
            RuntimeException exp
    ) {
        return new ResponseEntity("Failing to get vehicles from User " + userId, HttpStatus.OK);
    }

}
