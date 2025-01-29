package com.example.CarDealershipAPI.Customer;

import com.example.CarDealershipAPI.Dealership.DealershipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final DealershipRepository dealershipRepository;

    public CustomerService(CustomerRepository customerRepository, DealershipRepository dealershipRepository) {
        this.customerRepository = customerRepository;
        this.dealershipRepository = dealershipRepository;
    }

    // Create (POST)
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Read (GET)
    public List<Customer> getCustomerAndByName(String name) {
        if (name != null) {
            return customerRepository.getCustomerByName(name);
        } else {
            return customerRepository.findAll();
        }
    }

    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    // Update (PUT)
    public Optional <Customer> updateCustomer(Integer id, Customer newCustomer) {
        return customerRepository.findById(id)
                .map(exc -> {
                    exc.setName(newCustomer.getName());
                    exc.setEmailAddress(newCustomer.getEmailAddress());
                    exc.setPurchases(newCustomer.getPurchases());
                    return customerRepository.save(exc);
                });
    }

    // Delete
    public boolean deleteCustomerById(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
