package com.example.CarDealershipAPI.Dealer;

import com.example.CarDealershipAPI.Car.Car;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank(message = "Name is mandatory")
    private String name;

    @OneToMany(
            mappedBy = "dealer"
    )
    @JsonManagedReference
    private List<Car> cars;

    public Dealer(String name) {
        this.name = name;
    }

    public Dealer() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
