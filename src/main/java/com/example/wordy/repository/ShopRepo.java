package com.example.wordy.repository;

import com.example.wordy.model.ShopsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepo extends JpaRepository<ShopsModel, Integer> {
    Optional<ShopsModel> findByTitle(String title);
}
