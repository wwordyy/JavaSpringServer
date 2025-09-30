package org.example.productservice.repository;

import org.example.productservice.model.ModelProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelProductRepo extends JpaRepository<ModelProduct, Integer> {
    Optional<ModelProduct> findByTitle(String title);
}
