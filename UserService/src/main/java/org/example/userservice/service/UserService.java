package org.example.userservice.service;

import jakarta.persistence.Entity;
import org.example.userservice.model.User;
import org.example.userservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepo userRepo;


    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> findAll()
    {
        return userRepo.findAll();
    }

    public Optional<User> findById(int id)
    {
        return userRepo.findById(id);
    }

    public Optional<User> findByUsername(String username)
    {
        return userRepo.findByUsername(username);
    }


    @Transactional
    public void save(User user)
    {
        userRepo.save(user);
    }
}
