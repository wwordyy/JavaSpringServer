package org.example.productservice.service;

import org.example.productservice.model.ModelProduct;
import org.example.productservice.repository.ModelProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class ModelProductService {


    private final ModelProductRepo modelProductRepo;

    @Autowired
    public ModelProductService(ModelProductRepo modelProductRepo) {
        this.modelProductRepo = modelProductRepo;
    }

    public List<ModelProduct> findAll() {
        return modelProductRepo.findAll();
    }

    public Optional<ModelProduct> findById(int id) {
        return modelProductRepo.findById(id);
    }

    public Optional<ModelProduct> findByTitle(String title) {
        return modelProductRepo.findByTitle(title);
    }

    @Transactional
    public void save(ModelProduct model)
    {
        modelProductRepo.save(model);
    }

    @Transactional
    public void update(ModelProduct model, int id)
    {
        model.setId(id);
        modelProductRepo.save(model);
    }

    @Transactional
    public void deleteById(int id) {
        modelProductRepo.deleteById(id);
    }

    @Transactional
    public void delete(ModelProduct model)
    {
        modelProductRepo.delete(model);
    }
}
