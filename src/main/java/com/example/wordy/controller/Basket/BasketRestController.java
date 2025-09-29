package com.example.wordy.controller.Basket;

import com.example.wordy.dto.BasketUpdateRequest;
import com.example.wordy.dto.ProductDeleteFromBasketRequest;
import com.example.wordy.model.BasketModel;
import com.example.wordy.model.CustomerModel;
import com.example.wordy.model.ProductModel;
import com.example.wordy.service.BasketService;
import com.example.wordy.service.CustomerService;
import com.example.wordy.service.ProductService;
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
@RequestMapping("/api/baskets")
@Tag(name = "Baskets", description = "API для управления корзинами")
public class BasketRestController {

    private final BasketService basketService;
    private final ProductService productService;
    private final CustomerService customerService;

    @Autowired
    public BasketRestController(BasketService basketService, ProductService productService, CustomerService customerService) {
        this.basketService = basketService;
        this.productService = productService;
        this.customerService = customerService;
    }

    @GetMapping()
    @Operation(summary = "Вывод всех корзин")
    public List<BasketModel> getBaskets() {
        return basketService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Вывод одной корзины по ID")
    public ResponseEntity<BasketModel> getOneBasket(@PathVariable int id) {
        Optional<BasketModel> optionalBasket = basketService.findById(id);

        if (optionalBasket.isPresent()) {
            return ResponseEntity.ok(optionalBasket.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Создание корзины")
    public ResponseEntity<HttpStatus> createBasket(@RequestBody @Valid BasketModel basket,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        basketService.save(basket);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление корзины")
    public ResponseEntity<HttpStatus> updateBasket(@PathVariable int id,
                                                   @RequestBody @Valid BasketModel basket,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BasketModel updateBasket = basketService.findById(id).orElse(null);

        if (updateBasket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updateBasket.setProducts(basket.getProducts());
        updateBasket.setQuantity(basket.getQuantity());
        basketService.save(updateBasket);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление корзины по ID")
    public ResponseEntity<HttpStatus> deleteBasket(@PathVariable int id) {
        Optional<BasketModel> basket = basketService.findById(id);
        if (basket.isPresent()) {
            basketService.delete(basket.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/set/products")
    public ResponseEntity<HttpStatus> saveProductInBasket(@RequestBody @Valid BasketUpdateRequest requestData,
                                                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(requestData);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CustomerModel customer = customerService.findById(requestData.getIdCustomer());
        BasketModel basketUser = customer.getBasket();
        ProductModel productUser = productService.findById(requestData.getIdProduct()).get();



        basketUser.getProducts().add(productUser);
        basketService.save(basketUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{id}/get/products")
    public ResponseEntity<List<ProductModel>> getProductsFromBasket(@PathVariable("id") int id) {

        BasketModel basket = basketService.findById(id).orElse(null);

        return ResponseEntity.ok(basket.getProducts().stream().toList());

    }

    @DeleteMapping("/delete/product")
    public ResponseEntity<HttpStatus> deleteProductFromBasket(@RequestBody @Valid ProductDeleteFromBasketRequest request,
                                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        BasketModel basketUser = basketService.findById(request.getBasketId()).orElse(null);
        ProductModel productUser = productService.findById(request.getProductId()).orElse(null);
        basketUser.getProducts().remove(productUser);


        basketService.save(basketUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
