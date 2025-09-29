package com.example.wordy.controller.Product;

import com.example.wordy.model.ProductModel;
import com.example.wordy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class ProductController {

    private final ProductService productService;

    private final TypeOfProductService typeOfProductService;

    private final ManufacturerService manufacturerService;

    private final  ModelService modelService;

    private final CountryService countryService;

    private final BasketService basketService;

    @Autowired
    public ProductController(ProductService productService, TypeOfProductService typeOfProductService, ManufacturerService manufacturerService, ModelService modelService, CountryService countryService, BasketService basketService) {
        this.productService = productService;
        this.typeOfProductService = typeOfProductService;
        this.manufacturerService = manufacturerService;
        this.modelService = modelService;
        this.countryService = countryService;
        this.basketService = basketService;
    }


    private void getAll(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("typeOfProducts", typeOfProductService.findAll());
        model.addAttribute("manufacturers", manufacturerService.findAll());
        model.addAttribute("models", modelService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("baskets", basketService.findAll());
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        model.addAttribute("product", new ProductModel());
        getAll(model);

        return "Product/products";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute("product")
                             @Valid ProductModel product,
                             BindingResult bindingResult, Model model) {
        try {

            if (bindingResult.hasErrors()) {
                getAll(model);
                return "Product/products";
            }

            productService.save(product);
            return "redirect:/products";
        }
        catch (Exception e) {
            getAll(model);
            return "redirect:/products";
        }
    }

    @PostMapping("/products/update")
    public String updateProduct(@RequestParam int id,
                                @ModelAttribute("product")
                                @Valid ProductModel product,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            getAll(model);
            return "Product/products";
        }
        ProductModel existingProduct = productService.findById(id).orElseThrow();
        existingProduct.setPrice(product.getPrice());
        existingProduct.setProductDescription(product.getProductDescription());
        existingProduct.setTypeOfProduct(product.getTypeOfProduct());
        existingProduct.setManufacturer(product.getManufacturer());
        existingProduct.setModel(product.getModel());
        existingProduct.setCountry(product.getCountry());
        existingProduct.setBaskets(product.getBaskets());

        productService.save(existingProduct);
        return "redirect:/products";
    }

    @PostMapping("/products/delete")
    public String deleteProduct(@RequestParam int id) {
        ProductModel product = productService.findById(id).orElseThrow();
        productService.delete(product);
        return "redirect:/products";
    }

    @PostMapping("/products/findById")
    public String findProductById(@RequestParam int id, Model model) {
        try {
            var productData = productService.findById(id).orElseThrow();
            model.addAttribute("product", productData);
            return "Product/productDetails";
        } catch (Exception e) {
            return "redirect:/products";
        }
    }

    @PostMapping("/products/findByDescription")
    public String findProductByDescription(@RequestParam String productDescription, Model model) {
        Optional<ProductModel> product = productService.findByProductDescription(productDescription);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "Product/productDetails";
        } else {
            return "redirect:/products";
        }
    }




}
