package com.example.wordy.repository;

import com.example.wordy.model.CheckModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckRepo extends JpaRepository<CheckModel, Integer> {
    Optional<CheckModel> findByTotalPrice(int totalPrice);
}
