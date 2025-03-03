package com.example.CarDealershipAPI.Car;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Car> updateCar(Integer id, Car updatedCar) {

        Optional<Car> foundCar = carRepository.findById(id);

        if(foundCar.isPresent()) {
            Car carToUpdate = foundCar.get();

            carToUpdate.setMake(updatedCar.getMake());
            carToUpdate.setModel(updatedCar.getModel());
            carToUpdate.setYear(updatedCar.getYear());
            carToUpdate.setPrice(updatedCar.getPrice());
            carToUpdate.setDealership(updatedCar.getDealership());

            carRepository.save(carToUpdate);
            return Optional.of(carToUpdate);
        } else {
            return Optional.empty();
        }
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
