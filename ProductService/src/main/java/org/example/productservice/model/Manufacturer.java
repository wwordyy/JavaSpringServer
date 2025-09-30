package org.example.productservice.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Collection;
import java.util.List;

@Table(name = "Manufacturers")
@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 5, max = 50, message = "Название должно быть от 5 до 50 символов!!!")
    private String name;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List<Product> products;


    public Manufacturer() {}

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


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
