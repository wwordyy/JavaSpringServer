package org.example.userservice.service;

import jakarta.persistence.Entity;
import org.example.userservice.model.Review;
import org.example.userservice.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepo reviewRepo;

    @Autowired
    public ReviewService(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    public List<Review> findAll()
    {
        return reviewRepo.findAll();
    }

    public Optional<Review> findById(int id)
    {
        return reviewRepo.findById(id);
    }

    @Transactional
    public void save(Review review)
    {
        reviewRepo.save(review);
    }
}
