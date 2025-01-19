package com.example.CarDealershipAPI.Dealer;

import com.example.CarDealershipAPI.Dealership.Dealership;
import com.example.CarDealershipAPI.Dealership.DealershipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DealerService {

    private final DealerRepository dealerRepository;

    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    // Create
    public Dealer createDealer(Dealer dealer){
        return dealerRepository.save(dealer);
    }

    // Read
    public List<Dealer> getDealersByNameOrDealership(String name, Dealership dealership) {
        if (name != null && dealership != null) {
            return dealerRepository.findByNameContainingAndDealership(name, dealership);
        } else if (name != null) {
            return dealerRepository.findByNameContaining(name);
        } else if (dealership != null) {
            return dealerRepository.findByDealership(dealership);
        } else {
            return dealerRepository.findAll();
        }
    }

    public Dealer getDealerById(Integer id) {
        return dealerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Dealer not found with ID: " + id));
    }

    // Update
    public Optional<Dealer> updateDealer(Integer id, Dealer newDealer){

        var foundDealer = dealerRepository.findById(id);

        if(foundDealer.isPresent()) {
            Dealer foundDealerEntity = foundDealer.get();
            foundDealerEntity.setName(newDealer.getName());
            foundDealerEntity.setDealership(newDealer.getDealership());
            dealerRepository.save(foundDealerEntity);

            return Optional.of(foundDealerEntity);
        }
        return Optional.empty();
    }

    // Delete
    public void deleteDealerById(Integer id) {
        dealerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dealer with ID " + id + " not found."));
        dealerRepository.deleteById(id);
    }
}
