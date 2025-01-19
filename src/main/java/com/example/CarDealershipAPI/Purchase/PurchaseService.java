package com.example.CarDealershipAPI.Purchase;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    // Create
    public Purchase createPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    // Read
    public List<Purchase> getAllPurchasedCarsByDate(LocalDate date) {
        if(date !=null) {
            return purchaseRepository.findByDate(date);
        } else {
            return purchaseRepository.findAll();
        }
    }

    public Optional<Purchase> getPurchaseById(Integer id) {
        return purchaseRepository.findById(id);
    }

    // Update
    public Optional<Purchase> updatePurchase(Purchase newPurchase, Integer id) {
        return purchaseRepository.findById(id)
                .map(existingPurchase -> {
                    existingPurchase.setCarPurchased((newPurchase.getCarPurchased()));
                    existingPurchase.setCustomer((newPurchase.getCustomer()));
                    existingPurchase.setDate(newPurchase.getDate());
                    return purchaseRepository.save(existingPurchase);
                });
    }

    // Delete
    public boolean deletePurchaseById(Integer id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);

        if(purchase.isPresent()) {
            purchaseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
