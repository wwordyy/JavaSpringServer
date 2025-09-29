package com.example.wordy.controller.Shop;

import com.example.wordy.model.ShopsModel;
import com.example.wordy.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/shops")
@Tag(name = "Shops", description = "API для управления магазинами")
public class ShopsRestController {

    private final ShopService shopService;

    @Autowired
    public ShopsRestController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    @Operation(summary = "Получение всех магазинов")
    public Iterable<ShopsModel> getShops() {
        return shopService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение одного магазина")
    public ResponseEntity<ShopsModel> getOneShop(@PathVariable int id) {
        Optional<ShopsModel> shop = shopService.findById(id);

        if (shop.isPresent()) {
            return new ResponseEntity<>(shop.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping()
    @Operation(summary = "Создание магазина")
    public ResponseEntity<HttpStatus> createShop(@RequestBody @Valid ShopsModel shop,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        shopService.save(shop);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление магазина по ID")
    public ResponseEntity<HttpStatus> updateShop(@PathVariable int id,
                                                 @RequestBody @Valid ShopsModel shop,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ShopsModel oldShop = shopService.findById(id).orElse(null);

        if (oldShop == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        oldShop.setTitle(shop.getTitle());
        oldShop.setAddress(shop.getAddress());
        oldShop.setCustomers(shop.getCustomers());
        shopService.save(oldShop);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление магазина по ID")
    public ResponseEntity<HttpStatus> deleteShop(@PathVariable int id) {
        Optional<ShopsModel> shop = shopService.findById(id);

        if (shop.isPresent()) {
            shopService.delete(shop.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
