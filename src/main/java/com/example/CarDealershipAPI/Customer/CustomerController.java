package com.example.CarDealershipAPI.Customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Create (POST)
    @PostMapping("/api/customers")
    public ResponseEntity<Customer> createCustomer(
            @RequestBody Customer customer
    ) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    // Read (GET)
    @GetMapping("/api/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(required = false) String name
    ) {
        List<Customer> filteredCustomers = customerService.getCustomerAndByName(name);
        return new ResponseEntity<>(filteredCustomers, HttpStatus.OK);
    }

    @GetMapping("/api/customers/{customer-id}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable("customer-id") Integer customerId
    ) {
        Optional<Customer> requestedCustomer = customerService.getCustomerById(customerId);

        return requestedCustomer
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update (PUT)

    // Delete
    @DeleteMapping("/api/customers/{customer-id}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable("customer-id") Integer customerId
    ) {
        boolean isDeleted = customerService.deleteCustomerById(customerId);

        if (isDeleted){
            return new ResponseEntity<>("Deleted Customer ID: " + customerId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete Customer ID: " + customerId, HttpStatus.NOT_FOUND);
        }
    }

}
