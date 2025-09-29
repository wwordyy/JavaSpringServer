package com.example.wordy.controller.Manufacturer;

import com.example.wordy.model.ManufacturerModel;
import com.example.wordy.service.ManufacturerService;
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
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }


    @GetMapping("/manufacturers")
    public String getAllManufacturers(Model model) {
        Iterable<ManufacturerModel> manufacturerModels = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturerModels);
        model.addAttribute("manufacturer", new ManufacturerModel());

        return "Manufacturer/manufacturers";
    }

    @PostMapping("/manufacturers/add")
    public String addManufacturer(@ModelAttribute("manufacturer")
                                  @Valid ManufacturerModel manufacturerModel,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Manufacturer/manufacturers";
        }

        manufacturerService.save(manufacturerModel);
        return "redirect:/manufacturers";
    }

    @PostMapping("/manufacturers/update")
    public String updateManufacturer(@RequestParam int id,
                                     @ModelAttribute("manufacturer")
                                     @Valid ManufacturerModel manufacturerModel,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "Manufacturer/manufacturers";
        }
        ManufacturerModel existingManufacturer = manufacturerService.findById(id).orElseThrow();
        existingManufacturer.setName(manufacturerModel.getName());
        manufacturerService.save(existingManufacturer);
        return "redirect:/manufacturers";
    }

    @PostMapping("/manufacturers/delete")
    public String deleteManufacturer(@RequestParam int id) {
        ManufacturerModel manufacturerModel = manufacturerService.findById(id).orElseThrow();
        manufacturerService.delete(manufacturerModel);
        return "redirect:/manufacturers";
    }

    @PostMapping("/manufacturers/findById")
    public String findManufacturerById(@RequestParam int id, Model model) {
        try {
            var manufacturerData = manufacturerService.findById(id).orElseThrow();
            model.addAttribute("manufacturer", manufacturerData);
            return "Manufacturer/manufacturerDetails";
        } catch (Exception e) {
            return "redirect:/manufacturers";
        }
    }

    @PostMapping("/manufacturers/findByName")
    public String findManufacturerByName(@RequestParam String name, Model model) {
        Optional<ManufacturerModel> manufacturerModel = manufacturerService.findByName(name);
        if (manufacturerModel.isPresent()) {
            model.addAttribute("manufacturer", manufacturerModel.get());
            return "Manufacturer/manufacturerDetails";
        } else {
            return "redirect:/manufacturers";
        }
    }
}
