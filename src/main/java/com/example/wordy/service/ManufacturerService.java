package com.example.wordy.service;

import com.example.wordy.model.ManufacturerModel;
import com.example.wordy.repository.ManufacturerRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class ManufacturerService {

    private final ManufacturerRepo manufacturerRepo;

    public ManufacturerService(ManufacturerRepo manufacturerRepo) {
        this.manufacturerRepo = manufacturerRepo;
    }

    public List<ManufacturerModel> findAll() {
        return manufacturerRepo.findAll();
    }

    public Optional<ManufacturerModel> findById(int id) {
        return manufacturerRepo.findById(id);
    }

    public Optional<ManufacturerModel> findByName(String name) {
        return manufacturerRepo.findByName(name);
    }

    @Transactional
    public void save(ManufacturerModel manufacturerModel) {
        manufacturerRepo.save(manufacturerModel);
    }

    @Transactional
    public void update(ManufacturerModel manufacturerModel, int id) {
        manufacturerModel.setId(id);
        manufacturerRepo.save(manufacturerModel);
    }

    @Transactional
    public void delete(ManufacturerModel manufacturerModel) {
        manufacturerRepo.delete(manufacturerModel);
    }

    @Transactional
    public void deleteById(int id) {
        manufacturerRepo.deleteById(id);
    }
}
