package org.example.orderservice.repository;

import org.example.orderservice.model.ProductInBasket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInBasketRepo extends JpaRepository<ProductInBasket, Integer> {
}
