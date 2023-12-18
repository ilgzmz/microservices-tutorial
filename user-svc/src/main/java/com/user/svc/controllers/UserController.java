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
import java.util.Map;

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
    ){
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
    
    @PostMapping("/{userId}/car")
    public ResponseEntity<Car> saveCar(
            @PathVariable("userId") Long userId,
            @RequestBody Car car
    ){
        return ResponseEntity.ok(userSvc.saveCar(userId, car));
    }

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

    @PostMapping("/{userId}/bike")
    public ResponseEntity<Bike> saveBike(
            @PathVariable("userId") Long userId,
            @RequestBody Bike bike
    ){
        return ResponseEntity.ok(userSvc.saveBike(userId, bike));
    }

    @GetMapping("/{userId}/vehicles")
    public ResponseEntity<Map<String, Object>> getAllVehicles(
            @PathVariable("userId") Long userId
    ) {
        return ResponseEntity.ok(userSvc.getUserAndVehicles(userId));
    }
}
