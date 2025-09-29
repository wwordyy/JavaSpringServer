package com.example.wordy.service;

import com.example.wordy.model.*;
import com.example.wordy.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService{

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Optional<ProductModel> findByProductDescription(String description) {
        return productRepo.findByProductDescription(description);
    }

    public List<ProductModel> findAll() {
        return productRepo.findAll();
    }

    public Optional<ProductModel> findById(int id) {
        return productRepo.findById(id);
    }

    @Transactional
    public void save(ProductModel productModel)
    {
        productRepo.save(productModel);
    }

    @Transactional
    public void delete(ProductModel productModel) {
        productRepo.deleteById(productModel.getId());
    }

    @Transactional
    public void update(ProductModel productModel, int idUpd) {
        productModel.setId(idUpd);
        productRepo.save(productModel);
    }
}