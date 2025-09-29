package com.example.wordy.service;

import com.example.wordy.model.TypeOfProductModel;
import com.example.wordy.repository.TypeOfProductRepo;
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

    public List<TypeOfProductModel> findAll() {
        return typeOfProductRepo.findAll();
    }

    public Optional<TypeOfProductModel> findById(int id) {
        return typeOfProductRepo.findById(id);
    }


    public Optional<TypeOfProductModel> findByTitle(String title) {
        return typeOfProductRepo.findByTitle(title);
    }

    @Transactional
    public void save(TypeOfProductModel typeOfProductModel) {
        typeOfProductRepo.save(typeOfProductModel);
    }

    @Transactional
    public void delete(TypeOfProductModel typeOfProductModel) {
        typeOfProductRepo.delete(typeOfProductModel);
    }

    @Transactional
    public void update(TypeOfProductModel typeOfProductModel, int id) {
        typeOfProductModel.setId(id);
        typeOfProductRepo.save(typeOfProductModel);
    }

    @Transactional
    public void deleteById(int id) {
        typeOfProductRepo.deleteById(id);
    }

}
