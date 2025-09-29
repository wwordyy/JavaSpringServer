package com.example.wordy.controller.Manufacturer;

import com.example.wordy.model.ManufacturerModel;
import com.example.wordy.service.ManufacturerService;
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
@RequestMapping("/api/manufacturer")
@Tag(name = "Manufacturers", description = "API для управления производителями")
public class ManufacturerRestController {

    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerRestController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping()
    @Operation(summary = "Вывод всех производителей")
    public List<ManufacturerModel> getAllManufacturers() {
        return manufacturerService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Вывод производителя по его ID")
    public ResponseEntity<ManufacturerModel> getManufacturerById(@PathVariable int id) {
        Optional<ManufacturerModel> manufacturerModel = manufacturerService.findById(id);

        if (manufacturerModel.isPresent()) {
            return new ResponseEntity<>(manufacturerModel.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление производителя по ID")
    public ResponseEntity<HttpStatus> updateManufacturer(@PathVariable int id,
                                                         @RequestBody @Valid ManufacturerModel manufacturer,
                                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ManufacturerModel oldManufacturer = manufacturerService.findById(id).orElse(null);

        if (oldManufacturer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        oldManufacturer.setName(manufacturer.getName());
        oldManufacturer.setProducts(manufacturer.getProducts());
        manufacturerService.save(oldManufacturer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    @Operation(summary = "Создание производителя")
    public ResponseEntity<HttpStatus> createManufacturer(@RequestBody @Valid ManufacturerModel manufacturer,
                                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        manufacturerService.save(manufacturer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление производителя по ID")
    public ResponseEntity<HttpStatus> deleteManufacturer(@PathVariable int id) {
        Optional<ManufacturerModel> manufacturerModel = manufacturerService.findById(id);
        if (manufacturerModel.isPresent()) {
            manufacturerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
