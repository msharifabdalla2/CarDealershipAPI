package com.example.CarDealershipAPI.Dealer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealerService {

    private final DealerRepository dealerRepository;

    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    // Create Dealer (POST)
    public Dealer createDealer(Dealer dealer){
        return dealerRepository.save(dealer);
    }

    // Read Dealers (GET)
    public List<Dealer> getAllDealers() {
        return dealerRepository.findAll()
                .stream()
                .toList();
    }

    // Update Dealers (PUT)
    public Dealer updateDealerById(Integer id, Dealer updatedDealer){
        return dealerRepository.findById(id)
                .map(existingDealer -> {
                    existingDealer.setName(updatedDealer.getName());
                    return dealerRepository.save(existingDealer);
                })
                .orElseThrow(() -> new RuntimeException("Dealer not found"));
    }

    // Delete Dealer (DELETE)
    public void deleteDealerById(Integer id){
        dealerRepository.deleteById(id);
    }
}
