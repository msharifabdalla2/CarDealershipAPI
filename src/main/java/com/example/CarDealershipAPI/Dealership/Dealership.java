package com.example.CarDealershipAPI.Dealership;


import com.example.CarDealershipAPI.Car.Car;
import com.example.CarDealershipAPI.Dealer.Dealer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dealerships")
@JsonIgnoreProperties({"cars", "dealers"})
public class Dealership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @OneToMany(
            mappedBy = "dealership" // Establishing one-to-many relationship with Car
    )
    @JsonIgnoreProperties("dealership") // Avoid cyclic reference serialization on Car's dealership field
    private List<Car> cars;

    @OneToMany(
            mappedBy = "dealership" // Establishing one-to-many relationship with Dealer
    )
    @JsonIgnoreProperties("dealership") // Avoid cyclic reference serialization on Dealer's dealership field
    private List<Dealer> dealers;

    // Removed Cars and Dealers input in constructor
    public Dealership(String name, String location) {
        this.name = name;
        this.location = location;
        this.cars = new ArrayList<>();
        this.dealers = new ArrayList<>();
    }

    public Dealership() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Dealer> getDealers() {
        return dealers;
    }

    public void setDealers(List<Dealer> dealers) {
        this.dealers = dealers;
    }

    @Override
    public String toString() {
        return "Dealership{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
