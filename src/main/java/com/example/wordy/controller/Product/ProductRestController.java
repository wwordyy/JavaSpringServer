package com.example.wordy.controller.Product;


import com.example.wordy.model.ProductModel;
import com.example.wordy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping()
    public List<ProductModel> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") int id) {

       Optional<ProductModel> product  = productService.findById(id);

       if (product.isPresent())
       {
           return ResponseEntity.ok(product.get());
       }
       return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> addProduct(@RequestBody @Valid ProductModel product,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        productService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
