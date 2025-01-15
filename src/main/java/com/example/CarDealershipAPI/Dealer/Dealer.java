package com.example.CarDealershipAPI.Dealer;

import com.example.CarDealershipAPI.Dealership.Dealership;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "dealers")
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "dealerships_id" // Foreign key for the dealership
    )
    @JsonIgnoreProperties({"cars","dealers"}) // Ignore the dealers and cars list in Dealership class to avoid circular reference
    private Dealership dealership;

    public Dealer(String name, Dealership dealership) {
        this.name = name;
        this.dealership = dealership;
    }

    public Dealer() {

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

    public Dealership getDealership() {
        return dealership;
    }

    public void setDealership(Dealership dealership) {
        this.dealership = dealership;
    }

    @Override
    public String toString() {
        return "Dealer{id=" + id + ", name='" + name + "', dealership=" + dealership.getName() + "}";
    }
}
