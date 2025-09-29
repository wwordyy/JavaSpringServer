package com.example.wordy.repository;

import com.example.wordy.model.TypeOfProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeOfProductRepo extends JpaRepository<TypeOfProductModel, Integer> {

    Optional<TypeOfProductModel> findByTitle(String title);
}
