package org.example.orderservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Cheques")
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_cheque")
    private int id;

    @Min(value = 0, message = "Поле должно быть больше 0!")
    @Column(name = "total_price")
    private Long totalPrice;

    @CreationTimestamp
    @Column(name = "check_issue_date")
    private LocalDateTime checkIssueDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "ID_order")
    private Order order;


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


    public LocalDateTime getCheckIssueDate() {
        return checkIssueDate;
    }

    public void setCheckIssueDate(LocalDateTime checkIssueDate) {
        this.checkIssueDate = checkIssueDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
