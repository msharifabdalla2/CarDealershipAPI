package com.example.CarDealershipAPI.Dealer;

import com.example.CarDealershipAPI.Dealership.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DealerRepository extends JpaRepository<Dealer, Integer> {

    List<Dealer> findByNameContainingAndDealership(String name, Dealership dealership);
    List<Dealer> findByNameContaining(String name);
    List<Dealer> findByDealership(Dealership dealership);
}
