package com.example.wordy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;


@Table(name = "Countries")
@Entity
public class CountryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 5, max = 50, message = "Название должно быть от 5 до 50 символов!!!")
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Collection<ProductModel> products;

    public CountryModel() {}

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

    public Collection<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(Collection<ProductModel> products) {
        this.products = products;
    }
}
