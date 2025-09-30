package org.example.productservice.repository;

import org.example.productservice.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepo extends JpaRepository<Manufacturer, Integer> {
    Optional<Manufacturer> findByName(String name);
}
