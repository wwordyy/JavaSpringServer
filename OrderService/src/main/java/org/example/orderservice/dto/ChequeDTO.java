package org.example.orderservice.dto;

import jakarta.validation.constraints.NotNull;

public class ChequeDTO {

    @NotNull
    private Long totalPrice;

    private OrderDTO order;

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public @NotNull Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(@NotNull Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
