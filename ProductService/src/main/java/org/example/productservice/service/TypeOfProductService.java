package org.example.productservice.service;

import org.example.productservice.model.TypeOfProduct;
import org.example.productservice.repository.TypeOfProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TypeOfProductService {

    private final TypeOfProductRepo typeOfProductRepo;

    @Autowired
    public TypeOfProductService(TypeOfProductRepo typeOfProductRepo) {
        this.typeOfProductRepo = typeOfProductRepo;
    }

    public List<TypeOfProduct> findAll() {
        return typeOfProductRepo.findAll();
    }

    public Optional<TypeOfProduct> findById(int id) {
        return typeOfProductRepo.findById(id);
    }


    public Optional<TypeOfProduct> findByTitle(String title) {
        return typeOfProductRepo.findByTitle(title);
    }

    @Transactional
    public void save(TypeOfProduct TypeOfProduct) {
        typeOfProductRepo.save(TypeOfProduct);
    }

    @Transactional
    public void delete(TypeOfProduct TypeOfProduct) {
        typeOfProductRepo.delete(TypeOfProduct);
    }

    @Transactional
    public void update(TypeOfProduct TypeOfProduct, int id) {
        TypeOfProduct.setId(id);
        typeOfProductRepo.save(TypeOfProduct);
    }

    @Transactional
    public void deleteById(int id) {
        typeOfProductRepo.deleteById(id);
    }

}
