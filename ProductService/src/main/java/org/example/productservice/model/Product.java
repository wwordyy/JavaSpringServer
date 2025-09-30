package org.example.productservice.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Table(name = "Products")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Min(value = 0, message = "Поле должно быть больше 0!!!")
    private double price;

    @Size(min = 5, message = "Количество символов должно быть больше 5!!!`")
    private String productDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_of_product_id", referencedColumnName = "id")
    @NotNull(message = "Поле не может быть пустым")
    private TypeOfProduct typeOfProduct;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    @NotNull(message = "Поле не может быть пустым")
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    @NotNull(message = "Поле не может быть пустым")
    private ModelProduct model;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @NotNull(message = "Поле не может быть пустым")
    private Country country;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Min(value = 0, message = "Поле должно быть больше 0!!!")
    public double getPrice() {
        return price;
    }

    public void setPrice(@Min(value = 0, message = "Поле должно быть больше 0!!!") double price) {
        this.price = price;
    }

    public @Size(min = 5, message = "Количество символов должно быть больше 5!!!`") String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(@Size(min = 5, message = "Количество символов должно быть больше 5!!!`") String productDescription) {
        this.productDescription = productDescription;
    }

    public @NotNull(message = "Поле не может быть пустым") TypeOfProduct getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(@NotNull(message = "Поле не может быть пустым") TypeOfProduct typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public @NotNull(message = "Поле не может быть пустым") Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(@NotNull(message = "Поле не может быть пустым") Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public @NotNull(message = "Поле не может быть пустым") ModelProduct getModel() {
        return model;
    }

    public void setModel(@NotNull(message = "Поле не может быть пустым") ModelProduct model) {
        this.model = model;
    }

    public @NotNull(message = "Поле не может быть пустым") Country getCountry() {
        return country;
    }

    public void setCountry(@NotNull(message = "Поле не может быть пустым") Country country) {
        this.country = country;
    }
}
