package com.example.wordy.dto;

import javax.validation.constraints.NotNull;

public class ProductDeleteFromBasketRequest {

    @NotNull
    private Integer basketId;
    @NotNull
    private Integer productId;

    public @NotNull Integer getBasketId() {
        return basketId;
    }

    public void setBasketId(@NotNull Integer basketId) {
        this.basketId = basketId;
    }

    public @NotNull Integer getProductId() {
        return productId;
    }

    public void setProductId(@NotNull Integer productId) {
        this.productId = productId;
    }
}
