package com.example.wordy.service;

import com.example.wordy.model.CountryModel;
import com.example.wordy.repository.CountryRepo;
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

    public List<CountryModel> findAll() {
        return countryRepo.findAll();
    }

    public Optional<CountryModel> findByTitle(String title) {
        return countryRepo.findByTitle(title);
    }

    public Optional<CountryModel> findById(int id)
    {
        return countryRepo.findById(id);
    }

    @Transactional
    public void save(CountryModel country) {
        countryRepo.save(country);
    }

    @Transactional
    public void delete(CountryModel country) {
        countryRepo.delete(country);
    }

    @Transactional
    public void update(CountryModel country, int id) {
        country.setId(id);
        countryRepo.save(country);
    }

}
