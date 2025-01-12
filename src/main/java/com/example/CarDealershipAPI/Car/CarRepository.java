package com.example.CarDealershipAPI.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query("SELECT c FROM Car c WHERE (:make IS NULL OR c.make = :make) " +
            "AND (:model IS NULL OR c.model = :model) " +
            "AND (:year IS NULL OR c.year = :year) " +
            "AND (:price IS NULL OR c.price = :price)")
    List<Car> findByFilters(String make, String model, Integer year, Integer price);
}

