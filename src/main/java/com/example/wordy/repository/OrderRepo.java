package com.example.wordy.repository;

import com.example.wordy.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<OrderModel, Integer> {
    Optional<OrderModel> findByNumberOrder(String numberOrder);
}
