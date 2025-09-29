package com.example.wordy.service;

import com.example.wordy.model.ShopsModel;
import com.example.wordy.repository.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class ShopService   {

    private final ShopRepo shopRepo;


    @Autowired
    public ShopService(ShopRepo shopRepo) {
        this.shopRepo = shopRepo;
    }

    public List<ShopsModel> findAll() {
        return shopRepo.findAll();
    }

    public Optional<ShopsModel> findById(int id) {
        return shopRepo.findById(id);
    }

    public Optional<ShopsModel> findByTitle(String title) {
        return shopRepo.findByTitle(title);
    }

    @Transactional
    public void save(ShopsModel shop) {
        shopRepo.save(shop);
    }

    @Transactional
    public void delete(ShopsModel shop) {
        shopRepo.delete(shop);
    }

    @Transactional
    public void update(ShopsModel shop, int idUpd) {
        shop.setId(idUpd);
        shopRepo.save(shop);
    }

}
