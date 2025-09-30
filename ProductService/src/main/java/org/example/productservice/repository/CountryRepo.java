package org.example.productservice.repository;

import org.example.productservice.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepo extends JpaRepository<Country, Integer> {
    Optional<Country> findByTitle(String title);
}
