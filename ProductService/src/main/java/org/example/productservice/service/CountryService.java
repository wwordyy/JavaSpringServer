package org.example.productservice.service;

import org.example.productservice.model.Country;
import org.example.productservice.repository.CountryRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CountryService {

    private final CountryRepo countryRepo;

    public CountryService(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    public List<Country> findAll() {
        return countryRepo.findAll();
    }

    public Optional<Country> findByTitle(String title) {
        return countryRepo.findByTitle(title);
    }

    public Optional<Country> findById(int id)
    {
        return countryRepo.findById(id);
    }

    @Transactional
    public void save(Country country) {
        countryRepo.save(country);
    }

    @Transactional
    public void delete(Country country) {
        countryRepo.delete(country);
    }

    @Transactional
    public void update(Country country, int id) {
        country.setId(id);
        countryRepo.save(country);
    }

}
