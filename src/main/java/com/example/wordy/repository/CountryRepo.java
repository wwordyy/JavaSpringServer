package com.example.wordy.repository;

import com.example.wordy.model.CountryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepo extends JpaRepository<CountryModel, Integer> {
    Optional<CountryModel> findByTitle(String title);
}
