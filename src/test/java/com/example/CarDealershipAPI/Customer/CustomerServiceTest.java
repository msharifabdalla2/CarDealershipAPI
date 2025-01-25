package com.example.CarDealershipAPI.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCustomer() {
        // Given
        Customer customer = new Customer(
                "John Doe",
                "johndoe@example.com"
        );

        // Mocks
        when(customerRepository.save(customer))
                .thenReturn(customer);

        // When
        Customer savedCustomer = customerService.saveCustomer(customer);

        // Then
        assertNotNull(savedCustomer);
        assertEquals(customer, savedCustomer);

        // Verify
    }

    @Test
    public void testGetCustomerAndByName() {
        // Given
        String name = "John Doe";
        List<Customer> expectedCustomers = List.of(
                new Customer("John Doe",
                        "johndoe@example.com")
        );

        // Mocks
        when(customerRepository.getCustomerByName(name))
                .thenReturn(expectedCustomers);
        when(customerRepository.findAll())
                .thenReturn(expectedCustomers);

        // When
        List<Customer> customers = customerService.getCustomerAndByName(name);
        List<Customer> allCustomers = customerService.getCustomerAndByName(null);

        // Then
        assertNotNull(customers);
        assertEquals(1, customers.size());
        assertEquals("John Doe", customers.get(0).getName());

        assertNotNull(allCustomers);
        assertEquals(1, allCustomers.size());

        // Verify
    }

    @Test
    public void testGetCustomerById() {
        // Given
        Integer id = 1;
        Customer customer = new Customer("John Doe",
                "johndoe@example.com"
        );

        // Mocks
        when(customerRepository.findById(id))
                .thenReturn(Optional.of(customer));

        // When
        Optional<Customer> foundCustomer = customerService.getCustomerById(id);

        // Then
        assertTrue(foundCustomer.isPresent());
        assertEquals("John Doe", foundCustomer.get().getName());

        // Verify
//      verify(customerRepository, times(1)).findById(id);
    }


}