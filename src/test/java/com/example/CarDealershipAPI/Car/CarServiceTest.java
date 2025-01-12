package com.example.CarDealershipAPI.Car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {

    // Which service we want to test
    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCar() {
        // Given
        Car car = new Car(
                "Toyota",
                "S Series",
                2019,
                20000
        );

        // Mocks
        when(carRepository.save(car))
                .thenReturn(car);

        // When
        var savedCar = carService.saveCar(car);

        // Then
        assertEquals(car, savedCar);

        // Verify
        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void testGetAllCarsWhichShouldReturnListOfCars() {
        // Given
        List<Car> cars = List.of(
                new Car("Toyota", "S Series", 2019, 20000),
                new Car("Toyota", "Corolla", 2020, 18000),
                new Car("Honda", "Civic", 2019, 20000),
                new Car("Ford", "Focus", 2021, 22000),
                new Car("Chevrolet", "Malibu", 2018, 17000),
                new Car("Nissan", "Altima", 2022, 25000),
                new Car("Hyundai", "Elantra", 2020, 19000),
                new Car("Kia", "Optima", 2019, 18500),
                new Car("Volkswagen", "Jetta", 2021, 21000),
                new Car("Subaru", "Impreza", 2018, 16000),
                new Car("Mazda", "Mazda3", 2020, 20000),
                new Car("BMW", "3 Series", 2021, 35000),
                new Car("Audi", "A4", 2022, 37000),
                new Car("Mercedes-Benz", "C-Class", 2020, 40000),
                new Car("Lexus", "IS", 2021, 42000),
                new Car("Acura", "TLX", 2019, 32000),
                new Car("Tesla", "Model 3", 2022, 45000)
        );

        // Mocks
        when(carRepository.findAll())
                .thenReturn(cars);

        // When
        var savedCars = carService.getAllCars();

        // Then
        assertEquals(cars, savedCars);

        // Verify
        verify(carRepository, times(1)).findAll();
    }

    @Test
    public void testShouldUpdateCar() {
        // Given
        Integer id = 1;

        Car existingCar = new Car("Toyota", "S Series", 2019, 20000);

        existingCar.setId(id);

        Car updatedCar = new Car("BMW", "3 Series", 2021, 35000);

        // Mocks
        when(carRepository.findById(id))
                .thenReturn(Optional.of(existingCar));
        when(carRepository.save(existingCar))
                .thenReturn(existingCar);

        // When
        var newCar = carService.updateCar(id, updatedCar);

        // Then
        assertNotNull(newCar);
        assertEquals("BMW", newCar.getMake());
        assertEquals("3 Series", newCar.getModel());
        assertEquals(2021, newCar.getYear());
        assertEquals(35000, newCar.getPrice());

        // Verify
        verify(carRepository, times(1)).findById(id);
        verify(carRepository, times(1)).save(existingCar);
    }

    // Successful deletion test
    @Test
    public void testDeleteCarById() {
        // Given
        Integer id = 1;

        Car existingCar = new Car("Toyota", "S Series", 2019, 20000);

        // Mocks
        doNothing().when(carRepository).deleteById(id);

        // When
        carService.deleteCarById(id);

        // Then
        // No assertions needed for void method, but we can rely on verification

        // Verify
        verify(carRepository, times(1)).deleteById(id);
    }

    // Deletion throws an exception
    @Test
    void testDeleteCarByIdThrowsException() {
        // Arrange
        Integer carId = 999; // Non-existent car ID

        // Mocking: Simulate an exception when deleteById is called
        doThrow(new RuntimeException("Car not found")).when(carRepository).deleteById(carId);

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> carService.deleteCarById(carId));

        assertEquals("Car not found", thrown.getMessage());

        // Verify that deleteById was called exactly once with the given carId
        verify(carRepository, times(1)).deleteById(carId);
    }

    @Test
    public void testGetFilteredCars(){
        // Given
        String make = "Toyota";
        String model = "Camry";
        Integer year = 2020;
        Integer price = 25000;

        List<Car> cars = List.of(
                new Car("Toyota", "S Series", 2019, 20000),
                new Car("Toyota", "Corolla", 2020, 18000),
                new Car("Honda", "Civic", 2019, 20000),
                new Car("Ford", "Focus", 2021, 22000),
                new Car("Chevrolet", "Malibu", 2018, 17000),
                new Car("Nissan", "Altima", 2022, 25000),
                new Car("Hyundai", "Elantra", 2020, 19000),
                new Car("Kia", "Optima", 2019, 18500),
                new Car("Volkswagen", "Jetta", 2021, 21000),
                new Car("Subaru", "Impreza", 2018, 16000),
                new Car("Mazda", "Mazda3", 2020, 20000),
                new Car("BMW", "3 Series", 2021, 35000),
                new Car("Audi", "A4", 2022, 37000),
                new Car("Mercedes-Benz", "C-Class", 2020, 40000),
                new Car("Lexus", "IS", 2021, 42000),
                new Car("Acura", "TLX", 2019, 32000),
                new Car("Tesla", "Model 3", 2022, 45000),
                new Car("Toyota", "Camry", 2020, 25000),
                new Car("Toyota", "Corolla", 2021, 22000)
        );

        // Mocks
        when(carRepository.findByFilters(make, model, year, price))
                .thenReturn(cars);

        // When
        var filteredCars = carService.getFilteredCars(make, model, year, price);

        // Then
        assertNotNull(filteredCars);
        assertEquals(19, filteredCars.size());
        assertEquals("Toyota", filteredCars.get(17).getMake());
        assertEquals("Camry", filteredCars.get(17).getModel());

        // Verify
        verify(carRepository, times(1))
                .findByFilters(make, model, year, price);
    }

}