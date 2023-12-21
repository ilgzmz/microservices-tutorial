package com.user.svc.services;

import com.user.svc.feignclients.BikeFeignClient;
import com.user.svc.feignclients.CarFeignClient;
import com.user.svc.models.Bike;
import com.user.svc.models.Car;
import com.user.svc.models.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.svc.repositories.UserRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    // *** *** *** REST TEMPLATE *** *** ***
    @Autowired
    private RestTemplate restTemplate;

    // Bikes
    //private String baseUriBike = "http://localhost:8002/bike/";
    private String baseUriBike = "http://bike-service/bike/";

    public List<Bike> getBikes(Long userId) {
        List<Bike> bikes = restTemplate.getForObject(
                baseUriBike + "user/" + userId,
                List.class
        );
        return bikes;
    }

    // Cars
    // Used for services with a fixed port.
    //private String baseUriCar = "http://localhost:8003/car/";
    private String baseUriCar = "http://car-service/car/";

    public List<Car> getCars(Long userId) {
        List<Car> cars = restTemplate.getForObject(
                baseUriCar + "user/" + userId,
                List.class
        );
        return cars;
    }
    // *** *** ***

    // *** *** *** Feign Client *** *** ***
    @Autowired
    private BikeFeignClient bikeFeignClient;

    @Autowired
    private CarFeignClient carFeignClient;

    public Bike saveBike(Long usedId, Bike bike) {
        bike.setUserId(usedId);
        return bikeFeignClient.save(bike);
    }

    public Car saveCar(Long userId, Car car) {
        car.setUserId(userId);
        return carFeignClient.save(car);
    }

    public Map<String, Object> getUserAndVehicles(Long userId) {
        Map<String, Object> res = new HashMap<>();

        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            res.put("Message", "User not found");
            return res;
        }
        res.put("User", user);

        List<Car> cars = carFeignClient.getCars(userId);
        if (cars.isEmpty()) {
            res.put("Cars", "User has not cars.");
        } else {
            res.put("Cars", cars);
        }

        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if (bikes.isEmpty()) {
            res.put("Bikes", "User has not bikes.");
        } else {
            res.put("Bikes", bikes);
        }

        return res;
    }
    // *** *** ***
    
    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User getById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepo.save(user);
    }
    
}
