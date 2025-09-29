package com.example.wordy.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import java.util.Collection;

@Table(name = "Products")
@Entity
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Min(value = 0,message = "Поле должно быть больше 0!!!")
    private double price;

    @Size(min= 5, message = "Количество символов должно быть больше 5!!!`")
    private String productDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_of_product_id", referencedColumnName = "id")
    @NotNull(message = "Поле не может быть пустым")
    @JsonIgnoreProperties("products")
    private TypeOfProductModel typeOfProduct;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    @NotNull(message = "Поле не может быть пустым")
    @JsonIgnoreProperties("products")
    private ManufacturerModel manufacturer;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id",referencedColumnName = "id")
    @NotNull(message = "Поле не может быть пустым")
    @JsonIgnoreProperties("products")
    private ModelModel model;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id",referencedColumnName = "id")
    @NotNull(message = "Поле не может быть пустым")
    @JsonIgnoreProperties("products")
    private CountryModel country;


    @JsonIgnore
    @ManyToMany
    @JoinTable (
                name="product_in_basket",
                joinColumns=@JoinColumn(name = "product_id", referencedColumnName = "id"),
                inverseJoinColumns=@JoinColumn(name = "basket_id", referencedColumnName = "id"))
    private Collection<BasketModel> baskets;



    public int getId() {
        return id;
    }
    public double getPrice() {
        return price;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public TypeOfProductModel getTypeOfProduct() {
        return typeOfProduct;
    }
    public ManufacturerModel getManufacturer() {
        return manufacturer;
    }
    public ModelModel getModel() {
        return model;
    }
    public CountryModel getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public void setTypeOfProduct(TypeOfProductModel typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }
    public void setManufacturer(ManufacturerModel manufacturer) {
        this.manufacturer = manufacturer;
    }
    public void setModel(ModelModel model) {
        this.model = model;
    }
    public void setCountry(CountryModel country) {
        this.country = country;
    }

    public Collection<BasketModel> getBaskets() {
        return baskets;
    }

    public void setBaskets(Collection<BasketModel> baskets) {
        this.baskets = baskets;
    }
}
