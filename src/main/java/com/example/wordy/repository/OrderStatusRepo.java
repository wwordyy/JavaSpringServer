package com.example.wordy.repository;

import com.example.wordy.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStatusRepo extends JpaRepository<OrderStatus, Integer> {
    Optional<OrderStatus> findByTitle(String title);
}
