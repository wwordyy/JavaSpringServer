package com.example.wordy.repository;

import com.example.wordy.model.ModelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepo extends JpaRepository<ModelModel, Integer> {
    Optional<ModelModel> findByTitle(String title);
}
