package com.example.CarDealershipAPI.Dealership;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealershipService {

    private final DealershipRepository dealershipRepository;

    public DealershipService(DealershipRepository dealershipRepository) {
        this.dealershipRepository = dealershipRepository;
    }

    // Create (POST)
    public Dealership createDealership(Dealership dealership) {
        return dealershipRepository.save(dealership);
    }


    // Read (GET)
    public List<Dealership> getAllDealershipsByNameAndLocation(String name, String location) {
        if (name != null && location != null) {
            return dealershipRepository.findByNameContainingAndLocation(name , location);
        } else if (name != null) {
            return dealershipRepository.findByNameContaining(name);
        } else if (location != null) {
            return dealershipRepository.findByLocation(location);
        } else {
            return dealershipRepository.findAll();
        }
    }

    public Optional<Dealership> getDealershipById(Integer id) {
        return dealershipRepository.findById(id);
    }

    // Update (PUT)

    // Delete
    public boolean deleteDealershipById(Integer id) {
        Optional<Dealership> dealership = dealershipRepository.findById(id);
        if (dealership.isPresent()) {
            dealershipRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
