package org.example.productservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductDTO {



    @Min(value = 0, message = "Поле должно быть больше 0!!!")
    @NotNull
    private Double price;

    @Size(min = 5, message = "Количество символов должно быть больше 5!!!")
    @NotNull
    private String productDescription;

    @NotNull
    private ManufacturerDTO manufacturer;

    @NotNull
    private CountryDTO country;

    @NotNull
    private TypeOfProductDTO typeOfProduct;

    @NotNull
    private ModelProductDTO modelProduct;


    public ManufacturerDTO getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDTO manufacturer) {
        this.manufacturer = manufacturer;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public TypeOfProductDTO getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(TypeOfProductDTO typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public ModelProductDTO getModelProduct() {
        return modelProduct;
    }

    public void setModelProduct(ModelProductDTO modelProduct) {
        this.modelProduct = modelProduct;
    }

    public @Min(value = 0, message = "Поле должно быть больше 0!!!") @NotNull Double getPrice() {
        return price;
    }

    public void setPrice(@Min(value = 0, message = "Поле должно быть больше 0!!!") @NotNull Double price) {
        this.price = price;
    }

    public @Size(min = 5, message = "Количество символов должно быть больше 5!!!") @NotNull String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(@Size(min = 5, message = "Количество символов должно быть больше 5!!!") @NotNull String productDescription) {
        this.productDescription = productDescription;
    }
}
