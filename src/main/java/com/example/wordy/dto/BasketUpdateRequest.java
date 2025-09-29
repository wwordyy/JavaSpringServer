package com.example.wordy.dto;

import javax.validation.constraints.NotNull;

public class BasketUpdateRequest {

    @NotNull
    private Integer idCustomer;
    @NotNull
    private Integer idProduct;

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }
}
