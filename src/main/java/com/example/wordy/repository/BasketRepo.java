package com.example.wordy.repository;

import com.example.wordy.model.BasketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepo extends JpaRepository<BasketModel, Integer> {
    Optional<BasketModel> findByQuantity(int id);
}
