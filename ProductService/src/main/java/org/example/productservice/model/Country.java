package org.example.productservice.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Table(name = "Countries")
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 5, max = 50, message = "Название должно быть от 5 до 50 символов!!!")
    private String title;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Product> products;

    public Country() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
