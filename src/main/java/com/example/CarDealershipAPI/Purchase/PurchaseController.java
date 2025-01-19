package com.example.CarDealershipAPI.Purchase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    // GET
    @GetMapping("/api/purchases")
    public ResponseEntity<List<Purchase>> getAllPurchasedCarsByDate(
            @RequestParam(required = false) LocalDate date
    ) {
        List<Purchase> filteredPurchases = purchaseService.getAllPurchasedCarsByDate(date);
        return new ResponseEntity<>(filteredPurchases, HttpStatus.OK);
    }

    @GetMapping("/api/purchases/{purchase-id}")
    public ResponseEntity<Purchase> getPurchaseById(
            @PathVariable("purchase-id") Integer purchaseId
    ) {
        Optional<Purchase> requestedPurchase = purchaseService.getPurchaseById(purchaseId);

        return requestedPurchase
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST
    @PostMapping("/api/purchases")
    public ResponseEntity<Purchase> createPurchase(
            @RequestBody Purchase purchase
    ) {
        Purchase savedPurchase = purchaseService.createPurchase(purchase);
        return new ResponseEntity<>(savedPurchase, HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/api/purchases/{purchase-id}")
    public ResponseEntity<Purchase> updatePurchase(
            @PathVariable("purchase-id") Integer purchaseId,
            @RequestBody Purchase newPurchase
    ){
        Optional<Purchase> updatedPurchase = purchaseService.updatePurchase(newPurchase, purchaseId);

        return updatedPurchase
                .map(purchase -> new ResponseEntity<>(purchase, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE
    @DeleteMapping("/api/purchases/{purchase-id}")
    public ResponseEntity<String> deletePurchaseById(
            @PathVariable("purchase-id") Integer purchaseId
    ) {
        boolean isDeleted = purchaseService.deletePurchaseById(purchaseId);

        if(isDeleted) {
            return new ResponseEntity<>("Deleted Purchase ID: " + purchaseId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete Purchase ID: " + purchaseId, HttpStatus.NOT_FOUND);
        }
    }
}
