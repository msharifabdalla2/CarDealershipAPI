package com.example.CarDealershipAPI.Car;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/api/cars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("/api/cars")
    public Car saveCar(
            @Valid @RequestBody Car car
    ) {
        return carService.saveCar(car);
    }
    
    @PutMapping("/api/cars/{car-id}")
    public ResponseEntity<Car> updateCar(
            @PathVariable("car-id") Integer carId,
            @Valid @RequestBody Car newCar
    ){
        Optional<Car> updatedCar = carService.updateCar(carId, newCar);

        return updatedCar
                .map(car -> new ResponseEntity<>(car, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/api/cars/{car-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCarById(
            @PathVariable("car-id") Integer carId
    ) {
        carService.deleteCarById(carId);
    }

    // Filtering below
    @GetMapping("/api/cars/filtered")
    public List<Car> getFilteredCars(
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer price
    ) {
        return carService.getFilteredCars(make, model, year, price);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}


