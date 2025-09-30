package org.example.orderservice.service;

import org.example.orderservice.model.Basket;
import org.example.orderservice.repository.BasketRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BasketService {

    private final BasketRepo basketRepo;

    public BasketService(BasketRepo basketRepo) {
        this.basketRepo = basketRepo;
    }

    public List<Basket> findAll() {
        return basketRepo.findAll();
    }

    public Optional<Basket> findById(int id) {
        return basketRepo.findById(id);
    }

    @Transactional
    public Basket save(Basket basket) {
        return basketRepo.save(basket);
    }
}
