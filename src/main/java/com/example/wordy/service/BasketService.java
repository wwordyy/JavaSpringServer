package com.example.wordy.service;

import com.example.wordy.model.BasketModel;
import com.example.wordy.model.OrderModel;
import com.example.wordy.repository.BasketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BasketService {

    private final BasketRepo basketRepo;


    @Autowired
    public BasketService(BasketRepo basketRepo) {
        this.basketRepo = basketRepo;
    }

    public List<BasketModel> findAll() {
        return basketRepo.findAll();
    }

    public Optional<BasketModel> findById(int id) {
        return basketRepo.findById(id);
    }

    public Optional<BasketModel> findByQuantity(int quantity) {
        return basketRepo.findByQuantity(quantity);
    }


    @Transactional
    public void save(BasketModel basket) {
        basketRepo.save(basket);
    }

    @Transactional
    public void delete(BasketModel basket) {
        basketRepo.delete(basket);
    }

    @Transactional
    public void update(BasketModel basket, int idUpd) {
        basket.setId(idUpd);
    }


    @Transactional
    public void clearBasket(OrderModel orderUser) {

        BasketModel basketUser = orderUser.getCustomer().getBasket();

        basketUser.getProducts().forEach(product -> {product.getBaskets().remove(basketUser);});

        basketUser.getProducts().clear();

        basketRepo.save(basketUser);
    }
}
