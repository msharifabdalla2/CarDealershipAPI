package com.example.CarDealershipAPI.Purchase;

import com.example.CarDealershipAPI.Customer.Customer;
import com.example.CarDealershipAPI.Car.Car;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(
            name = "customers_id"
    )
    @JsonIgnoreProperties({"purchases"}) // Ignore the purchases list in Customer class to avoid circular reference
    private Customer customer;

    @ManyToOne
    @JoinColumn(
            name = "cars_id"
    )
    @JsonIgnoreProperties({"dealership"}) // Ignore the dealership in Car class to avoid circular reference
    private Car carPurchased;

    public Purchase(LocalDate date, Customer customer, Car carPurchased) {
        this.date = date;
        this.customer = customer;
        this.carPurchased = carPurchased;
    }

    public Purchase() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCarPurchased() {
        return carPurchased;
    }

    public void setCarPurchased(Car carPurchased) {
        this.carPurchased = carPurchased;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", date=" + date +
                ", customer=" + customer +
                ", carPurchased=" + carPurchased +
                '}';
    }
}
