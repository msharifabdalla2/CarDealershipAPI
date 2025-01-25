package com.example.CarDealershipAPI.Dealership;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DealershipServiceTest {

    @InjectMocks
    private DealershipService dealershipService;

    @Mock
    private DealershipRepository dealershipRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDealership() {
        // Given
        Dealership dealership = new Dealership(
                "SuperCars Dealership",
                "123 Main Street, Springfield"
        );

        // Mocks
        when(dealershipRepository.save(dealership))
                .thenReturn(dealership);

        // When
        var createdDealership = dealershipService.createDealership(dealership);

        // Then
        assertEquals(dealership.getName(), createdDealership.getName());
        assertEquals(dealership.getLocation(), createdDealership.getLocation());

        // Verify
        verify(dealershipRepository, times(1))
                .save(dealership);
    }

    @Test
    public void testGetDealershipById() {
        // Given
        Integer id = 1;
        Dealership dealership = new Dealership(
                "Toyota Dealership",
                "123 Main St"
        );

        // Mocks
        when(dealershipRepository.findById(id))
                .thenReturn(Optional.of(dealership));

        // When
        Optional <Dealership> foundDealership = dealershipService.getDealershipById(id);

        // Then
        assertTrue(foundDealership.isPresent());
        assertEquals(dealership.getName(), foundDealership.get().getName());
        assertEquals(dealership.getLocation(), foundDealership.get().getLocation());

        // Verify
        verify(dealershipRepository, times(1))
                .findById(id);
    }

    @Test
    public void testGetAllDealershipsByNameAndLocation() {

        // Given
        String name = "Toyota";
        String location = "123 Main St";

        List<Dealership> dealerships = List.of(
                new Dealership("Toyota Dealership", "123 Main St"),
                new Dealership("Toyota Service Center", "456 Elm St")
        );

        // Mocks
        when(dealershipRepository.findByNameContainingAndLocation(name, location))
                .thenReturn(dealerships);
        when(dealershipRepository.findByNameContaining(name))
                .thenReturn(dealerships);
        when(dealershipRepository.findByLocation(location))
                .thenReturn(List.of(dealerships.get(0)));
        when(dealershipRepository.findAll())
                .thenReturn(dealerships);

        // When
        List<Dealership> filteredDealerships = dealershipService.getAllDealershipsByNameAndLocation(name, location);
        List<Dealership> nameOnlyDealerships = dealershipService.getAllDealershipsByNameAndLocation(name, null);
        List<Dealership> locationOnlyDealerships = dealershipService.getAllDealershipsByNameAndLocation(null, location);
        List<Dealership> allDealerships = dealershipService.getAllDealershipsByNameAndLocation(null, null);

        // Then
        assertNotNull(filteredDealerships);
        assertEquals(2, filteredDealerships.size());
        assertEquals("Toyota Dealership", filteredDealerships.get(0).getName());
        assertEquals("123 Main St", filteredDealerships.get(0).getLocation());

        assertNotNull(nameOnlyDealerships);
        assertEquals(2, nameOnlyDealerships.size());

        assertNotNull(locationOnlyDealerships);
        assertEquals(1, locationOnlyDealerships.size());
        assertEquals("Toyota Dealership", locationOnlyDealerships.get(0).getName());

        assertNotNull(allDealerships);
        assertEquals(2, allDealerships.size());

        // Verify
        verify(dealershipRepository, times(1))
                .findByNameContainingAndLocation(name, location);
        verify(dealershipRepository, times(1))
                .findByNameContaining(name);
        verify(dealershipRepository, times(1))
                .findByLocation(location);
        verify(dealershipRepository, times(1))
                .findAll();
    }

    @Test
    public void testUpdateDealership() {
        // Given
        Integer id = 1;

        Dealership initialDealership = new Dealership(
                "Toyota Dealership",
                "123 Main St"
        );
        initialDealership.setId(id);

        Dealership updatedDealership = new Dealership(
                "BMW Dealership",
                "456 Oak Rd"
        );

        // Mocks
        when(dealershipRepository.findById(id))
                .thenReturn(Optional.of(initialDealership));
        when(dealershipRepository.save(initialDealership))
                .thenReturn(initialDealership); // Check this before moving on

        // When
        var newDealership = dealershipService.updateDealership(id, updatedDealership);

        // Then
        assertTrue(newDealership.isPresent());
        assertEquals(updatedDealership.getName(), newDealership.get().getName());
        assertEquals(updatedDealership.getLocation(), newDealership.get().getLocation());
        assertEquals(updatedDealership.getCars(), newDealership.get().getCars());

        //  Verify
        verify(dealershipRepository, times(1))
                .findById(id);
        verify(dealershipRepository, times(1))
                .save(initialDealership);
    }

    @Test
    public void testDeleteDealership() {
        // Given
        Integer id = 1;

        Dealership initialDealership = new Dealership(
                "BMW Dealership",
                "456 Oak Rd"
        );

        initialDealership.setId(id);

        // Mocks
        when(dealershipRepository.findById(id))
                .thenReturn(Optional.of(initialDealership));
        doNothing().when(dealershipRepository)
                .deleteById(id);

        // When
        boolean result = dealershipService.deleteDealershipById(id);

        // Then
        assertTrue(result, "The dealership should be successfully deleted");

        // Verify
        verify(dealershipRepository, times(1))
                .findById(id);

        verify(dealershipRepository, times(1))
                .deleteById(id);
    }
}