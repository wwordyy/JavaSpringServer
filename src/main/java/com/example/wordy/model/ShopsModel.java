package com.example.wordy.model;


import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Collection;

@Table(name = "Shops")
@Entity
public class ShopsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, max = 50, message = "Количество символов должно быть от 2 до 50!!!")
    private String title;

    @Size(min = 3, max = 70, message = "Количество символов должно быть от 3 до 70!!!")
    private String address;

    @OneToMany(mappedBy = "shops", cascade = CascadeType.ALL)
    private Collection<CustomerModel> customers;



    public ShopsModel(){}

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Collection<CustomerModel> getCustomers() {
        return customers;
    }

    public void setCustomers(Collection<CustomerModel> customers) {
        this.customers = customers;
    }
}
