package com.example.wordy.controller.TypeOfProduct;

import com.example.wordy.model.TypeOfProductModel;
import com.example.wordy.service.TypeOfProductService;
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
@RequestMapping("/api/typesOfProducts")
@Tag(name = "TypesOfProducts", description = "API для управления типами продуктов")
public class TypeOfProductRestController {

    private final TypeOfProductService typeOfProductService;

    @Autowired
    public TypeOfProductRestController(TypeOfProductService typeOfProductService) {
        this.typeOfProductService = typeOfProductService;
    }

    @GetMapping()
    @Operation(summary = "Вывод всех типов продуктов")
    public List<TypeOfProductModel> getAllTypeOfProducts() {
        return typeOfProductService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Вывод одного типа продукта по его ID")
    public ResponseEntity<TypeOfProductModel> getTypeOfProduct(@PathVariable int id) {
        Optional<TypeOfProductModel> typeOfProductModel = typeOfProductService.findById(id);

        if (typeOfProductModel.isPresent()) {
            return new ResponseEntity<>(typeOfProductModel.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    @Operation(summary = "Создание нового типа продукта")
    public ResponseEntity<HttpStatus> addTypeOfProduct(@RequestBody @Valid TypeOfProductModel typeOfProductModel,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        typeOfProductService.save(typeOfProductModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление типа продукта по ID")
    public ResponseEntity<HttpStatus> updateTypeOfProduct(@PathVariable int id,
                                                          @RequestBody @Valid TypeOfProductModel typeOfProductModel,
                                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        TypeOfProductModel oldType = typeOfProductService.findById(id).orElse(null);

        if (oldType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        oldType.setTitle(typeOfProductModel.getTitle());
        oldType.setProduct(typeOfProductModel.getProducts());

        typeOfProductService.save(oldType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление типа продукта по ID")
    public ResponseEntity<HttpStatus> deleteTypeOfProduct(@PathVariable int id) {
        Optional<TypeOfProductModel> typeOfProductModel = typeOfProductService.findById(id);
        if (typeOfProductModel.isPresent()) {
            typeOfProductService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
