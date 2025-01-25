package com.example.CarDealershipAPI.Dealer;

import com.example.CarDealershipAPI.Dealership.Dealership;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DealerServiceTest {

    @InjectMocks
    private DealerService dealerService;

    @Mock
    private DealerRepository dealerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDealer() {
        // Given
        Dealer dealer = new Dealer(
                "John Doe",
                new Dealership("BMW Dealership", "London")
        );

        // Mocks
        when(dealerRepository.save(dealer))
                .thenReturn(dealer);

        // When
        Dealer createdDealer = dealerService.createDealer(dealer);

        // Then
        assertEquals(dealer, createdDealer);

        // Verify
    }

    @Test
    public void testGetDealersByNameOrDealership() {
        // Given
        String name = "John";

        Dealership dealership = new Dealership(
                "BMW Dealership",
                "London");

        List<Dealer> expectedDealers = List.of(
                new Dealer("John Doe", dealership),
                new Dealer("John Smith", dealership)
        );

        // Mocks
        when(dealerRepository.findByNameContaining(name))
                .thenReturn(expectedDealers);
        when(dealerRepository.findByNameContainingAndDealership(name, dealership))
                .thenReturn(expectedDealers);
        when(dealerRepository.findByDealership(dealership))
                .thenReturn(expectedDealers);
        when(dealerRepository.findAll())
                .thenReturn(expectedDealers);

        // When
        List<Dealer> filteredDealers = dealerService.getDealersByNameOrDealership(name, dealership);
        List<Dealer> nameOnlyDealers = dealerService.getDealersByNameOrDealership(name, null);
        List<Dealer> dealershipOnlyDealers = dealerService.getDealersByNameOrDealership(null, dealership);
        List<Dealer> allDealerships = dealerService.getDealersByNameOrDealership(null, null);

        // Then
        assertNotNull(filteredDealers);
        assertEquals(2, filteredDealers.size());
        assertEquals("John Doe", filteredDealers.get(0).getName());
        assertEquals("BMW Dealership", filteredDealers.get(0).getDealership().getName());

        assertNotNull(nameOnlyDealers);
        assertEquals(2, nameOnlyDealers.size());

        assertNotNull(dealershipOnlyDealers);
        assertEquals(2, dealershipOnlyDealers.size());

        assertNotNull(allDealerships);
        assertEquals(2, allDealerships.size());

        // Verify

    }

    @Test
    public void testGetDealerById() {
        // Given
        Integer id = 1;
        Dealer dealer = new Dealer(
                "John Doe",
                new Dealership("BMW Dealership", "London"));

        dealer.setId(id);

        // Mocks
        when(dealerRepository.findById(id))
                .thenReturn(Optional.of(dealer));

        // When
        Dealer dealerById = dealerService.getDealerById(id);

        // Then
        assertEquals(dealer, dealerById);

        // Verify
    }

    @Test
    public void testUpdateDealer() {
        // Given
        Integer id = 1;
        Dealer existingDealer = new Dealer(
                "John Doe",
                new Dealership("BMW Dealership", "London")
        );

        Dealer updatedDealer = new Dealer("Jane Doe",
                new Dealership("Audi Dealership", "Berlin")
        );

        existingDealer.setId(id);

        // Mocks
        when(dealerRepository.findById(id))
                .thenReturn(Optional.of(existingDealer));
        when(dealerRepository.save(existingDealer))
                .thenReturn(updatedDealer);

        // When
        Optional<Dealer> result = dealerService.updateDealer(id, updatedDealer);

        // Then
        assertTrue(result.isPresent());
        assertEquals(updatedDealer.getName(), result.get().getName());
        assertEquals(updatedDealer.getDealership(), result.get().getDealership());

        // Verify
    }

    @Test
}