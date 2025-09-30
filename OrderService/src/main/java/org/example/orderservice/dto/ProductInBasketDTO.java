package org.example.orderservice.dto;

import jakarta.validation.constraints.NotNull;

public class ProductInBasketDTO {

    @NotNull
    private Integer productId;

    @NotNull
    private BasketDTO basket;



    public @NotNull Integer getProductId() {
        return productId;
    }

    public void setProductId(@NotNull Integer productId) {
        this.productId = productId;
    }

    public @NotNull BasketDTO getBasket() {
        return basket;
    }

    public void setBasket(@NotNull BasketDTO basket) {
        this.basket = basket;
    }
}
