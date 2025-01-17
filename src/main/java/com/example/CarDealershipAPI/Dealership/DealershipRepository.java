package com.example.CarDealershipAPI.Dealership;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealershipRepository extends JpaRepository<Dealership, Integer> {

    List<Dealership> findByNameContainingAndLocation(String name, String location);

    List<Dealership> findByNameContaining(String name);

    List<Dealership> findByLocation(String location);
}
