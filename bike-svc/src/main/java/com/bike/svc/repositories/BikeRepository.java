package com.bike.svc.repositories;

import com.bike.svc.models.Bike;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long>{

    List<Bike> getByUserId(Long userId);

}
