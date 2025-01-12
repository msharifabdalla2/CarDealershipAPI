package com.example.CarDealershipAPI.Car;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Create Car (POST)
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    // Read Cars (GET)
    public List<Car> getAllCars() {
        return carRepository.findAll()
            .stream()
            .toList();
    }

    // Update Car (PUT)
    public Car updateCar(Integer id, Car updatedCar) {
        return carRepository.findById(id)
                .map(existingCar -> {
                    existingCar.setMake(updatedCar.getMake());
                    existingCar.setModel(updatedCar.getModel());
                    existingCar.setYear(updatedCar.getYear());
                    existingCar.setPrice(updatedCar.getPrice());
                    return carRepository.save(existingCar);
                })
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    // Delete Car (DELETE)
    public void deleteCarById(Integer id) {
        carRepository.deleteById(id);
    }

    // Filter car by make, model, year, price
    public List<Car> getFilteredCars(String make, String model, Integer year, Integer price) {
        return carRepository.findByFilters(make, model, year, price);
    }

}
