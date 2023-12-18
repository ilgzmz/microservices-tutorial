package com.bike.svc.controllers;

import com.bike.svc.models.Bike;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bike.svc.services.BikeService;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    private BikeService bikeSvc;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> cars = bikeSvc.getAll();
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }
   
    @GetMapping("/{id}")
    public ResponseEntity<Bike> get(
            @PathVariable("id") Long id
    ){
        Bike bike = bikeSvc.getById(id);

        if (bike == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    @PostMapping
    public ResponseEntity<Bike> save(
            @RequestBody Bike bike
    ) {
        return ResponseEntity.ok(bikeSvc.save(bike));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bike>> listByUserId(
            @PathVariable("userId") Long userId
    ) {
        List<Bike> bikes = bikeSvc.getByUserId(userId);
        if (bikes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

}