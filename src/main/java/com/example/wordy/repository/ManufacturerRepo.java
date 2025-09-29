package com.example.wordy.repository;

import com.example.wordy.model.ManufacturerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepo extends JpaRepository<ManufacturerModel, Integer> {
    Optional<ManufacturerModel> findByName(String name);
}
