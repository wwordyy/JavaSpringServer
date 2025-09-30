package org.example.userservice.repository;

import org.example.userservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
}
