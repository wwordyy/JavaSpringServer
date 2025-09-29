package com.example.wordy.controller.TypeOfProduct;

import com.example.wordy.model.TypeOfProductModel;
import com.example.wordy.service.TypeOfProductService;
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
public class TypeOfProductController {


    private final TypeOfProductService typeOfProductService;

    @Autowired
    public TypeOfProductController(TypeOfProductService typeOfProductService) {
        this.typeOfProductService = typeOfProductService;
    }


    @GetMapping("/typeOfProducts")
    public String getAllShop(Model model) {
        Iterable<TypeOfProductModel> typeOfProductModels = typeOfProductService.findAll();
        model.addAttribute("typeOfProducts", typeOfProductModels);
        model.addAttribute("typeOfProduct", new TypeOfProductModel());

        return "TypeOfProduct/typeOfProducts";
    }

    @PostMapping("/typeOfProducts/add")
    public String addShop(@ModelAttribute("typeOfProduct")
                              @Valid TypeOfProductModel typeOfProductModel,
                          BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "TypeOfProduct/typeOfProducts";
        }

        typeOfProductService.save(typeOfProductModel);
        return "redirect:/typeOfProducts";
    }

    @PostMapping("/typeOfProducts/update")
    public String updateShop(@RequestParam int id,
                             @ModelAttribute("typeOfProduct")
                             @Valid TypeOfProductModel typeOfProductModel,
                             BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors()) {
            return "TypeOfProduct/typeOfProducts";
        }
        TypeOfProductModel typeOfProductModel1 = typeOfProductService.findById(id).orElseThrow();
        typeOfProductModel1.setTitle(typeOfProductModel.getTitle());
        typeOfProductService.save(typeOfProductModel1);
        return "redirect:/typeOfProducts";
    }

    @PostMapping("/typeOfProducts/delete")
    public String deleteShop(@RequestParam int id)
    {
        TypeOfProductModel typeOfProductModel = typeOfProductService.findById(id).orElseThrow();
        typeOfProductService.delete(typeOfProductModel);
        return "redirect:/typeOfProducts";
    }

    @PostMapping("/typeOfProducts/findById")
    public String findShopById(@RequestParam int id,
                               Model model)
    {
        try {
            var typeOfProduct = typeOfProductService.findById(id).orElseThrow();
            if (typeOfProduct != null) {
                model.addAttribute("typeOfProduct", typeOfProduct);
                return "TypeOfProduct/typeOfProductsDetails";
            } else {
                return "redirect:/typeOfProducts";
            }
        }
        catch (Exception e) {
            return "redirect:/typeOfProducts";
        }
    }



    @PostMapping("/typeOfProducts/findByTitle")
    public String findPersonalDateByLogin(@RequestParam String title,
                                          Model model) {
        Optional<TypeOfProductModel> typeOfProductModel = typeOfProductService.findByTitle(title);
        if (typeOfProductModel.isPresent()) {
            model.addAttribute("typeOfProduct", typeOfProductModel.get());
            return "TypeOfProduct/typeOfProductsDetails";
        } else {
            return "redirect:/typeOfProducts";
        }

    }
}
