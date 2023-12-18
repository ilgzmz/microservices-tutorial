package com.bike.svc.services;

import com.bike.svc.models.Bike;
import com.bike.svc.repositories.BikeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BikeService {

    @Autowired
    BikeRepository bikeRepo;

    public List<Bike> getAll() {
        return bikeRepo.findAll();
    }

    public Bike getById(Long id) {
        return bikeRepo.findById(id).orElse(null);
    }

    public Bike save(Bike bike) {
        return bikeRepo.save(bike);
    }

    public List<Bike> getByUserId(Long userId) {
        return bikeRepo.getByUserId(userId);
    }

}
