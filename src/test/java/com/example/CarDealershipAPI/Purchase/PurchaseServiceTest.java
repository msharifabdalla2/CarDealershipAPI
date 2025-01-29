package com.example.CarDealershipAPI.Purchase;

import com.example.CarDealershipAPI.Car.Car;
import com.example.CarDealershipAPI.Customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PurchaseServiceTest {

    @InjectMocks
    private PurchaseService purchaseService;

    @Mock
    private PurchaseRepository purchaseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createPurchase() {
        // Given
        Customer customer = new Customer(
                "John Doe",
                "john@example.com"
        );

        Car car = new Car(
                "Toyota", "Camry", 2021, 25000, null
        );

        Purchase purchase = new Purchase(
                LocalDate.now(),
                customer,
                car
        );

        // Mocks
        when(purchaseRepository.save(purchase))
                .thenReturn(purchase);

        // When
        Purchase createdPurchase = purchaseService.createPurchase(purchase);

        // Then
        assertNotNull(createdPurchase);
        assertEquals(customer, createdPurchase.getCustomer());
        assertEquals(car, createdPurchase.getCarPurchased());
        assertEquals(LocalDate.now(), createdPurchase.getDate());

        // Verify
        verify(purchaseRepository, times(1))
                .save(purchase);
    }

    // Given

    // Mocks

    // When

    // Then

    // Verify
}