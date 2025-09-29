package com.example.wordy.controller.Model;

import com.example.wordy.model.ModelModel;
import com.example.wordy.service.ModelService;
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
@RequestMapping("/api/models")
@Tag(name = "Models", description = "API для управления моделями")
public class ModelControllerRest {

    private final ModelService modelService;

    @Autowired
    public ModelControllerRest(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping()
    @Operation(summary = "Вывод всех моделей")
    public List<ModelModel> getAllModels() {
        return modelService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Вывод одной модели по ID")
    public ResponseEntity<ModelModel> getModelById(@PathVariable int id) {
        Optional<ModelModel> model = modelService.findById(id);

        if (model.isPresent()) {
            return new ResponseEntity<>(model.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @PostMapping()
    @Operation(summary = "Создание модели")
    public ResponseEntity<HttpStatus> createModel(@RequestBody @Valid ModelModel model,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        modelService.save(model);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление модели по ID")
    public ResponseEntity<HttpStatus> updateModel(@PathVariable int id,
                                                  @RequestBody @Valid ModelModel model,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ModelModel oldModel = modelService.findById(id).orElse(null);
        if (oldModel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        oldModel.setTitle(model.getTitle());
        oldModel.setProducts(model.getProducts());

        modelService.save(oldModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление модели по ID")
    public ResponseEntity<HttpStatus> deleteModel(@PathVariable int id) {
        Optional<ModelModel> model = modelService.findById(id);
        if (model.isPresent()) {
            modelService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
