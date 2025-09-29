package com.example.wordy.controller.Basket;

import com.example.wordy.model.BasketModel;
import com.example.wordy.service.BasketService;
import com.example.wordy.service.ProductService;
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
public class BasketController {

    private final BasketService basketService;
    private final ProductService productService;

    @Autowired
    public BasketController(BasketService basketService, ProductService productService) {
        this.basketService = basketService;
        this.productService = productService;
    }




    private void getAll(Model model)
    {
        model.addAttribute("baskets", basketService.findAll());
        model.addAttribute("products", productService.findAll());

    }

    @GetMapping("/baskets")
    public String getAllBaskets(Model model) {
        getAll(model);
        model.addAttribute("basket", new BasketModel());

        return "Basket/baskets";
    }

    @PostMapping("/baskets/add")
    public String addBasket(@ModelAttribute("basket") @Valid BasketModel basket,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            getAll(model);
            return "Basket/baskets";
        }

        basketService.save(basket);
        return "redirect:/baskets";
    }

    @PostMapping("/baskets/update")
    public String updateBasket(@RequestParam int id,
                               @ModelAttribute("basket") @Valid BasketModel basket,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            getAll(model);
            return "Basket/baskets";
        }

        BasketModel existingBasket = basketService.findById(id).orElseThrow();
        existingBasket.setQuantity(basket.getQuantity());
        existingBasket.setProducts(basket.getProducts());
        basketService.save(existingBasket);
        return "redirect:/baskets";
    }

    @PostMapping("/baskets/delete")
    public String deleteBasket(@RequestParam int id) {
        BasketModel basketModel = basketService.findById(id).orElseThrow();
        basketService.delete(basketModel);
        return "redirect:/baskets";
    }


    @PostMapping("/baskets/findById")
    public String findBasketById(@RequestParam int id, Model model) {
        Optional<BasketModel> basketModel = basketService.findById(id);
        if (basketModel.isPresent()) {
            model.addAttribute("basket", basketModel.get());
            return "Basket/basketDetails";
        } else {
            return "redirect:/baskets";
        }
    }


    @PostMapping("/baskets/findByQuantity")
    public String findByQuantity(@RequestParam int quantity,
                                 Model model) {
        Optional<BasketModel> basketModel = basketService.findByQuantity(quantity);
        if (basketModel.isPresent()) {
            model.addAttribute("basket", basketModel.get());
            return "Basket/basketDetails";
        } else {
            return "redirect:/baskets";
        }

    }

}
