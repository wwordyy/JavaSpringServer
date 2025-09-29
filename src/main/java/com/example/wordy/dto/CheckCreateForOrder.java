package com.example.wordy.dto;

import javax.validation.constraints.NotNull;

public class CheckCreateForOrder {

    @NotNull
    private Integer orderUser;

    @NotNull
    private String emailUser;

    @NotNull
    private Long totalPrice;

    public @NotNull Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(@NotNull Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public @NotNull Integer getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(@NotNull Integer orderUser) {
        this.orderUser = orderUser;
    }

    public @NotNull String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(@NotNull String emailUser) {
        this.emailUser = emailUser;
    }

}
