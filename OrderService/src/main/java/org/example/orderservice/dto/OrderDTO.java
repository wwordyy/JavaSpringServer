package org.example.orderservice.dto;

import jakarta.validation.constraints.NotNull;

public class OrderDTO {

    @NotNull
    private Integer orderNumber;

    @NotNull
    private OrderStatusDTO orderStatus;

    @NotNull
    private BasketDTO basket;

    public @NotNull Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(@NotNull Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BasketDTO getBasket() {
        return basket;
    }

    public void setBasket(BasketDTO basket) {
        this.basket = basket;
    }

    public OrderStatusDTO getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusDTO orderStatus) {
        this.orderStatus = orderStatus;
    }
}
