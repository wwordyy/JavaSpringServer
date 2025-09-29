package com.example.wordy.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

@Table(name = "Orders")
@Entity
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String numberOrder;


    @CreationTimestamp
    private Date dateCreateOrder;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "idCustomer")
    @NotNull(message = "Поле обязательно к заполнению!")
    private CustomerModel customer;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_status_id", referencedColumnName = "id")
    @NotNull(message = "Поле обязательно к заполнению!")
    private OrderStatus orderStatus;





    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private CheckModel check;


    public OrderModel(CustomerModel customer,
                      OrderStatus orderStatus, long numberOrder) {
        this.numberOrder = "№ "+ numberOrder;
        this.customer = customer;
        this.orderStatus = orderStatus;
    }

    public OrderModel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public Date getDateCreateOrder() {
        return dateCreateOrder;
    }

    public void setDateCreateOrder(Date dateCreateOrder) {
        this.dateCreateOrder = dateCreateOrder;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public OrderStatus getOrderStatusId() {
        return orderStatus;
    }

    public void setOrderStatusId(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }



    public CheckModel getCheck() {
        return check;
    }

    public void setCheck(CheckModel check) {
        this.check = check;
    }


}
