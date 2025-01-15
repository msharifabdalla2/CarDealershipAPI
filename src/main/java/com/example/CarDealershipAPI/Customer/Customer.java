package com.example.CarDealershipAPI.Customer;

import com.example.CarDealershipAPI.Purchase.Purchase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String emailAddress;

    @OneToMany(
            mappedBy = "customer" // Establishing one-to-many relationship with Purchase
    )
    @JsonIgnoreProperties({"customer","dealership"})
    private List<Purchase> purchases;

    public Customer(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.purchases = new ArrayList<>();
    }

    public Customer() {

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", purchases=" + purchases +
                '}';
    }
}
