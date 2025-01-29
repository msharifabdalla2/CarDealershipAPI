package com.example.CarDealershipAPI.Purchase;

import com.example.CarDealershipAPI.Car.Car;
import com.example.CarDealershipAPI.Customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void testGetPurchaseById() {
        // Given
        Integer id = 1;

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

        purchase.setId(id);

        // Mocks
        when(purchaseRepository.findById(id))
                .thenReturn(Optional.of(purchase));

        // When
        Optional<Purchase> foundPurchase = purchaseService.getPurchaseById(id);

        // Then
        assertTrue(foundPurchase.isPresent());
        assertEquals(customer, foundPurchase.get().getCustomer());
        assertEquals(car, foundPurchase.get().getCarPurchased());

        // Verify
        verify(purchaseRepository, times(1))
                .findById(id);
    }

    @Test
    public void getAllPurchasedCarsByDate() {
        // Given
        LocalDate date = LocalDate.of(2024, 1, 29);
        Customer customer1 = new Customer("Alice", "alice@example.com");
        Customer customer2 = new Customer("Bob", "bob@example.com");
        Car car1 = new Car("Tesla", "Model 3", 2023, 45000, null);
        Car car2 = new Car("BMW", "X5", 2022, 55000, null);
        Purchase purchase1 = new Purchase(date, customer1, car1);
        Purchase purchase2 = new Purchase(date, customer2, car2);

        List<Purchase> purchases = Arrays.asList(
                purchase1,
                purchase2);

        // Mocks
        when(purchaseRepository.findByDate(date))
                .thenReturn(purchases);

        // When
        List<Purchase> actualPurchases = purchaseService.getAllPurchasedCarsByDate(date);

        // Then
        assertEquals(2, actualPurchases.size());
        assertEquals(purchases, actualPurchases);

        // Verify
        verify(purchaseRepository, times(1))
                .findByDate(date);
        verify(purchaseRepository, never())
                .findAll();
    }

    @Test
    public void getPurchaseById(){
        // Given
        Integer id = 1;

        LocalDate date = LocalDate.of(2024, 1, 29);

        Customer customer = new Customer(
                "Alice",
                "alice@example.com");

        Car car = new Car(
                "Tesla",
                "Model 3",
                2023,
                45000,
                null);

        Purchase purchase = new Purchase(
                date,
                customer,
                car
        );

        purchase.setId(id);

        // Mocks
        when(purchaseRepository.findById(id))
                .thenReturn(Optional.of(purchase));

        // When
        Optional<Purchase> actualPurchase = purchaseService.getPurchaseById(id);

        // Then
        assertTrue(actualPurchase.isPresent());
        assertEquals(purchase, actualPurchase.get());

        // Verify
        verify(purchaseRepository, times(1))
                .findById(id);
    }

    // Given

    // Mocks

    // When

    // Then

    // Verify
}