package com.example.wordy.repository;

import com.example.wordy.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductModel, Integer> {

    Optional<ProductModel> findByProductDescription(String productDescription);
}
