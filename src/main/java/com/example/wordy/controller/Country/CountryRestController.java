package com.example.wordy.controller.Country;

import com.example.wordy.model.CountryModel;
import com.example.wordy.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Countries", description = "API для управления странами производителей")
public class CountryRestController {

    private final CountryService countryService;

    @Autowired
    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping()
    @Operation(summary = "Вывод всех стран производителей")
    public List<CountryModel> getAllCountries() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Вывод одной страны по ID")
    public ResponseEntity<CountryModel> getCountryById(@PathVariable int id) {
        Optional<CountryModel> country = countryService.findById(id);

        if (country.isPresent()) {
            return new ResponseEntity<>(country.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping()
    @Operation(summary = "Создание страны производителя")
    public ResponseEntity<HttpStatus> createCountry(@RequestBody @Valid CountryModel country,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        countryService.save(country);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление страны")
    public ResponseEntity<HttpStatus> updateCountry(@PathVariable int id,
                                                    @RequestBody @Valid CountryModel country,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CountryModel newCountry = countryService.findById(id).orElse(null);
        if (newCountry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        newCountry.setTitle(country.getTitle());
        newCountry.setProducts(country.getProducts());
        countryService.save(newCountry);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление страны по ID")
    public ResponseEntity<HttpStatus> deleteCountry(@PathVariable int id) {
        CountryModel country = countryService.findById(id).orElse(null);
        if (country == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        countryService.delete(country);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
