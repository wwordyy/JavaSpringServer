package com.example.wordy.service;

import com.example.wordy.model.ModelModel;
import com.example.wordy.repository.ModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class ModelService {


    private final ModelRepo modelRepo;

    @Autowired
    public ModelService(ModelRepo modelRepo) {
        this.modelRepo = modelRepo;
    }

    public List<ModelModel> findAll() {
        return modelRepo.findAll();
    }

    public Optional<ModelModel> findById(int id) {
        return modelRepo.findById(id);
    }

    public Optional<ModelModel> findByTitle(String title) {
        return modelRepo.findByTitle(title);
    }

    @Transactional
    public void save(ModelModel model)
    {
        modelRepo.save(model);
    }

    @Transactional
    public void update(ModelModel model, int id)
    {
        model.setId(id);
        modelRepo.save(model);
    }

    @Transactional
    public void deleteById(int id) {
        modelRepo.deleteById(id);
    }

    @Transactional
    public void delete(ModelModel model)
    {
        modelRepo.delete(model);
    }
}
