package com.example.wordy.repository;

import com.example.wordy.model.PersonalDateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalDataRepo extends JpaRepository<PersonalDateModel, Integer> {
    Optional<PersonalDateModel> findByLogin(String login);
}
