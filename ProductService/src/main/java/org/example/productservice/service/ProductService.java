package org.example.productservice.service;

import org.example.productservice.model.Product;
import org.example.productservice.repository.ProductRepo;
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

    public Optional<Product> findByProductDescription(String description) {
        return productRepo.findByProductDescription(description);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Optional<Product> findById(int id) {
        return productRepo.findById(id);
    }

    @Transactional
    public void save(Product Product)
    {
        productRepo.save(Product);
    }

    @Transactional
    public void delete(Product Product) {
        productRepo.deleteById(Product.getId());
    }

    @Transactional
    public void update(Product Product, int idUpd) {
        Product.setId(idUpd);
        productRepo.save(Product);
    }
}