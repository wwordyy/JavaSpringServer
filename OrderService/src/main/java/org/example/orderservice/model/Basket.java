package org.example.orderservice.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Baskets")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_basket")
    private int id;

    @Column(name = "user_ID")
    private int user;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<ProductInBasket> products;


    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProductInBasket> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInBasket> products) {
        this.products = products;
    }
}
