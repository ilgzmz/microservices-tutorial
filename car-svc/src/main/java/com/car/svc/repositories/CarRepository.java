package com.car.svc.repositories;

import com.car.svc.models.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

    List<Car> findByUserId(Long userId);

}
