package com.example.wordy.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;


@Table(name = "Checks")
@Entity
public class CheckModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = 0, message = "Поле должно быть больше 0!")
    private Long totalPrice;

    @CreationTimestamp
    private Date checkIssueDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderModel order;


    public CheckModel(Long totalPrice, OrderModel order) {
        this.totalPrice = totalPrice;
        this.order = order;
    }

    public CheckModel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCheckIssueDate() {
        return checkIssueDate;
    }

    public void setCheckIssueDate(Date checkIssueDate) {
        this.checkIssueDate = checkIssueDate;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }
}
