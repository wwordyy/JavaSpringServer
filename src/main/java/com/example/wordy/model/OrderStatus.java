package com.example.wordy.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Collection;

@Table(name = "OrderStatuses")
@Entity
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 5, max = 50, message = "Название должно быть от 5 до 50 символов!!!")
    private String title;


    @OneToMany(mappedBy = "orderStatus", cascade = CascadeType.ALL)
    private Collection<OrderModel> orders;


    public OrderStatus() {}

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

    public Collection<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(Collection<OrderModel> orders) {
        this.orders = orders;
    }
}
