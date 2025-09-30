package org.example.orderservice.dto;

import jakarta.validation.constraints.NotNull;

public class OrderStatusDTO {

    @NotNull
    private String title;


    public @NotNull String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }
}
