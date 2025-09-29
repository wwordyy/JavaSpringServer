package com.example.wordy.controller.Country;

import com.example.wordy.model.CountryModel;
import com.example.wordy.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public String getAllCountries(Model model) {
        Iterable<CountryModel> countryModels = countryService.findAll();
        model.addAttribute("countries", countryModels);
        model.addAttribute("country", new CountryModel());

        return "Country/countries";
    }

    @PostMapping("/countries/add")
    public String addCountry(@ModelAttribute("country")
                             @Valid CountryModel countryModel,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Country/countries";
        }

        countryService.save(countryModel);
        return "redirect:/countries";
    }

    @PostMapping("/countries/update")
    public String updateCountry(@RequestParam int id,
                                @ModelAttribute("country")
                                @Valid CountryModel countryModel,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Country/countries";
        }
        CountryModel existingCountry = countryService.findById(id).get();
        existingCountry.setTitle(countryModel.getTitle());
        countryService.save(existingCountry);
        return "redirect:/countries";
    }

    @PostMapping("/countries/delete")
    public String deleteCountry(@RequestParam int id) {
        CountryModel countryModel = countryService.findById(id).get();
        countryService.delete(countryModel);
        return "redirect:/countries";
    }

    @PostMapping("/countries/findById")
    public String findCountryById(@RequestParam int id, Model model) {
        try {
            var countryData = countryService.findById(id);
            model.addAttribute("country", countryData);
            return "Country/countryDetails";
        } catch (Exception e) {
            return "redirect:/countries";
        }
    }

    @PostMapping("/countries/findByTitle")
    public String findCountryByTitle(@RequestParam String title, Model model) {
        Optional<CountryModel> countryModel = countryService.findByTitle(title);
        if (countryModel.isPresent()) {
            model.addAttribute("country", countryModel.get());
            return "Country/countryDetails";
        } else {
            return "redirect:/countries";
        }
    }
}
