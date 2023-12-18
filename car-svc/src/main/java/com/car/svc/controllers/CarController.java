package com.car.svc.controllers;

import com.car.svc.models.Car;
import com.car.svc.services.CarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carSvc;

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        List<Car> cars = carSvc.getAll();
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }
   
    @GetMapping("/{id}")
    public ResponseEntity<Car> get(
            @PathVariable("id") Long id
    ){
        Car car = carSvc.getById(id);

        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> save(
            @RequestBody Car user
    ) {
        return ResponseEntity.ok(carSvc.save(user));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Car>> listByUserId(
            @PathVariable("userId") Long userId
    ) {
        List<Car> cars = carSvc.getByUserId(userId);
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

}