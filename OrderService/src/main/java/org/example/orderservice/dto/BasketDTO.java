package org.example.orderservice.dto;


import jakarta.validation.constraints.NotNull;

public class BasketDTO {

    @NotNull
    private Integer userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
