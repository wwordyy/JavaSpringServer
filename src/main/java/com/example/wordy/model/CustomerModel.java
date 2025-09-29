package com.example.wordy.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "Customers")
@Entity
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCustomer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_date_id", referencedColumnName = "idPersonalDate")
    @NotNull(message = "Выберите личные данные!")
    private PersonalDateModel personalDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @NotNull(message = "Выберите магазин")
    private ShopsModel shops;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    @NotNull(message = "Необходимо выбрать корзину")
    private BasketModel basket;


    public CustomerModel(PersonalDateModel personalDate, ShopsModel shops, BasketModel basket) {
        this.personalDate = personalDate;
        this.shops = shops;
        this.basket = basket;
    }


    public CustomerModel() {}

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public PersonalDateModel getPersonalDate() {
        return personalDate;
    }
    public void setPersonalDate(PersonalDateModel personalDate) {
        this.personalDate = personalDate;
    }


    public ShopsModel getShops() {
        return shops;
    }

    public void setShops(ShopsModel shops) {
        this.shops = shops;
    }


    public @NotNull(message = "Необходимо выбрать корзину") BasketModel getBasket() {
        return basket;
    }

    public void setBasket(@NotNull(message = "Необходимо выбрать корзину") BasketModel basket) {
        this.basket = basket;
    }
}
