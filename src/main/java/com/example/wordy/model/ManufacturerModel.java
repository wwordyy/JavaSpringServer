package com.example.wordy.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Collection;

@Table(name = "Manufacturers")
@Entity
public class ManufacturerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 5, max = 50, message = "Название должно быть от 5 до 50 символов!!!")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private Collection<ProductModel> products;


    public ManufacturerModel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(Collection<ProductModel> products) {
        this.products = products;
    }
}
