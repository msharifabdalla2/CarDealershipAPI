package com.example.CarDealershipAPI.Dealership;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DealershipController {

    private final DealershipService dealershipService;

    public DealershipController(DealershipService dealershipService) {
        this.dealershipService = dealershipService;
    }

    // POST
    @PostMapping("/api/dealerships")
    public ResponseEntity<Dealership> createDealership(
            @RequestBody Dealership dealership
    ) {
        Dealership savedDealership = dealershipService.createDealership(dealership);
        return new ResponseEntity<>(savedDealership, HttpStatus.CREATED);
    }

    // GET
    @GetMapping("/api/dealerships")
    public ResponseEntity<List<Dealership>> getAllDealerships(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location
    ) {
        List<Dealership> filteredDealerships = dealershipService.getAllDealershipsByNameAndLocation(name, location);
        return new ResponseEntity<>(filteredDealerships, HttpStatus.OK);
    }

    @GetMapping("api/dealerships/{dealership-id}")
    public ResponseEntity<Dealership> getFilteredDealership(
            @PathVariable("dealership-id") Integer dealershipId
    ) {
        Optional<Dealership> requestedDealership = dealershipService.getDealershipById(dealershipId);

        return requestedDealership
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // PUT

    // Delete
    @DeleteMapping("/api/dealerships/{dealership-id}")
    public ResponseEntity<String> deleteDealership(
            @PathVariable("dealership-id") Integer dealershipId
    ) {
        boolean isDeleted = dealershipService.deleteDealershipById(dealershipId);

        if (isDeleted) {
            return new ResponseEntity<>("Deleted Dealership ID: " + dealershipId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete Dealership ID: " + dealershipId, HttpStatus.NOT_FOUND);
        }
    }



}
