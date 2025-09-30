package org.example.orderservice.repository;

import org.example.orderservice.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepo extends JpaRepository<Basket, Integer> {

}
