package com.example.wordy.model;

import javax.persistence.*;
import java.util.Collection;


@Table(name = "Baskets")
@Entity
public class BasketModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @OneToOne(mappedBy = "basket", cascade = CascadeType.ALL)
    private CustomerModel customer;

    @ManyToMany
    @JoinTable(
                name = "product_in_basket",
                joinColumns = @JoinColumn(name = "basket_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id"))
    private Collection<ProductModel> products;


    public BasketModel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }




    public Collection<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(Collection<ProductModel> products) {
        this.products = products;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
}
