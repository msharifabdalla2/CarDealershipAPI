package com.example.CarDealershipAPI.Dealer;

import com.example.CarDealershipAPI.Dealership.Dealership;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DealerController {

    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    // Create
    @PostMapping("/api/dealers")
    public ResponseEntity<Dealer> createDealer(
            @RequestBody Dealer dealer
    ) {
        Dealer savedDealer = dealerService.createDealer(dealer);
        return new ResponseEntity<>(savedDealer, HttpStatus.CREATED);
    }


    // Read
    @GetMapping("/api/dealers")
    public ResponseEntity<List<Dealer>> getAllDealersByNameOrDealership(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Dealership dealership
    ) {
        List<Dealer> dealers = dealerService.getDealersByNameOrDealership(name, dealership);
        return new ResponseEntity<>(dealers, HttpStatus.OK);
    }

    @GetMapping("/api/dealers/{dealer-id}")
    public ResponseEntity<Dealer> getDealerById(
            @PathVariable("dealer-id") Integer dealerId
    ) {
        Dealer dealer = dealerService.getDealerById(dealerId);
        return new ResponseEntity<>(dealer, HttpStatus.OK);
    }

    // Update


    // Delete
    @DeleteMapping("/api/dealers/{dealer-id}")
    public ResponseEntity<String> deleteDealerById(
            @PathVariable("dealer-id") Integer dealerId
    ) {
        dealerService.deleteDealerById(dealerId);
        return new ResponseEntity<>("Deleted Dealer ID: " + dealerId, HttpStatus.NOT_FOUND);
    }

}
