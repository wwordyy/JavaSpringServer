package org.example.orderservice.service;

import org.example.orderservice.model.ProductInBasket;
import org.example.orderservice.repository.ProductInBasketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductInBasketService {

    private final ProductInBasketRepo productInBasketRepo;

    @Autowired
    public ProductInBasketService(ProductInBasketRepo productInBasketRepo) {
        this.productInBasketRepo = productInBasketRepo;
    }

    public List<ProductInBasket> findAll() {
        return productInBasketRepo.findAll();
    }

    public Optional<ProductInBasket> findById(int id) {
        return productInBasketRepo.findById(id);
    }

    @Transactional
    public void save(ProductInBasket productInBasket) {
        productInBasketRepo.save(productInBasket);
    }

}
