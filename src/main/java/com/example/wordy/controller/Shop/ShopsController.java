package com.example.wordy.controller.Shop;


import com.example.wordy.model.ShopsModel;
import com.example.wordy.service.ShopService;
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
public class ShopsController {

    private final ShopService shopService;

    @Autowired
    public ShopsController(ShopService shopService) {
        this.shopService = shopService;
    }


    @GetMapping("/shop")
    public String getAllShop(Model model) {
        Iterable<ShopsModel> shopsModels = shopService.findAll();
        model.addAttribute("shops", shopsModels);
        model.addAttribute("shop", new ShopsModel());

        return "Shop/shop";
    }

    @PostMapping("/shop/add")
    public String addShop(@ModelAttribute("shop") @Valid ShopsModel shop,
                          BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "Shop/shop";
        }

        shopService.save(shop);
        return "redirect:/shop";
    }

    @PostMapping("/shop/update")
    public String updateShop(@RequestParam int id,
                             @ModelAttribute("shop")
                             @Valid ShopsModel shop, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors()) {
            return "Shop/shop";
        }
        ShopsModel shopsModel = shopService.findById(id).orElseThrow();
        shopsModel.setTitle(shop.getTitle());
        shopsModel.setAddress(shop.getAddress());
        shopService.save(shopsModel);
        return "redirect:/shop";
    }

    @PostMapping("/shop/delete")
    public String deleteShop(@RequestParam int id)
    {
        ShopsModel shopsModel = shopService.findById(id).orElseThrow();
        shopService.delete(shopsModel);
        return "redirect:/shop";
    }

    @PostMapping("/shop/findById")
    public String findShopById(@RequestParam int id,
                               Model model)
    {
        try {
            var shopModel = shopService.findById(id).orElseThrow();
            if (shopModel != null) {
                model.addAttribute("shop", shopModel);
                return "Shop/shopDetails";
            } else {
                return "redirect:/shop";
            }
        }
        catch (Exception e) {
            return "redirect:/shop";
        }
    }



    @PostMapping("/shop/findByTitle")
    public String findPersonalDateByLogin(@RequestParam String title,
                                          Model model) {
        Optional<ShopsModel> shopsModel = shopService.findByTitle(title);
        if (shopsModel.isPresent()) {
            model.addAttribute("shop", shopsModel.get());
            return "Shop/shopDetails";
        } else {
            return "redirect:/shop";
        }

    }
}
