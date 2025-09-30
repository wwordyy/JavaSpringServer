package org.example.orderservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Product_In_Basket")
public class ProductInBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_product_in_basket")
    private int id;

    @Column(name = "product_id")
    private int product;


    @ManyToOne
    @JoinColumn(
            name = "basket_id", referencedColumnName = "ID_basket"
    )
    private Basket basket;

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
