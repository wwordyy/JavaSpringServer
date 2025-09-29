package com.example.wordy.util;

import com.example.wordy.model.BasketModel;
import com.example.wordy.model.ProductModel;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class Calculate {


    public static long sumOfAllProducts(BasketModel basket) {

        long sum = 0;

        Collection<ProductModel> products = basket.getProducts();

        for (ProductModel product : products) {
            sum += product.getPrice();
        }

        return sum;

    }
}
