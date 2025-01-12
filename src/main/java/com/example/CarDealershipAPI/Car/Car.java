package com.example.CarDealershipAPI.Car;

import com.example.CarDealershipAPI.Dealer.Dealer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Size(min = 1, max = 50, message = "Make must be between 1 and 50 characters")
    @NotBlank(message = "Make is mandatory")
    private String make;

    @Column
    @Size(min = 1, max = 50, message = "Model must be between 1 and 50 characters")
    @NotBlank(message = "Model is mandatory")
    private String model;

    @Column
    @NotNull(message = "Year is mandatory")
    @Min(value = 1886, message = "Year must be a valid year (>= 1886)")
    private Integer year;

    @Column
    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price must be a positive number")
    private Integer price;

    @ManyToOne
    @JoinColumn(
            name = "dealer_id"
    )
    @JsonBackReference
    private Dealer dealer;

    public Car(String make, String model, Integer year, Integer price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public Car() {

    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{id=" + id + ", make='" + make + "', model='" + model + "', year=" + year + ", price=" + price + "}";
    }
}
