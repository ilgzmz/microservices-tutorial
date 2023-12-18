package com.car.svc.services;

import com.car.svc.models.Car;
import com.car.svc.repositories.CarRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepo;

    public List<Car> getAll() {
        return carRepo.findAll();
    }

    public Car getById(Long id) {
        return carRepo.findById(id).orElse(null);
    }

    public Car save(Car car) {
        return carRepo.save(car);
    }

    public List<Car> getByUserId(Long userId) {
        return carRepo.findByUserId(userId);
    }
}
