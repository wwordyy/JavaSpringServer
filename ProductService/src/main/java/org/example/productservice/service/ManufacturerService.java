package org.example.productservice.service;


import org.example.productservice.model.Manufacturer;
import org.example.productservice.repository.ManufacturerRepo;
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

    public List<Manufacturer> findAll() {
        return manufacturerRepo.findAll();
    }

    public Optional<Manufacturer> findById(int id) {
        return manufacturerRepo.findById(id);
    }

    public Optional<Manufacturer> findByName(String name) {
        return manufacturerRepo.findByName(name);
    }

    @Transactional
    public void save(Manufacturer Manufacturer) {
        manufacturerRepo.save(Manufacturer);
    }

    @Transactional
    public void update(Manufacturer Manufacturer, int id) {
        Manufacturer.setId(id);
        manufacturerRepo.save(Manufacturer);
    }

    @Transactional
    public void delete(Manufacturer Manufacturer) {
        manufacturerRepo.delete(Manufacturer);
    }

    @Transactional
    public void deleteById(int id) {
        manufacturerRepo.deleteById(id);
    }
}
